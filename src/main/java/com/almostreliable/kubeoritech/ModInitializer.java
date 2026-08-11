package com.almostreliable.kubeoritech;

import com.almostreliable.kubeoritech.event.BedrockExtractorRegistrationEvent;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@SuppressWarnings({"UtilityClassWithPublicConstructor", "StaticNonFinalField", "NonConstantFieldWithUpperCaseName"})
@Mod(ModConstants.MOD_ID)
public final class ModInitializer {

    public static boolean NETHER_PORTAL_ENABLED = true;
    public static boolean END_PORTAL_ENABLED = true;

    private static final Logger LOGGER = LogUtils.getLogger();

    public ModInitializer() {
        LOGGER.info("Loading Oritech integration for KubeJS.");
        NeoForge.EVENT_BUS.addListener(BedrockExtractorRegistrationEvent::onRecipeJsonEvent);
    }
}
