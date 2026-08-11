package com.almostreliable.kubeoritech;

import com.almostreliable.kubeoritech.event.BedrockExtractorRegistrationEvent;
import com.almostreliable.kubeoritech.event.SoulCollectionEvent;
import com.almostreliable.kubeoritech.event.particle.state.ParticleCollidedEvent;
import com.almostreliable.kubeoritech.event.particle.state.ParticleExitedEvent;
import com.almostreliable.kubeoritech.event.particle.state.ParticleInjectedEvent;
import com.almostreliable.kubeoritech.recipe.generator.BioGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.generator.FuelGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.generator.LavaGeneratorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.generator.NuclearReactorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.AssemblerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.AtomicForgeKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.BedrockExtractorKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.CentrifugeFluidKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.CentrifugeKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.FoundryKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.GrinderKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.IndustrialChillerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.LaserKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.ParticleCollisionKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.PulverizerKubeRecipe;
import com.almostreliable.kubeoritech.recipe.machine.RefineryKubeRecipe;
import com.almostreliable.kubeoritech.schema.OritechRecipeSchema;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.generator.KubeDataGenerator;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.KubeRecipeFactory;
import dev.latvian.mods.kubejs.recipe.schema.RecipeFactoryRegistry;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;

import java.util.List;
import java.util.function.Supplier;

public class KubePlugin implements KubeJSPlugin {

    private static final Supplier<List<KubeRecipeFactory>> FACTORIES = () -> List.of(
        PulverizerKubeRecipe.FACTORY,
        GrinderKubeRecipe.FACTORY,
        AssemblerKubeRecipe.FACTORY,
        RefineryKubeRecipe.FACTORY,
        FoundryKubeRecipe.FACTORY,
        CentrifugeKubeRecipe.FACTORY,
        CentrifugeFluidKubeRecipe.FACTORY,
        AtomicForgeKubeRecipe.FACTORY,
        BioGeneratorKubeRecipe.FACTORY,
        FuelGeneratorKubeRecipe.FACTORY,
        LavaGeneratorKubeRecipe.FACTORY,
        BedrockExtractorKubeRecipe.FACTORY,
        ParticleCollisionKubeRecipe.FACTORY,
        IndustrialChillerKubeRecipe.FACTORY,
        NuclearReactorKubeRecipe.FACTORY,
        LaserKubeRecipe.FACTORY
    );

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(Events.GROUP);
    }

    @Override
    public void registerRecipeFactories(RecipeFactoryRegistry registry) {
        for (var factory : FACTORIES.get()) {
            registry.register(factory);
        }
    }

    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry registry) {
        for (var factory : FACTORIES.get()) {
            registry.register(factory.id(), OritechRecipeSchema.of(factory));
        }
    }

    @Override
    public void generateData(KubeDataGenerator generator) {
        if (Events.BEDROCK_EXTRACTOR.hasListeners()) {
            var event = new BedrockExtractorRegistrationEvent(generator::json);
            Events.BEDROCK_EXTRACTOR.post(event);
        }
    }

    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public interface Events {

        EventGroup GROUP = EventGroup.of("OritechEvents");

        EventHandler PARTICLE_INJECTED = GROUP.server("particleInjected", () -> ParticleInjectedEvent.class).hasResult();
        EventHandler PARTICLE_COLLIDED = GROUP.server("particleCollided", () -> ParticleCollidedEvent.class).hasResult();
        EventHandler PARTICLE_EXITED = GROUP.server("particleExited", () -> ParticleExitedEvent.class);

        EventHandler BEDROCK_EXTRACTOR = GROUP.server("bedrockExtractorRegistration", () -> BedrockExtractorRegistrationEvent.class);
        EventHandler SOUL_COLLECTION = GROUP.server("soulCollection", () -> SoulCollectionEvent.class).hasResult();
    }
}
