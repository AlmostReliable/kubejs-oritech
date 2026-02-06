package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.CoolerRecipeBuilder;
import rearth.oritech.block.entity.processing.CoolerBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link CoolerRecipeBuilder}, and {@link CoolerBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class CoolerKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.COOLER.getIdentifier(),
        CoolerKubeRecipe.class,
        CoolerKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsEmpty();
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputNotEmpty();
        ensureFluidOutputsEmpty();
    }
}
