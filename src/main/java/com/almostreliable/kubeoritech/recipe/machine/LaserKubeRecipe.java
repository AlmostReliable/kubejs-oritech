package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.LaserRecipeBuilder;
import rearth.oritech.block.entity.interaction.LaserArmBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link LaserRecipeBuilder}, and {@link LaserArmBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class LaserKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.LASER.getIdentifier(),
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
