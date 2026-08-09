package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.processing.CentrifugeBlockEntity;
import rearth.oritech.datagen.builders.CentrifugeRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link CentrifugeRecipeBuilder}, and {@link CentrifugeBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class CentrifugeKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        idFromRecipeType(RecipeContent.CENTRIFUGE.get()),
        CentrifugeKubeRecipe.class,
        CentrifugeKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsNotEmpty(1, 2);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
