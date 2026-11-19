package com.almostreliable.kubeoritech.event.particle.state;

import com.almostreliable.kubeoritech.KubePlugin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import dev.latvian.mods.kubejs.event.EventExit;
import dev.latvian.mods.kubejs.level.CachedLevelBlock;
import dev.latvian.mods.kubejs.level.LevelBlock;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.util.HideFromJS;
import org.jspecify.annotations.Nullable;
import rearth.oritech.api.accelerator.ParticleImpactContext;
import rearth.oritech.api.accelerator.ParticleImpactResult;
import rearth.oritech.block.entity.accelerator.ParticleAcceleratorBlockEntity.ParticleEvent;

import java.util.Optional;

@SuppressWarnings("WeakerAccess")
public final class ParticleImpactEvent extends ParticleStateEvent {

    private static final ParticleImpactResult PASS_THROUGH = new ParticleImpactResult(0, false, false);

    private final ParticleImpactContext context;
    private final LevelBlock block;

    @Nullable
    private ParticleImpactResult result;
    @Nullable
    private Float defaultUsedMomentum;

    private ParticleImpactEvent(ParticleImpactContext context) {
        super(context.accelerator());
        this.context = context;
        this.block = new CachedLevelBlock(context.level(), context.hitPos()).cache(context.hitState());
    }

    @HideFromJS
    public static Optional<ParticleImpactResult> onImpact(ParticleImpactContext context) {
        var eventHandler = KubePlugin.Events.PARTICLE_IMPACT;
        var key = context.hitState().getBlock().kjs$getKey();
        if (!eventHandler.hasListeners(key)) return Optional.empty();

        var kubeEvent = new ParticleImpactEvent(context);
        var eventResult = eventHandler.post(kubeEvent, key);

        if (kubeEvent.result != null) return Optional.of(kubeEvent.result);
        if (eventResult.interruptFalse()) return Optional.of(PASS_THROUGH);
        if (kubeEvent.defaultUsedMomentum != null) {
            var momentum = (long) Math.ceil(kubeEvent.defaultUsedMomentum);
            var result = new ParticleImpactResult(momentum, false, false);
            return Optional.of(result);
        }
        return Optional.empty();
    }

    public Object absorb(Context cx) throws EventExit {
        return absorb(cx, false);
    }

    public Object absorb(Context cx, boolean consumeItem) throws EventExit {
        var impactResult = ParticleImpactResult.absorbed(context, consumeItem);
        return finish(cx, impactResult);
    }

    public Object passThrough(Context cx) throws EventExit {
        return finish(cx, PASS_THROUGH);
    }

    public Object handle(Context cx, long usedMomentum) throws EventExit {
        return handle(cx, usedMomentum, false, false);
    }

    public Object handle(Context cx, long usedMomentum, boolean consumeItem, boolean consumeParticle) throws EventExit {
        var momentum = Math.max(0, usedMomentum);
        var impactResult = new ParticleImpactResult(momentum, consumeItem, consumeParticle);
        return finish(cx, impactResult);
    }

    public float runDefaultImpact() {
        if (defaultUsedMomentum == null) {
            defaultUsedMomentum = context.accelerator()
                .handleParticleBlockCollision(context.hitPos(), context.speed(), context.hitState());
        }
        return defaultUsedMomentum;
    }

    private Object finish(Context cx, ParticleImpactResult impactResult) throws EventExit {
        result = impactResult;
        return success(cx);
    }

    public ServerLevel getLevel() {
        return context.level();
    }

    public BlockPos getPos() {
        return context.controllerPos();
    }

    public BlockPos getHitPos() {
        return context.hitPos();
    }

    public LevelBlock getBlock() {
        return block;
    }

    public BlockState getBlockState() {
        return context.hitState();
    }

    public Vec3 getDirection() {
        return context.direction();
    }

    public ParticleEvent getReason() {
        return context.reason();
    }

    public long getSpeed() {
        return context.speed();
    }

    public ItemStack getItem() {
        return context.particleItem();
    }
}
