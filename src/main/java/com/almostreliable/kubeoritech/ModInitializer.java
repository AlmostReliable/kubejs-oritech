package com.almostreliable.kubeoritech;

import com.almostreliable.kubejs_oritech.ModConstants;

import net.neoforged.fml.common.Mod;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod(ModConstants.MOD_ID)
public final class ModInitializer {

    private static final Logger LOGGER = LogUtils.getLogger();

    public ModInitializer() {
        LOGGER.info("Loading Oritech integration for KubeJS.");
    }
}
