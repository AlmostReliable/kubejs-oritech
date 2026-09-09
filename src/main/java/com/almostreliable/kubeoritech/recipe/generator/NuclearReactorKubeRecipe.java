package com.almostreliable.kubeoritech.recipe.generator;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.reactor.NuclearReactorControllerBlockEntity;
import rearth.oritech.datagen.builders.NuclearReactorGeneratorRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link NuclearReactorGeneratorRecipeBuilder}, and {@link NuclearReactorControllerBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class NuclearReactorKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.REACTOR.get()),
        NuclearReactorKubeRecipe.class,
        NuclearReactorKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsEmpty();
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
