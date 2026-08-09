package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.accelerator.ParticleAcceleratorBlockEntity;
import rearth.oritech.datagen.builders.ParticleCollisionRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link ParticleCollisionRecipeBuilder}, and {@link ParticleAcceleratorBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class ParticleCollisionKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        idFromRecipeType(RecipeContent.PARTICLE_COLLISION.get()),
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
