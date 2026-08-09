package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.processing.FoundryBlockEntity;
import rearth.oritech.datagen.builders.FoundryRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link FoundryRecipeBuilder}, and {@link FoundryBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class FoundryKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        idFromRecipeType(RecipeContent.FOUNDRY.get()),
        FoundryKubeRecipe.class,
        FoundryKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(2, 2);
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
