package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.ReactorGeneratorRecipeBuilder;
import rearth.oritech.block.entity.reactor.ReactorControllerBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link ReactorGeneratorRecipeBuilder}, and {@link ReactorControllerBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class ReactorKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.REACTOR.getIdentifier(),
        ReactorKubeRecipe.class,
        ReactorKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsEmpty();
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
