package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.processing.IndustrialChillerBlockEntity;
import rearth.oritech.datagen.builders.IndustrialChillerRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link IndustrialChillerRecipeBuilder}, and {@link IndustrialChillerBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class IndustrialChillerKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.INDUSTRIAL_CHILLER.get()),
        IndustrialChillerKubeRecipe.class,
        IndustrialChillerKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsEmpty();
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputNotEmpty();
        ensureFluidOutputsEmpty();
    }
}
