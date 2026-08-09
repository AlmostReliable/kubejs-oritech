package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.datagen.builders.LaserRecipeBuilder;
import rearth.oritech.block.entity.interaction.EndericLaserBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link LaserRecipeBuilder}, and {@link EndericLaserBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class LaserKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        idFromRecipeType(RecipeContent.LASER.get()),
        LaserKubeRecipe.class,
        LaserKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
