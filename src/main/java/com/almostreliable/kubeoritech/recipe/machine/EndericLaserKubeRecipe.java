package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.interaction.EndericLaserBlockEntity;
import rearth.oritech.datagen.builders.EndericLaserRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link EndericLaserRecipeBuilder}, and {@link EndericLaserBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class EndericLaserKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.LASER.get()),
        EndericLaserKubeRecipe.class,
        EndericLaserKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
