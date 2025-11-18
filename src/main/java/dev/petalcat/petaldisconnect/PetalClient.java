package dev.petalcat.petaldisconnect;

import net.fabricmc.api.ClientModInitializer;

public class PetalClient implements ClientModInitializer {

    public static PetalConfig CONFIG;

    @Override
    public void onInitializeClient() {
        System.out.println("[PetalDisconnect] Client initialized.");

        CONFIG = PetalConfig.load(); // however you load your config
        PetalFocusWatcher.init(CONFIG);
    }

    public static void reloadConfig() {
        CONFIG = PetalConfig.load();
        PetalFocusWatcher.updateConfig(CONFIG);
    }
}