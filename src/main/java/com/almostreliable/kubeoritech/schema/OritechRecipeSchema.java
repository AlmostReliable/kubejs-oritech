package com.almostreliable.kubeoritech.schema;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;

import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.FluidStackComponent;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.ItemStackComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.SizedFluidIngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.util.IntBounds;
import rearth.oritech.Oritech;
import rearth.oritech.init.recipes.OritechRecipe;
import rearth.oritech.init.recipes.RecipeContent;

import java.util.List;

/**
 * See {@link OritechRecipe} and {@link RecipeContent}.
 */
public abstract class OritechRecipeSchema {

    public static final RecipeKey<List<Ingredient>> ITEM_INPUTS = IngredientComponent.INGREDIENT
        .asList()
        .withBounds(IntBounds.OPTIONAL)
        .key("itemInputs", ComponentRole.INPUT)
        .functionNames(List.of("itemInputs", "ingredients", "inputs", "itemInput", "ingredient", "input"))
        .optional(List.of())
        .alwaysWrite()
        .exclude();
    public static final RecipeKey<List<ItemStack>> ITEM_OUTPUTS = ItemStackComponent.ITEM_STACK
        .asList()
        .withBounds(IntBounds.OPTIONAL)
        .key("itemResults", ComponentRole.OUTPUT)
        .functionNames(List.of("itemOutputs", "results", "outputs", "itemOutput", "result", "output", "itemResult", "itemResults"))
        .optional(List.of())
        .alwaysWrite()
        .exclude();
    public static final RecipeKey<SizedFluidIngredient> FLUID_INPUT = SizedFluidIngredientComponent.SIZED_FLUID_INGREDIENT
        .key("fluidInput", ComponentRole.INPUT)
        .functionNames(List.of("fluidInput", "fluidIngredient", "fluid", "fluidInputs", "fluidIngredients", "fluids"))
        .optional((SizedFluidIngredient) null)
        .exclude();
    public static final RecipeKey<List<FluidStack>> FLUID_OUTPUTS = FluidStackComponent.FLUID_STACK
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
    // Oritech has changed to using oritech:machine_recipe as the "type" identifier,
    // and passing in a "recipeType" identifier for the actual machines
    // This is just adding "recipeType" to the schema here, the value will be injected
    // in {@link OritechKubeRecipe}
    public static final RecipeKey<String> RECIPE_TYPE = StringComponent.ID
        .key("recipeType", ComponentRole.OTHER)
        .defaultOptional()
        .alwaysWrite()
        .exclude();

    public static RecipeSchema of(KubeRecipeFactory recipeFactory) {
        return new RecipeSchema(ITEM_INPUTS, ITEM_OUTPUTS, FLUID_INPUT, FLUID_OUTPUTS, TIME, RECIPE_TYPE)
            .typeOverride(Oritech.id("machine_recipe")).factory(recipeFactory);
    }
}
