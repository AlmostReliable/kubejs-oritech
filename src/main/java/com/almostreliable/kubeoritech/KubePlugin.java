package com.almostreliable.kubeoritech;

import com.almostreliable.kubeoritech.component.ArchFluidStackComponent;
import com.almostreliable.kubeoritech.component.FluidIngredientComponent;
import com.almostreliable.kubeoritech.event.SoulCollectionEvent;
import com.almostreliable.kubeoritech.recipe.AssemblerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.AtomicForgeKubeRecipe;
import com.almostreliable.kubeoritech.recipe.BioGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.CentrifugeFluidKubeRecipe;
import com.almostreliable.kubeoritech.recipe.CentrifugeKubeRecipe;
import com.almostreliable.kubeoritech.recipe.CoolerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.DeepDrillKubeRecipe;
import com.almostreliable.kubeoritech.recipe.FoundryKubeRecipe;
import com.almostreliable.kubeoritech.recipe.FuelGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.GrinderKubeRecipe;
import com.almostreliable.kubeoritech.recipe.LaserKubeRecipe;
import com.almostreliable.kubeoritech.recipe.LavaGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.ParticleCollisionKubeRecipe;
import com.almostreliable.kubeoritech.recipe.PulverizerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.ReactorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.RefineryKubeRecipe;
import com.almostreliable.kubeoritech.schema.OritechRecipeSchema;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentTypeRegistry;
import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import dev.latvian.mods.kubejs.recipe.schema.RecipeFactoryRegistry;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import rearth.oritech.init.recipes.OritechRecipeType;
import rearth.oritech.init.recipes.RecipeContent;

import java.util.HashMap;
import java.util.Map;

public class KubePlugin implements KubeJSPlugin {

    private static final Map<OritechRecipeType, KubeRecipeFactory> RECIPES = new HashMap<>();

    static {
        RECIPES.put(RecipeContent.PULVERIZER, PulverizerKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.GRINDER, GrinderKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.ASSEMBLER, AssemblerKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.REFINERY, RefineryKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.FOUNDRY, FoundryKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.CENTRIFUGE, CentrifugeKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.CENTRIFUGE_FLUID, CentrifugeFluidKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.ATOMIC_FORGE, AtomicForgeKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.BIO_GENERATOR, BioGeneratorKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.FUEL_GENERATOR, FuelGeneratorKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.LAVA_GENERATOR, LavaGeneratorKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.DEEP_DRILL, DeepDrillKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.PARTICLE_COLLISION, ParticleCollisionKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.COOLER, CoolerKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.REACTOR, ReactorKubeRecipe.FACTORY);
        RECIPES.put(RecipeContent.LASER, LaserKubeRecipe.FACTORY);
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(Events.GROUP);
    }

    @Override
    public void registerRecipeComponents(RecipeComponentTypeRegistry registry) {
        registry.register(ArchFluidStackComponent.TYPE);
        registry.register(FluidIngredientComponent.TYPE);
    }

    @Override
    public void registerRecipeFactories(RecipeFactoryRegistry registry) {
        for (var factory : RECIPES.values()) {
            registry.register(factory);
        }
    }

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        for (var entry : RECIPES.entrySet()) {
            var recipeType = entry.getKey();
            var kubeRecipeFactory = entry.getValue();
            var id = recipeType.getIdentifier();
            registry.register(id, OritechRecipeSchema.of(kubeRecipeFactory));
        }
    }

    public interface Events {

        EventGroup GROUP = EventGroup.of("OritechEvents");
        EventHandler SOUL_COLLECTION = GROUP.server("soulCollection", () -> SoulCollectionEvent.class).hasResult();
    }
}
