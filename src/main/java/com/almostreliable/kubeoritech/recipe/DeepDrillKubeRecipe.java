package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.DeepDrillRecipeBuilder;
import rearth.oritech.block.entity.interaction.DeepDrillEntity;
import rearth.oritech.init.recipes.RecipeContent;

// TODO: add custom event to add tag and recipe automatically

/**
 * See {@link DeepDrillRecipeBuilder}, and {@link DeepDrillEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class DeepDrillKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.DEEP_DRILL.getIdentifier(),
        DeepDrillKubeRecipe.class,
        DeepDrillKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
