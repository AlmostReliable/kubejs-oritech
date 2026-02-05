package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.ParticleCollisionRecipeBuilder;
import rearth.oritech.block.entity.accelerator.AcceleratorControllerBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link ParticleCollisionRecipeBuilder}, and {@link AcceleratorControllerBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class ParticleCollisionKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.PARTICLE_COLLISION.getIdentifier(),
        ParticleCollisionKubeRecipe.class,
        ParticleCollisionKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(2, 2);
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
