package dev.petalcat.petaldisconnect;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;

public class PetalFocusWatcher {

    private static boolean disconnectOnFocusLoss = false;
    private static boolean autoReconnect = false;

    private static boolean lastFocused = true;

    private static ServerInfo lastServer = null;

    private static DisconnectReason lastReason = DisconnectReason.NONE;

    // Set to true right before we call client.disconnect because of focus loss
    private static boolean disconnectRequestedByFocus = false;

    private static void log(String msg) {
        System.out.println("[PetalDisconnect] " + msg);
    }

    public static void init(PetalConfig config) {
        disconnectOnFocusLoss = config.enableFocusCheck;
        autoReconnect = config.enableAutoReconnect;

        log("FocusWatcher initialized. focus=" + disconnectOnFocusLoss + " autoreconnect=" + autoReconnect);

        // Fires when the client disconnects from a server
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            // Classify this disconnect based on whether WE requested it
            if (disconnectRequestedByFocus) {
                lastReason = DisconnectReason.FOCUS_LOSS;
                log("DISCONNECT event fired. Classified as FOCUS_LOSS.");
            } else {
                lastReason = DisconnectReason.SERVER;
                log("DISCONNECT event fired. Classified as SERVER.");
            }

            // Reset the flag for next time
            disconnectRequestedByFocus = false;
        });

        // Per-tick focus watcher
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null) return;

            boolean focused = client.isWindowFocused();
            ClientWorld world = client.world;

            // Track last server while connected
            if (world != null && client.getCurrentServerEntry() != null) {
                lastServer = client.getCurrentServerEntry();
            }

            // Focus lost
            if (lastFocused && !focused) {
                log("Focus LOST");

                if (world != null) {
                    if (disconnectOnFocusLoss) {
                        log("Disconnecting due to focus loss");
                        disconnectRequestedByFocus = true;
                        client.disconnect(Text.literal("Disconnected due to focus loss"));
                    } else {
                        log("Focus loss ignored due to config");
                    }
                }
            }

            // Focus regained
            if (!lastFocused && focused) {
                log("Focus GAINED. lastReason=" + lastReason);

                // Only auto-reconnect if the *last* disconnect was our focus one
                if (autoReconnect && lastReason == DisconnectReason.FOCUS_LOSS) {
                    // Also make sure we're actually disconnected (world == null)
                    if (client.world == null) {
                        log("Reconnecting because lastReason=FOCUS_LOSS");
                        reconnectToLastServer(client);
                    } else {
                        log("World is still loaded on focus regain; not reconnecting yet.");
                    }
                } else {
                    log("NOT reconnecting. autoReconnect=" + autoReconnect +
                            " lastReason=" + lastReason);
                }

                // Clear the reason so unrelated future focus changes don't misfire
                lastReason = DisconnectReason.NONE;
            }

            lastFocused = focused;
        });
    }

    private static void reconnectToLastServer(MinecraftClient client) {
        if (lastServer == null) {
            log("Cannot reconnect: lastServer = null");
            return;
        }

        log("Reconnecting to " + lastServer.address);

        ServerAddress address = ServerAddress.parse(lastServer.address);

        client.execute(() -> {
            client.setScreen(null);

            ConnectScreen.connect(
                    null,
                    client,
                    address,
                    lastServer,
                    false,
                    null
            );
        });
    }

    public static void updateConfig(PetalConfig config) {
        disconnectOnFocusLoss = config.enableFocusCheck;
        autoReconnect = config.enableAutoReconnect;

        log("Config updated. focus=" + disconnectOnFocusLoss + " autoreconnect=" + autoReconnect);
    }
}