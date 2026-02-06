package com.almostreliable.kubeoritech.mixin;

import com.almostreliable.kubeoritech.KubePlugin;
import com.almostreliable.kubeoritech.event.DeepDrillRegistrationEvent;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;

import com.google.gson.JsonElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    private void kubejs_oritech$injectRecipes(
        Map<ResourceLocation, JsonElement> recipes, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci
    ) {
        if (KubePlugin.Events.DEEP_DRILL.hasListeners()) {
            DeepDrillRegistrationEvent.onInjectRecipes(recipes::put);
        }
    }
}
