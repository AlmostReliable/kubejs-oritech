package com.almostreliable.kubeoritech.mixin;

import com.almostreliable.kubeoritech.KubePlugin;
import com.almostreliable.kubeoritech.ModInitializer;
import com.almostreliable.kubeoritech.event.particle.state.ParticleCollidedEvent;
import com.almostreliable.kubeoritech.event.particle.state.ParticleInjectedEvent;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import com.google.common.base.Preconditions;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rearth.oritech.api.item.containers.InOutInventoryStorage;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;
import rearth.oritech.block.entity.accelerator.AcceleratorParticleLogic;
import rearth.oritech.init.recipes.OritechRecipe;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@Mixin(AcceleratorControllerBlockEntity.class)
public abstract class AcceleratorControllerBlockEntityMixin {

    @Shadow
    @Nullable
    private AcceleratorParticleLogic.ActiveParticle particle;
    @Shadow
    public ItemStack activeItemParticle;

    @Shadow
    protected abstract boolean tryCraftResult(float speed, ItemStack inputA, ItemStack inputB);

    @Shadow
    @Final
    public InOutInventoryStorage inventory;

    @Inject(method = "injectParticle", at = @At(value = "INVOKE", target = "Lrearth/oritech/api/item/containers/InOutInventoryStorage;getItem(I)Lnet/minecraft/world/item/ItemStack;"), cancellable = true, remap = false)
    private void kubejs_oritech$onInjectParticle(
        CallbackInfo ci, @Local(name = "startPosition") BlockPos startPos, @Local(name = "nextGate") BlockPos firstGatePos
    ) {
        var eventHandler = KubePlugin.Events.PARTICLE_INJECTED;
        if (!eventHandler.hasListeners()) return;

        var blockEntity = (AcceleratorControllerBlockEntity) (Object) this;
        var level = (ServerLevel) blockEntity.getLevel();

        Preconditions.checkNotNull(level);
        Preconditions.checkNotNull(particle);

        var kubeEvent = new ParticleInjectedEvent(level, blockEntity, startPos, firstGatePos, particle, inventory.getItem(0));
        if (eventHandler.post(kubeEvent).interruptFalse()) {
            particle = null;
            ci.cancel();
        }
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Inject(method = "tryCraftResult", at = @At(value = "INVOKE", target = "Ljava/util/Optional;isEmpty()Z", ordinal = 1), cancellable = true, remap = false)
    private void kubejs_oritech$onParticleCollidedCraft(
        float speed, ItemStack inputA, ItemStack inputB, CallbackInfoReturnable<Boolean> cir,
        @Local(name = "candidate") Optional<RecipeHolder<OritechRecipe>> candidate
    ) {
        var eventHandler = KubePlugin.Events.PARTICLE_COLLIDED;
        if (!eventHandler.hasListeners()) return;

        var blockEntity = (AcceleratorControllerBlockEntity) (Object) this;
        var level = (ServerLevel) blockEntity.getLevel();
        var recipeHolder = candidate.orElse(null);
        var recipeId = recipeHolder == null ? null : recipeHolder.id();
        var recipe = recipeHolder == null ? null : recipeHolder.value();

        Preconditions.checkNotNull(level);
        Preconditions.checkNotNull(particle);

        var kubeEvent = new ParticleCollidedEvent(level, blockEntity.getBlockPos(), blockEntity, inputA, inputB, speed, recipeId, recipe);
        if (eventHandler.post(kubeEvent).interruptFalse()) {
            cir.setReturnValue(false);
        }
    }

    @WrapWithCondition(method = "onParticleCollided", at = @At(value = "INVOKE", target = "Lrearth/oritech/block/entity/accelerator/AcceleratorControllerBlockEntity;spawnEndPortal(Lnet/minecraft/core/BlockPos;)V"), remap = false)
    private boolean kubejs_oritech$onSpawnEndPortal(
        AcceleratorControllerBlockEntity instance, BlockPos pos, @Local(name = "relativeSpeed") float relativeSpeed,
        @Local(name = "secondControllerEntity") AcceleratorControllerBlockEntity secondControllerEntity
    ) {
        if (ModInitializer.END_PORTAL_ENABLED) return true;

        // it should still be possible to craft an item with two ender pearls
        tryCraftResult(relativeSpeed, activeItemParticle, secondControllerEntity.activeItemParticle);
        return false;
    }

    @WrapWithCondition(method = "onParticleCollided", at = @At(value = "INVOKE", target = "Lrearth/oritech/block/entity/accelerator/AcceleratorControllerBlockEntity;spawnNetherPortal(Lnet/minecraft/core/BlockPos;)V"), remap = false)
    private boolean kubejs_oritech$onSpawnNetherPortal(
        AcceleratorControllerBlockEntity instance, BlockPos pos, @Local(name = "relativeSpeed") float relativeSpeed,
        @Local(name = "secondControllerEntity") AcceleratorControllerBlockEntity secondControllerEntity
    ) {
        if (ModInitializer.NETHER_PORTA_ENABLED) return true;

        // it should still be possible to craft an item with two fire charges
        tryCraftResult(relativeSpeed, activeItemParticle, secondControllerEntity.activeItemParticle);
        return false;
    }
}
