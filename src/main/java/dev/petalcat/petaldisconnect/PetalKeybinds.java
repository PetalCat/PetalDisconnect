package dev.petalcat.petaldisconnect;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import org.lwjgl.glfw.GLFW;

public class PetalKeybinds {

    public static KeyBinding QUICK_DISCONNECT;

    public static void register() {

        KeyBinding.Category CATEGORY =
                KeyBinding.Category.create(
                        Identifier.of("petaldisconnect", "category")
                );

        QUICK_DISCONNECT = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.petaldisconnect.quick_disconnect",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_Y,
                        CATEGORY
                )
        );
    }
}