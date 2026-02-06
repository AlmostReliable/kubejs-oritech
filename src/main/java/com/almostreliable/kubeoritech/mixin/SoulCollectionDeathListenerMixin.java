package com.almostreliable.kubeoritech.mixin;

import com.almostreliable.kubeoritech.KubePlugin;
import com.almostreliable.kubeoritech.event.SoulCollectionEvent;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import com.google.common.base.Preconditions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rearth.oritech.block.entity.arcane.BaseSoulCollectionEntity;

import org.jetbrains.annotations.Nullable;

/**
 * See {@link SoulCollectionEvent}.
 */
@Mixin(BaseSoulCollectionEntity.DeathListener.class)
public class SoulCollectionDeathListenerMixin {

    @Shadow
    @Final
    BaseSoulCollectionEntity this$0;

    @Inject(method = "handleGameEvent", at = @At(value = "INVOKE", target = "Lrearth/oritech/block/entity/arcane/BaseSoulCollectionEntity;onSoulIncoming(Lnet/minecraft/world/phys/Vec3;)V"), cancellable = true, remap = false)
    private void kubejs_oritech$onSoulCollection(
        ServerLevel level, Holder<GameEvent> event, GameEvent.Context emitter, Vec3 emitterPos, CallbackInfoReturnable<Boolean> cir
    ) {
        if (!KubePlugin.Events.SOUL_COLLECTION.hasListeners()) {
            return;
        }

        var entity = emitter.sourceEntity();
        Preconditions.checkArgument(entity == null || entity instanceof LivingEntity);

        var kubeEvent = new SoulCollectionEvent(level, emitterPos, this$0, (@Nullable LivingEntity) entity);
        if (KubePlugin.Events.SOUL_COLLECTION.post(kubeEvent).interruptFalse()) {
            cir.setReturnValue(false);
        }

        var souls = kubeEvent.getSoulValue() - 1; // -1 for the default soul being collected
        for (var i = 0; i < souls; i++) {
            this$0.onSoulIncoming(emitterPos);
        }
    }
}
