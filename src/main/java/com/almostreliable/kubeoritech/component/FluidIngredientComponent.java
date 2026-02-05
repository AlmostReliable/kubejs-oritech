package com.almostreliable.kubeoritech.component;

import net.neoforged.neoforge.fluids.crafting.EmptyFluidIngredient;
import net.neoforged.neoforge.fluids.crafting.SingleFluidIngredient;
import net.neoforged.neoforge.fluids.crafting.TagFluidIngredient;

import com.mojang.serialization.Codec;
import dev.latvian.mods.kubejs.error.InvalidRecipeComponentValueException;
import dev.latvian.mods.kubejs.fluid.FluidWrapper;
import dev.latvian.mods.kubejs.recipe.RecipeScriptContext;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.rhino.NativeArray;
import dev.latvian.mods.rhino.type.TypeInfo;
import io.wispforest.owo.serialization.CodecUtils;
import rearth.oritech.Oritech;
import rearth.oritech.util.FluidIngredient;

public record FluidIngredientComponent(RecipeComponentType<?> type) implements RecipeComponent<FluidIngredient> {

    public static final RecipeComponentType<FluidIngredient> TYPE = RecipeComponentType.unit(
        Oritech.id("fluid_ingredient"),
        FluidIngredientComponent::new
    );

    @Override
    public Codec<FluidIngredient> codec() {
        return CodecUtils.toCodec(FluidIngredient.FLUID_INGREDIENT_ENDEC);
    }

    @Override
    public TypeInfo typeInfo() {
        return TypeInfo.of(FluidIngredient.class)
            .or(FluidWrapper.SIZED_INGREDIENT_TYPE_INFO)
            .or(FluidWrapper.INGREDIENT_TYPE_INFO)
            .or(FluidWrapper.FLUID_TYPE_INFO)
            .or(FluidWrapper.TYPE_INFO);
    }

    @Override
    public FluidIngredient wrap(RecipeScriptContext cx, Object from) {
        if (from instanceof FluidIngredient i) {
            return i;
        }
        if (from instanceof NativeArray array) {
            if (array.size() != 1) {
                throw new InvalidRecipeComponentValueException(
                    "fluid ingredient array must have exactly one element",
                    this,
                    from
                ).source(cx.recipe().sourceLine);
            }
            return wrap(cx, array.getFirst());
        }

        var wrapped = FluidWrapper.wrapSizedIngredient(cx.cx(), from);
        var fluidIngredient = wrapped.ingredient();
        var amount = wrapped.amount();

        if (wrapped == FluidWrapper.EMPTY_SIZED || fluidIngredient.isEmpty() || wrapped.amount() <= 0) {
            return FluidIngredient.EMPTY;
        }

        var converted = switch (fluidIngredient) {
            case EmptyFluidIngredient ignored -> FluidIngredient.EMPTY;
            case SingleFluidIngredient single -> {
                var key = single.fluid().getKey();
                if (key == null) {
                    throw new InvalidRecipeComponentValueException(
                        "fluid ingredient must have a fluid key",
                        this,
                        from
                    ).source(cx.recipe().sourceLine);
                }
                yield new FluidIngredient().withContent(key);
            }
            case TagFluidIngredient tag -> new FluidIngredient().withContent(tag.tag());
            default -> throw new InvalidRecipeComponentValueException(
                "unknown fluid ingredient type: " + fluidIngredient.getClass().getName(),
                this,
                from
            ).source(cx.recipe().sourceLine);
        };
        return converted.withAmount(amount);
    }

    @Override
    public boolean allowEmpty() {
        return true;
    }

    @Override
    public boolean isEmpty(FluidIngredient value) {
        return value.isEmpty();
    }
}
