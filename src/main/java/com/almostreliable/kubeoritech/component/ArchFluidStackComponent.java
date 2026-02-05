package com.almostreliable.kubeoritech.component;

import com.mojang.serialization.Codec;
import dev.architectury.fluid.FluidStack;
import dev.latvian.mods.kubejs.fluid.FluidWrapper;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.type.TypeInfo;
import io.wispforest.owo.serialization.CodecUtils;
import rearth.oritech.Oritech;
import rearth.oritech.init.recipes.OritechRecipeType;

public record ArchFluidStackComponent(RecipeComponentType<?> type) implements RecipeComponent<FluidStack> {

    public static final RecipeComponentType<FluidStack> TYPE = RecipeComponentType.unit(
        Oritech.id("fluid_stack"),
        ArchFluidStackComponent::new
    );

    @Override
    public Codec<FluidStack> codec() {
        return CodecUtils.toCodec(OritechRecipeType.FLUID_STACK_ENDEC);
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(FluidStack.class)
            .or(FluidWrapper.FLUID_TYPE_INFO)
            .or(FluidWrapper.TYPE_INFO);
    }

    @Override
    public FluidStack wrap(RecipeScriptContext cx, Object from) {
        if (from instanceof FluidStack s) {
            return s;
        }

        var wrapped = FluidWrapper.wrap(cx.cx(), from);
        if (wrapped.isEmpty()) {
            return FluidStack.empty();
        }

        return FluidStack.create(wrapped.getFluid(), wrapped.getAmount(), wrapped.getComponentsPatch());
    }

    @Override
    public boolean allowEmpty() {
        return true;
    }

    @Override
    public boolean isEmpty(FluidStack value) {
        return value.isEmpty();
    }
}
