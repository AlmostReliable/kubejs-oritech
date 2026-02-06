package com.almostreliable.kubeoritech.recipe.generator;

import com.almostreliable.kubeoritech.recipe.base.FluidGeneratorKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.FuelGeneratorRecipeBuilder;
import rearth.oritech.block.entity.generators.FuelGeneratorEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link FuelGeneratorRecipeBuilder}, and {@link FuelGeneratorEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class FuelGeneratorKubeRecipe extends FluidGeneratorKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.FUEL_GENERATOR.getIdentifier(),
        FuelGeneratorKubeRecipe.class,
        FuelGeneratorKubeRecipe::new
    );
}
