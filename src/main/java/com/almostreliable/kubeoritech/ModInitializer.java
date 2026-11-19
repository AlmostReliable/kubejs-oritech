package com.almostreliable.kubeoritech;

import com.almostreliable.kubeoritech.event.BedrockExtractorRegistrationEvent;
import com.almostreliable.kubeoritech.event.particle.state.ParticleImpactEvent;

import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;
import rearth.oritech.api.accelerator.ParticleImpactBehaviors;

@SuppressWarnings({"UtilityClassWithPublicConstructor", "StaticNonFinalField", "NonConstantFieldWithUpperCaseName"})
@Mod(ModConstants.MOD_ID)
public final class ModInitializer {

    public static boolean NETHER_PORTAL_ENABLED = true;
    public static boolean END_PORTAL_ENABLED = true;

    private static final int PARTICLE_IMPACT_PRIORITY = 1000; // higher than default behavior so scripts can override
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModInitializer(IEventBus modEventBus) {
        LOGGER.info("Loading Oritech integration for KubeJS.");
        modEventBus.addListener(ModInitializer::onCommonSetup);
        NeoForge.EVENT_BUS.addListener(BedrockExtractorRegistrationEvent::onRecipeJsonEvent);
    }

    private static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> ParticleImpactBehaviors.register(
            Identifier.fromNamespaceAndPath(ModConstants.MOD_ID, "script_impact"),
            PARTICLE_IMPACT_PRIORITY,
            ParticleImpactEvent::onImpact
        ));
    }
}
