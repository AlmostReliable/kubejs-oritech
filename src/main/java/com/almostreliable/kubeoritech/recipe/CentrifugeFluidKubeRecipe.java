package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.CentrifugeFluidRecipeBuilder;
import rearth.oritech.block.entity.processing.CentrifugeBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link CentrifugeFluidRecipeBuilder}, and {@link CentrifugeBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class CentrifugeFluidKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.CENTRIFUGE_FLUID.getIdentifier(),
        CentrifugeFluidKubeRecipe.class,
        CentrifugeFluidKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsOptional(1);
        ensureItemOutputsOptional(2);
        ensureFluidOutputsOptional(1);
        ensureAtLeastOneInput();
        ensureAtLeastOneOutput();
    }
}
