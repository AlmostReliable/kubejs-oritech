package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.BioGeneratorRecipeBuilder;
import rearth.oritech.block.entity.generators.BioGeneratorEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link BioGeneratorRecipeBuilder}, and {@link BioGeneratorEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class BioGeneratorKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.BIO_GENERATOR.getIdentifier(),
        BioGeneratorKubeRecipe.class,
        BioGeneratorKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsEmpty();
        ensureFluidInputEmpty();
        ensureFluidInputEmpty();
    }
}
