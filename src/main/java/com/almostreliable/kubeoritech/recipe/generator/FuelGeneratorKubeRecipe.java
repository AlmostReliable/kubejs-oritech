package com.almostreliable.kubeoritech.recipe.generator;

import com.almostreliable.kubeoritech.recipe.base.FluidGeneratorKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.generators.FuelGeneratorEntity;
import rearth.oritech.datagen.builders.FuelGeneratorRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link FuelGeneratorRecipeBuilder}, and {@link FuelGeneratorEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class FuelGeneratorKubeRecipe extends FluidGeneratorKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.FUEL_GENERATOR.get()),
        FuelGeneratorKubeRecipe.class,
        FuelGeneratorKubeRecipe::new
    );
}
