package com.almostreliable.kubeoritech;

import com.almostreliable.kubeoritech.component.ArchFluidStackComponent;
import com.almostreliable.kubeoritech.component.FluidIngredientComponent;
import com.almostreliable.kubeoritech.event.DeepDrillRegistrationEvent;
import com.almostreliable.kubeoritech.event.SoulCollectionEvent;
import com.almostreliable.kubeoritech.event.particle.state.ParticleCollidedEvent;
import com.almostreliable.kubeoritech.event.particle.state.ParticleExitedEvent;
import com.almostreliable.kubeoritech.event.particle.state.ParticleInjectedEvent;
import com.almostreliable.kubeoritech.recipe.generator.BioGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.generator.FuelGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.generator.LavaGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.generator.ReactorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.AssemblerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.AtomicForgeKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.CentrifugeFluidKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.CentrifugeKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.CoolerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.DeepDrillKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.FoundryKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.GrinderKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.LaserKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.ParticleCollisionKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.PulverizerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.RefineryKubeRecipe;
import com.almostreliable.kubeoritech.schema.OritechRecipeSchema;

import net.minecraft.Util;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.generator.KubeDataGenerator;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentTypeRegistry;
import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import dev.latvian.mods.kubejs.recipe.schema.RecipeFactoryRegistry;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import rearth.oritech.init.recipes.OritechRecipeType;
import rearth.oritech.init.recipes.RecipeContent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class KubePlugin implements KubeJSPlugin {

    private static final Supplier<Map<OritechRecipeType, KubeRecipeFactory>> RECIPES = () -> Util.make(() -> {
        var recipes = new HashMap<OritechRecipeType, KubeRecipeFactory>();
        recipes.put(RecipeContent.PULVERIZER, PulverizerKubeRecipe.FACTORY);
        recipes.put(RecipeContent.GRINDER, GrinderKubeRecipe.FACTORY);
        recipes.put(RecipeContent.ASSEMBLER, AssemblerKubeRecipe.FACTORY);
        recipes.put(RecipeContent.REFINERY, RefineryKubeRecipe.FACTORY);
        recipes.put(RecipeContent.FOUNDRY, FoundryKubeRecipe.FACTORY);
        recipes.put(RecipeContent.CENTRIFUGE, CentrifugeKubeRecipe.FACTORY);
        recipes.put(RecipeContent.CENTRIFUGE_FLUID, CentrifugeFluidKubeRecipe.FACTORY);
        recipes.put(RecipeContent.ATOMIC_FORGE, AtomicForgeKubeRecipe.FACTORY);
        recipes.put(RecipeContent.BIO_GENERATOR, BioGeneratorKubeRecipe.FACTORY);
        recipes.put(RecipeContent.FUEL_GENERATOR, FuelGeneratorKubeRecipe.FACTORY);
        recipes.put(RecipeContent.LAVA_GENERATOR, LavaGeneratorKubeRecipe.FACTORY);
        recipes.put(RecipeContent.DEEP_DRILL, DeepDrillKubeRecipe.FACTORY);
        recipes.put(RecipeContent.PARTICLE_COLLISION, ParticleCollisionKubeRecipe.FACTORY);
        recipes.put(RecipeContent.COOLER, CoolerKubeRecipe.FACTORY);
        recipes.put(RecipeContent.REACTOR, ReactorKubeRecipe.FACTORY);
        recipes.put(RecipeContent.LASER, LaserKubeRecipe.FACTORY);
        return recipes;
    });

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
        for (var factory : RECIPES.get().values()) {
            registry.register(factory);
        }
    }

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        for (var entry : RECIPES.get().entrySet()) {
            var recipeType = entry.getKey();
            var kubeRecipeFactory = entry.getValue();
            var id = recipeType.getIdentifier();
            registry.register(id, OritechRecipeSchema.of(kubeRecipeFactory));
        }
    }

    @Override
    public void generateData(KubeDataGenerator generator) {
        if (Events.DEEP_DRILL.hasListeners()) {
            var event = new DeepDrillRegistrationEvent(generator::json);
            Events.DEEP_DRILL.post(event);
        }
    }

    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public interface Events {

        EventGroup GROUP = EventGroup.of("OritechEvents");

        EventHandler PARTICLE_INJECTED = GROUP.server("particleInjected", () -> ParticleInjectedEvent.class).hasResult();
        EventHandler PARTICLE_COLLIDED = GROUP.server("particleCollided", () -> ParticleCollidedEvent.class).hasResult();
        EventHandler PARTICLE_EXITED = GROUP.server("particleExited", () -> ParticleExitedEvent.class);

        EventHandler DEEP_DRILL = GROUP.server("deepDrillRegistration", () -> DeepDrillRegistrationEvent.class);
        EventHandler SOUL_COLLECTION = GROUP.server("soulCollection", () -> SoulCollectionEvent.class).hasResult();
    }
}
