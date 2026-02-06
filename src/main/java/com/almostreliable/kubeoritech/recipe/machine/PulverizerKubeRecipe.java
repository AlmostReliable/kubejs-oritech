package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.PulverizerRecipeBuilder;
import rearth.oritech.block.entity.processing.PulverizerBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link PulverizerRecipeBuilder}, and {@link PulverizerBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class PulverizerKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.PULVERIZER.getIdentifier(),
        PulverizerKubeRecipe.class,
        PulverizerKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsNotEmpty(1, 2);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
