package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.RefineryRecipeBuilder;
import rearth.oritech.block.entity.processing.RefineryBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link RefineryRecipeBuilder}, and {@link RefineryBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class RefineryKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.REFINERY.getIdentifier(),
        RefineryKubeRecipe.class,
        RefineryKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsOptional(1);
        ensureItemOutputsOptional(1);
        ensureFluidOutputsOptional(3);
        ensureAtLeastOneInput();
        ensureAtLeastOneOutput();
    }
}
