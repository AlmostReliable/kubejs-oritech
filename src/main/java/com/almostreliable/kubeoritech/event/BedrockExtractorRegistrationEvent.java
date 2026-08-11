package com.almostreliable.kubeoritech.event;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.ModifyRecipeJsonsEvent;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import dev.latvian.mods.kubejs.error.KubeRuntimeException;
import dev.latvian.mods.kubejs.event.EventResult;
import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.script.SourceLine;
import dev.latvian.mods.rhino.Context;
import rearth.oritech.Oritech;
import rearth.oritech.init.TagContent;
import rearth.oritech.init.recipes.OritechRecipe;
import rearth.oritech.init.recipes.RecipeContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class BedrockExtractorRegistrationEvent implements KubeEvent {

    private static final Map<Identifier, JsonElement> RECIPES = new HashMap<>();

    private final BiConsumer<Identifier, JsonElement> resourceJsonConsumer;
    private final List<String> inputBlocks = new ArrayList<>();

    public BedrockExtractorRegistrationEvent(BiConsumer<Identifier, JsonElement> resourceJsonConsumer) {
        this.resourceJsonConsumer = resourceJsonConsumer;
        RECIPES.clear(); // workaround because entry point KubePlugin#generateData is fired twice
    }

    public void add(Context ctx, Block inputBlock, Item outputItem, int time, Identifier id) {
        if (RECIPES.containsKey(id)) {
            throw new KubeRuntimeException("recipe id '" + id + "' already registered").source(SourceLine.of(ctx));
        }

        var oritechRecipe = new OritechRecipe(
            List.of(Ingredient.of(inputBlock)),
            List.of(new ItemStackTemplate(outputItem)),
            Optional.empty(),
            List.of(),
            time,
            RecipeContent.BEDROCK_EXTRACTOR.get()
        );

        var encodeResult = Recipe.CODEC.encode(oritechRecipe, JsonOps.INSTANCE, new JsonObject());
        if (encodeResult.isError()) {
            throw new KubeRuntimeException("could not serialize bedrock extractor recipe").source(SourceLine.of(ctx));
        }

        var recipeJson = encodeResult.getOrThrow();
        RECIPES.put(id, recipeJson);
        inputBlocks.add(inputBlock.kjs$getId());
    }

    public void add(Context ctx, Block inputBlock, Item outputItem, Identifier id) {
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
        resourceJsonConsumer.accept(tagId, json);

        inputBlocks.clear();
    }

    public static void onRecipeJsonEvent(ModifyRecipeJsonsEvent event) {
        event.getRecipeJsons().putAll(RECIPES);
        RECIPES.clear();
    }
}
