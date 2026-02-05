package com.almostreliable.kubeoritech.recipe;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.AssemblerRecipeBuilder;
import rearth.oritech.block.entity.processing.AssemblerBlockEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link AssemblerRecipeBuilder}, and {@link AssemblerBlockEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class AssemblerKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.ASSEMBLER.getIdentifier(),
        AssemblerKubeRecipe.class,
        AssemblerKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(4, 4);
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
