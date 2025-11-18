package dev.petalcat.petaldisconnect;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SettingsScreenGenerator {

    private static final Identifier LOGO =
            Identifier.of("petaldisconnect", "icon.png");

    public Screen create(Screen parent) {
        return new ConfigScreen(parent);
    }

    private class ConfigScreen extends Screen {
        private final Screen parent;
        private final PetalConfig config = PetalClient.CONFIG;

        protected ConfigScreen(Screen parent) {
            super(Text.literal("PetalDisconnect Settings"));
            this.parent = parent;
        }

        @Override
        protected void init() {
            int center = width / 2;
            int y = 100;

            addDrawableChild(
                    CyclingButtonWidget.onOffBuilder(config.enableQuickDisconnect)
                            .build(center - 100, y, 200, 20,
                                    Text.literal("Enable Quick Disconnect"),
                                    (btn, value) -> {
                                        config.enableQuickDisconnect = value;
                                        config.save();
                                    })
            );

            y += 25;

            addDrawableChild(
                    CyclingButtonWidget.onOffBuilder(config.enableFocusCheck)
                            .build(center - 100, y, 200, 20,
                                    Text.literal("Disconnect On Focus Loss"),
                                    (btn, value) -> {
                                        config.enableFocusCheck = value;
                                        config.save();
                                    })
            );

            y += 25;

            addDrawableChild(
                    CyclingButtonWidget.onOffBuilder(config.enableAutoReconnect)
                            .build(center - 100, y, 200, 20,
                                    Text.literal("Auto Reconnect"),
                                    (btn, value) -> {
                                        config.enableAutoReconnect = value;
                                        config.save();
                                    })
            );

            y += 35;

            addDrawableChild(
                    ButtonWidget.builder(Text.literal("Done"), b -> close())
                            .dimensions(center - 100, y, 200, 20)
                            .build()
            );
        }

        @Override
        public void close() {
            client.setScreen(parent);
        }





        @Override
        public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
//            this.(ctx, 0, 0, this.width, this.height, 0xC0101010, 0xD0101010);

            int size = 64;
            int x = this.width / 2 - size / 2;
            int y = 20;

            // CORRECT for Minecraft 1.21.10
            ctx.drawTexturedQuad(
                    LOGO,
                    x, y,
                    x + size, y + size,
                    0f, 1f,
                    0f, 1f
            );

            super.render(ctx, mouseX, mouseY, delta);
        }
    }
}