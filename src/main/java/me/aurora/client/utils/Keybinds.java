package me.aurora.client.utils;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Keybinds {
    public static KeyBinding GhostBlocks;
    public static KeyBinding AutoSellBazaar;

    public static void register() {
        GhostBlocks = new KeyBinding("GhostBlocks", Keyboard.KEY_G, "Aurora Client");
        AutoSellBazaar = new KeyBinding("AutoSellBazaar", Keyboard.KEY_NONE, "Aurora Client");

        ClientRegistry.registerKeyBinding(GhostBlocks);
        ClientRegistry.registerKeyBinding(AutoSellBazaar);
    }
}
