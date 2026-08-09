package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.processing.RefineryBlockEntity;
import rearth.oritech.datagen.builders.RefineryRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link RefineryRecipeBuilder}, and {@link RefineryBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class RefineryKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.REFINERY.get()),
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
