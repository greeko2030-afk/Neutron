package com.neutron;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.neutron.config.NeutronConfig;

public class Neutron implements ModInitializer {
    public static final String MOD_ID = "neutron";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[Neutron] Initializing core optimization systems...");
        NeutronConfig.load();
        LOGGER.info("[Neutron] Core engines initialized successfully.");
    }
}
