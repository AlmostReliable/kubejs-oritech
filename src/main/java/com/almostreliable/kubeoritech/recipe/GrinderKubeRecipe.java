package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.GrinderRecipeBuilder;
import rearth.oritech.block.entity.processing.FragmentForgeBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link GrinderRecipeBuilder}, and {@link FragmentForgeBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class GrinderKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.GRINDER.getIdentifier(),
        GrinderKubeRecipe.class,
        GrinderKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsNotEmpty(1, 3);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
