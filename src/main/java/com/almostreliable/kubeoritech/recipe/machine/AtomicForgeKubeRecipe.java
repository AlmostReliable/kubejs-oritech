package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.processing.AtomicForgeBlockEntity;
import rearth.oritech.datagen.builders.AtomicForgeRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link AtomicForgeRecipeBuilder}, and {@link AtomicForgeBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class AtomicForgeKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.ATOMIC_FORGE.get()),
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
