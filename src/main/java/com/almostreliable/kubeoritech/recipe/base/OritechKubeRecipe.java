package com.almostreliable.kubeoritech.recipe.base;

import com.almostreliable.kubeoritech.schema.OritechRecipeSchema;

import dev.latvian.mods.kubejs.error.InvalidRecipeComponentValueException;
import dev.latvian.mods.kubejs.error.KubeRuntimeException;
import dev.latvian.mods.kubejs.recipe.KubeRecipe;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.RecipesKubeEvent;
import dev.latvian.mods.kubejs.script.ConsoleJS;
import rearth.oritech.init.recipes.OritechRecipe;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * See {@link OritechRecipe}, and {@link OritechRecipeSchema}.
 */
public abstract class OritechKubeRecipe extends KubeRecipe {

    public boolean valid = true;

    @Override
    public void serialize() {
        // workaround for the validation method firing too early (after CTor call, before chained functions)
        try {
            validateBeforeSerialization();
            super.serialize();
        } catch (KubeRuntimeException e) {
            ConsoleJS.SERVER.error("Error creating recipe '" + getOrCreateId() + "'", e, RecipesKubeEvent.CREATE_RECIPE_SKIP_ERROR);
            valid = false;
        }
    }

    public OritechKubeRecipe timeInSeconds(int timeInSeconds) {
        var ticks = timeInSeconds * 20;
        setValue(OritechRecipeSchema.TIME, ticks);
        return this;
    }

    public OritechKubeRecipe seconds(int timeInSeconds) {
        return timeInSeconds(timeInSeconds);
    }

    protected abstract void validateBeforeSerialization();

    private void ensureEmpty(RecipeKey<? extends List<?>> key, String name) {
        var value = getValue(key);
        if (value != null && !value.isEmpty()) {
            throw new InvalidRecipeComponentValueException(
                "this recipe type (" + type.idString + ") doesn't support " + name,
                key.component,
                value
            ).source(sourceLine);
        }
    }

    private void ensureOptional(RecipeKey<? extends List<?>> key, String name, @Nullable Integer max) {
        var value = getValue(key);
        if (value != null && max != null && value.size() > max) {
            throw new InvalidRecipeComponentValueException(
                "this recipe type (" + type.idString + ") can have at most " + max + " " + name,
                key.component,
                value
            ).source(sourceLine);
        }
    }

    private void ensureNotEmpty(RecipeKey<? extends List<?>> key, String name, int min, @Nullable Integer max) {
        var message = "";
        if (max != null) {
            if (min == max) {
                message = "exactly " + min;
            } else {
                message = "between " + min + " and " + max;
            }
        } else {
            message = "at least " + min;
        }
        message += " " + name;

        var value = getValue(key);
        if (value == null || value.isEmpty() || value.size() < min || (max != null && value.size() > max)) {
            throw new InvalidRecipeComponentValueException(
                "this recipe type (" + type.idString + ") needs " + message,
                key.component,
                value
            ).source(sourceLine);
        }
    }

    protected void ensureItemInputsEmpty() {
        ensureEmpty(OritechRecipeSchema.ITEM_INPUTS, "item inputs");
    }

    protected void ensureItemInputsOptional(int max) {
        ensureOptional(OritechRecipeSchema.ITEM_INPUTS, "item inputs", max);
    }

    protected void ensureItemInputsNotEmpty(int min, @Nullable Integer max) {
        ensureNotEmpty(OritechRecipeSchema.ITEM_INPUTS, "item inputs", min, max);
    }

    protected void ensureItemOutputsEmpty() {
        ensureEmpty(OritechRecipeSchema.ITEM_OUTPUTS, "item outputs");
    }

    protected void ensureItemOutputsOptional(int max) {
        ensureOptional(OritechRecipeSchema.ITEM_OUTPUTS, "item outputs", max);
    }

    protected void ensureItemOutputsNotEmpty(int min, @Nullable Integer max) {
        ensureNotEmpty(OritechRecipeSchema.ITEM_OUTPUTS, "item outputs", min, max);
    }

    protected void ensureFluidInputEmpty() {
        var fluidInput = getValue(OritechRecipeSchema.FLUID_INPUT);
        if (fluidInput != null && !fluidInput.isEmpty()) {
            throw new InvalidRecipeComponentValueException(
                "this recipe type (" + type.idString + ") doesn't support a fluid input",
                OritechRecipeSchema.FLUID_INPUT.component,
                fluidInput
            ).source(sourceLine);
        }
    }

    protected void ensureFluidInputNotEmpty() {
        var fluidInput = getValue(OritechRecipeSchema.FLUID_INPUT);
        if (fluidInput == null || fluidInput.isEmpty()) {
            throw new InvalidRecipeComponentValueException(
                "this recipe type (" + type.idString + ") needs a fluid input",
                OritechRecipeSchema.FLUID_INPUT.component,
                fluidInput
            ).source(sourceLine);
        }
    }

    protected void ensureFluidOutputsEmpty() {
        ensureEmpty(OritechRecipeSchema.FLUID_OUTPUTS, "fluid outputs");
    }

    protected void ensureFluidOutputsOptional(int max) {
        ensureOptional(OritechRecipeSchema.FLUID_OUTPUTS, "fluid outputs", max);
    }

    void ensureFluidOutputsNotEmpty(int min, @Nullable Integer max) {
        ensureNotEmpty(OritechRecipeSchema.FLUID_OUTPUTS, "fluid outputs", min, max);
    }

    void ensureFluidOutputsNotEmpty() {
        ensureFluidOutputsNotEmpty(1, null);
    }

    protected void ensureAtLeastOneInput() {
        var itemInputs = getValue(OritechRecipeSchema.ITEM_INPUTS);
        var fluidInput = getValue(OritechRecipeSchema.FLUID_INPUT);
        if ((itemInputs == null || itemInputs.isEmpty()) && fluidInput == null) {
            throw new KubeRuntimeException("this recipe type (" + type.idString + ") needs at least one item or fluid input")
                .source(sourceLine);
        }
    }

    protected void ensureAtLeastOneOutput() {
        var itemOutputs = getValue(OritechRecipeSchema.ITEM_OUTPUTS);
        var fluidOutputs = getValue(OritechRecipeSchema.FLUID_OUTPUTS);
        if ((itemOutputs == null || itemOutputs.isEmpty()) && (fluidOutputs == null || fluidOutputs.isEmpty())) {
            throw new KubeRuntimeException("this recipe type (" + type.idString + ") needs at least one item or fluid output")
                .source(sourceLine);
        }
    }
}
