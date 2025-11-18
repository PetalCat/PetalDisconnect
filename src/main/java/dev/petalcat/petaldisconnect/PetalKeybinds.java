package dev.petalcat.petaldisconnect;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.KeyBinding.Category;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class PetalKeybinds {

    public static KeyBinding QUICK_DISCONNECT;

    public static void register() {

        Category CATEGORY = Category.create(Identifier.of("petaldisconnect", "category"));

        QUICK_DISCONNECT = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.petaldisconnect.quick_disconnect",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_Y, // default key changed to Y
                        CATEGORY
                )
        );
    }
}