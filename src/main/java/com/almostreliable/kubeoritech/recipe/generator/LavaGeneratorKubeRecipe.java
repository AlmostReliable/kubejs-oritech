package com.almostreliable.kubeoritech.recipe.generator;

import com.almostreliable.kubeoritech.recipe.base.FluidGeneratorKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.generators.LavaGeneratorEntity;
import rearth.oritech.datagen.builders.LavaGeneratorRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link LavaGeneratorRecipeBuilder}, and {@link LavaGeneratorEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class LavaGeneratorKubeRecipe extends FluidGeneratorKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.LAVA_GENERATOR.get()),
        LavaGeneratorKubeRecipe.class,
        LavaGeneratorKubeRecipe::new
    );
}
