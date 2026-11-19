package com.almostreliable.kubeoritech.recipe.generator;

import com.almostreliable.kubeoritech.recipe.base.OritechKubeRecipe;

import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import rearth.oritech.api.recipe.SteamGeneratorRecipeBuilder;
import rearth.oritech.block.entity.generators.SteamEngineEntity;
import rearth.oritech.init.recipes.RecipeContent;

/**
 * See {@link SteamGeneratorRecipeBuilder}, and {@link SteamEngineEntity}.
 * <p>
 * The steam engine only accepts fluids from the {@code c:steam} tag and ignores the recipe time. The fluid input amount
 * is the base steam consumption per tick.
 */
@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public class SteamEngineKubeRecipe extends OritechKubeRecipe {

    public static final KubeRecipeFactory FACTORY = new KubeRecipeFactory(
        RecipeContent.STEAM_ENGINE.getIdentifier(),
        SteamEngineKubeRecipe.class,
        SteamEngineKubeRecipe::new
    );

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsEmpty();
        ensureItemOutputsEmpty();
        ensureFluidInputNotEmpty();
        ensureFluidOutputsEmpty();
    }
}
