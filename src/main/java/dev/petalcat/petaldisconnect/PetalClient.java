package dev.petalcat.petaldisconnect;

import net.fabricmc.api.ClientModInitializer;

public class PetalClient implements ClientModInitializer {

    public static PetalConfig CONFIG;

    @Override
    public void onInitializeClient() {
        System.out.println("[PetalDisconnect] Client initialized.");

        // 1. Register keybinds (required or they DO NOT WORK)
        PetalKeybinds.register();

        // 2. Load config
        CONFIG = PetalConfig.load();

        // 3. Start watcher logic
        PetalFocusWatcher.init(CONFIG);
    }

    public static void reloadConfig() {
        CONFIG = PetalConfig.load();
        PetalFocusWatcher.updateConfig(CONFIG);
    }
}