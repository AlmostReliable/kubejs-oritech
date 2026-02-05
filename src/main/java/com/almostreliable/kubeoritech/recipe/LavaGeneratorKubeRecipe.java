package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.FluidGeneratorKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.LavaGeneratorRecipeBuilder;
import rearth.oritech.block.entity.generators.LavaGeneratorEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link LavaGeneratorRecipeBuilder}, and {@link LavaGeneratorEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class LavaGeneratorKubeRecipe extends FluidGeneratorKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.LAVA_GENERATOR.getIdentifier(),
        LavaGeneratorKubeRecipe.class,
        LavaGeneratorKubeRecipe::new
    );
}
