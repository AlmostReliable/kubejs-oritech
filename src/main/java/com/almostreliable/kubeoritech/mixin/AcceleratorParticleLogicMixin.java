package com.almostreliable.kubeoritech.mixin;

import com.almostreliable.kubeoritech.KubePlugin;
import com.almostreliable.kubeoritech.event.particle.state.ParticleExitedEvent;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

import com.google.common.base.Preconditions;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rearth.oritech.block.entity.accelerator.AcceleratorParticleLogic;
import rearth.oritech.block.entity.accelerator.AcceleratorParticleLogic.ActiveParticle;
import rearth.oritech.block.entity.accelerator.ParticleAcceleratorBlockEntity;
import rearth.oritech.block.entity.accelerator.ParticleAcceleratorBlockEntity.ParticleEvent;

@Mixin(AcceleratorParticleLogic.class)
public abstract class AcceleratorParticleLogicMixin {

    @Shadow
    @Final
    private ParticleAcceleratorBlockEntity entity;

    @Inject(
        method = "exitParticle",
        at = @At(value = "INVOKE", target = "Lrearth/oritech/block/entity/accelerator/ParticleAcceleratorBlockEntity;onParticleExited(Lrearth/oritech/block/entity/accelerator/ParticleAcceleratorBlockEntity$ParticleEvent;)V"),
        remap = false
    )
    private void kubejs_oritech$onParticleExited(
        ActiveParticle particle,
        Vec3 direction,
        ParticleEvent reason,
        CallbackInfo ci,
        @Local(name = "exitFrom") Vec3 exitFrom,
        @Local(name = "exitTo") Vec3 exitTo
    ) {
        var eventHandler = KubePlugin.Events.PARTICLE_EXITED;
        if (!eventHandler.hasListeners()) return;

        var level = (ServerLevel) entity.getLevel();
        Preconditions.checkNotNull(level);

        var kubeEvent = new ParticleExitedEvent(
            level,
            entity.getBlockPos(),
            entity,
            particle.lastGate,
            exitFrom,
            exitTo,
            direction,
            reason
        );
        eventHandler.post(kubeEvent);
    }
}
