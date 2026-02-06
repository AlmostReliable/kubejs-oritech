package com.almostreliable.kubeoritech.schema;

import com.almostreliable.kubeoritech.component.ArchFluidStackComponent;
import com.almostreliable.kubeoritech.component.FluidIngredientComponent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import dev.architectury.fluid.FluidStack;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.util.IntBounds;
import rearth.oritech.init.recipes.OritechRecipe;
import rearth.oritech.init.recipes.OritechRecipeType;
import rearth.oritech.init.recipes.RecipeContent;
import rearth.oritech.util.FluidIngredient;

import java.util.List;

/**
 * See {@link OritechRecipe}, {@link OritechRecipeType}, and {@link RecipeContent}.
 */
public abstract class OritechRecipeSchema {

    public static final RecipeKey<List<Ingredient>> ITEM_INPUTS = IngredientComponent.INGREDIENT
        .instance()
        .asList()
        .withBounds(IntBounds.OPTIONAL)
        .key("ingredients", ComponentRole.INPUT)
        .functionNames(List.of("itemInputs", "ingredients", "inputs", "itemInput", "ingredient", "input"))
        .optional(List.of())
        .alwaysWrite()
        .exclude();
    public static final RecipeKey<List<ItemStack>> ITEM_OUTPUTS = ItemStackComponent.ITEM_STACK
        .instance()
        .asList()
        .withBounds(IntBounds.OPTIONAL)
        .key("results", ComponentRole.OUTPUT)
        .functionNames(List.of("itemOutputs", "results", "outputs", "itemOutput", "result", "output"))
        .optional(List.of())
        .alwaysWrite()
        .exclude();
    public static final RecipeKey<FluidIngredient> FLUID_INPUT = FluidIngredientComponent.TYPE
        .key("fluidInput", ComponentRole.INPUT)
        .functionNames(List.of("fluidInput", "fluidIngredient", "fluid", "fluidInputs", "fluidIngredients", "fluids"))
        .optional(FluidIngredient.EMPTY)
        .exclude();
    public static final RecipeKey<List<FluidStack>> FLUID_OUTPUTS = ArchFluidStackComponent.TYPE
        .instance()
        .asList()
        .withBounds(IntBounds.OPTIONAL)
        .key("fluidOutputs", ComponentRole.OUTPUT)
        .functionNames(List.of("fluidOutputs", "fluidResults", "fluidOutput", "fluidResult"))
        .optional(List.of())
        .exclude();
    public static final RecipeKey<Integer> TIME = NumberComponent.INT
        .key("time", ComponentRole.OTHER)
        .functionNames(List.of("time", "ticks", "timeInTicks", "duration"))
        .optional(60)
        .exclude();

    public static RecipeSchema of(KubeRecipeFactory recipeFactory) {
        return new RecipeSchema(ITEM_INPUTS, ITEM_OUTPUTS, FLUID_INPUT, FLUID_OUTPUTS, TIME).factory(recipeFactory);
    }
}
