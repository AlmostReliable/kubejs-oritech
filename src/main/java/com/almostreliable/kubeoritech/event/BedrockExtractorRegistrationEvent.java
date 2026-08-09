package com.almostreliable.kubeoritech.event;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;
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

    private final BiConsumer<Identifier, JsonElement> jsonConsumer;
    private final List<String> inputBlocks = new ArrayList<>();

    public BedrockExtractorRegistrationEvent(BiConsumer<Identifier, JsonElement> jsonConsumer) {
        this.jsonConsumer = jsonConsumer;
        RECIPES.clear(); // workaround because entry point KubePlugin#generateData is fired twice
    }

    public void add(Context ctx, Block inputBlock, ItemStackTemplate outputItem, int time, Identifier id) {
        if (RECIPES.containsKey(id)) {
            throw new KubeRuntimeException("recipe id '" + id + "' already registered").source(SourceLine.of(ctx));
        }

        var oritechRecipe = new OritechRecipe(
            List.of(Ingredient.of(inputBlock)),
            List.of(outputItem),
            Optional.empty(),
            List.of(),
            time,
            RecipeContent.BEDROCK_EXTRACTOR.get()
        );

        var encodeResult = OritechRecipe.CODEC.codec().encode(oritechRecipe, JsonOps.INSTANCE, new JsonObject());
        if (encodeResult.isError()) {
            throw new KubeRuntimeException("could not serialize deep drill recipe").source(SourceLine.of(ctx));
        }

        var recipeJson = encodeResult.getOrThrow();
        RECIPES.put(id, recipeJson);
        inputBlocks.add(inputBlock.kjs$getId());
    }

    public void add(Context ctx, Block inputBlock, ItemStackTemplate outputItem, Identifier id) {
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
    public static void onInjectRecipes(BiConsumer<Identifier, JsonElement> jsonConsumer) {
        RECIPES.forEach(jsonConsumer);
        RECIPES.clear();
    }
}
