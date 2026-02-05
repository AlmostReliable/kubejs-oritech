package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.AtomicForgeRecipeBuilder;
import rearth.oritech.block.entity.processing.AtomicForgeBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link AtomicForgeRecipeBuilder}, and {@link AtomicForgeBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class AtomicForgeKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.ATOMIC_FORGE.getIdentifier(),
        AtomicForgeKubeRecipe.class,
        AtomicForgeKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 3);
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
