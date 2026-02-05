package com.almostreliable.kubeoritech.mixin;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import com.llamalad7.mixinextras.injector.ModifyReceiver;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.RecipesKubeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(RecipesKubeEvent.class)
public class RecipesKubeEventMixin {

    @ModifyReceiver(method = "applyChanges", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;peek(Ljava/util/function/Consumer;)Ljava/util/stream/Stream;", ordinal = 1), remap = false)
    private Stream<KubeRecipe> kubejs_oritech$filterInvalidRecipes(Stream<KubeRecipe> instance, Consumer<? super KubeRecipe> peekConsumer) {
        return instance.filter(t -> t instanceof OritechKubeRecipe recipe && recipe.valid);
    }
}
