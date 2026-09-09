package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.processing.FragmentForgeBlockEntity;
import rearth.oritech.datagen.builders.FragmentForgeRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link FragmentForgeRecipeBuilder}, and {@link FragmentForgeBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class FragmentForgeKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.FRAGMENT_FORGE.get()),
        FragmentForgeKubeRecipe.class,
        FragmentForgeKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsNotEmpty(1, 3);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
