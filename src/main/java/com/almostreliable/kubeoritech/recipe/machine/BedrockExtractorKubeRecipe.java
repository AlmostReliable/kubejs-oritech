package com.almostreliable.kubeoritech.recipe.machine;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.block.entity.interaction.BedrockExtractorEntity;
import rearth.oritech.datagen.builders.BedrockExtractorRecipeBuilder;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link BedrockExtractorRecipeBuilder}, and {@link BedrockExtractorEntity}.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class BedrockExtractorKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        getRecipeTypeId(RecipeContent.BEDROCK_EXTRACTOR.get()),
        BedrockExtractorKubeRecipe.class,
        BedrockExtractorKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsNotEmpty(1, 1);
        ensureItemOutputsNotEmpty(1, 1);
        ensureFluidInputEmpty();
        ensureFluidOutputsEmpty();
    }
}
