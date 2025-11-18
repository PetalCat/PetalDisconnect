package dev.petalcat.petaldisconnect;

import net.fabricmc.api.ModInitializer;
public class PetalDisconnect implements ModInitializer {
    public static DisconnectReason lastDisconnectReason = DisconnectReason.NONE;

    @Override
    public void onInitialize() {
        System.out.println("[PetalDisconnect] Mod initialized.");
    }
}