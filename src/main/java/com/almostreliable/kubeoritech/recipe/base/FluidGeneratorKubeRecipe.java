package com.almostreliable.kubeoritech.recipe.base;

import com.almostreliable.kubeoritech.schema.OritechRecipeSchema;

import net.minecraft.world.level.material.Fluids;

import com.google.common.base.Preconditions;
import dev.architectury.fluid.FluidStack;
import dev.latvian.mods.kubejs.error.InvalidRecipeComponentValueException;
import rearth.oritech.block.base.entity.FluidMultiblockGeneratorBlockEntity;

/**
 * See {@link FluidMultiblockGeneratorBlockEntity}.
 */
public abstract class FluidGeneratorKubeRecipe extends OritechKubeRecipe {

    @Override
    protected void validateBeforeSerialization() {
        ensureItemInputsEmpty();
        ensureItemOutputsEmpty();
        ensureFluidInputNotEmpty();
        ensureFluidOutputsEmpty();

        var fluidInput = getValue(OritechRecipeSchema.FLUID_INPUT);
        Preconditions.checkNotNull(fluidInput);
        if (fluidInput.test(FluidStack.create(Fluids.WATER, 1))) {
            throw new InvalidRecipeComponentValueException(
                "this recipe type (" + type.idString + ") doesn't support water as the fluid input",
                OritechRecipeSchema.FLUID_INPUT.component,
                fluidInput
            ).source(sourceLine);
        }
    }
}
