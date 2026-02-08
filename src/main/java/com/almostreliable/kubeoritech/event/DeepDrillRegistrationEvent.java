package com.almostreliable.kubeoritech.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import dev.latvian.mods.kubejs.error.KubeRuntimeException;
import dev.latvian.mods.kubejs.event.EventResult;
import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.script.SourceLine;
import dev.latvian.mods.rhino.Context;
import io.wispforest.owo.serialization.CodecUtils;
import rearth.oritech.Oritech;
import rearth.oritech.init.TagContent;
import rearth.oritech.init.recipes.OritechRecipe;
import rearth.oritech.init.recipes.OritechRecipeType;
import rearth.oritech.init.recipes.RecipeContent;
import rearth.oritech.util.FluidIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class DeepDrillRegistrationEvent implements KubeEvent {

    private static final Map<ResourceLocation, JsonElement> RECIPES = new HashMap<>();

    private final BiConsumer<ResourceLocation, JsonElement> jsonConsumer;
    private final List<String> inputBlocks = new ArrayList<>();

    public DeepDrillRegistrationEvent(BiConsumer<ResourceLocation, JsonElement> jsonConsumer) {
        this.jsonConsumer = jsonConsumer;
        RECIPES.clear(); // workaround because entry point KubePlugin#generateData is fired twice
    }

    public void add(Context ctx, Block inputBlock, ItemStack outputItem, int time, ResourceLocation id) {
        if (RECIPES.containsKey(id)) {
            throw new KubeRuntimeException("recipe id '" + id + "' already registered").source(SourceLine.of(ctx));
        }

        var oritechRecipe = new OritechRecipe(
            time,
            List.of(Ingredient.of(inputBlock)),
            List.of(outputItem),
            RecipeContent.DEEP_DRILL,
            FluidIngredient.EMPTY,
            List.of()
        );

        var encodeResult = CodecUtils.toCodec(OritechRecipeType.ORI_RECIPE_ENDEC).encode(oritechRecipe, JsonOps.INSTANCE, new JsonObject());
        if (encodeResult.isError()) {
            throw new KubeRuntimeException("could not serialize deep drill recipe").source(SourceLine.of(ctx));
        }

        var recipeJson = encodeResult.getOrThrow();
        RECIPES.put(id, recipeJson);
        inputBlocks.add(inputBlock.kjs$getId());
    }

    public void add(Context ctx, Block inputBlock, ItemStack outputItem, ResourceLocation id) {
        add(ctx, inputBlock, outputItem, 60, id);
    }

    @Override
    public void afterPosted(EventResult result) {
        var array = new JsonArray();
        for (var inputBlock : inputBlocks) {
            array.add(inputBlock);
        }
        JsonObject json = new JsonObject();
        json.add("values", array);

        var tag = TagContent.RESOURCE_NODES.location();
        var tagId = Oritech.id("tags/block/" + tag.getPath());
        jsonConsumer.accept(tagId, json);

        inputBlocks.clear();
    }

    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public static void onInjectRecipes(BiConsumer<ResourceLocation, JsonElement> jsonConsumer) {
        RECIPES.forEach(jsonConsumer);
        RECIPES.clear();
    }
}
