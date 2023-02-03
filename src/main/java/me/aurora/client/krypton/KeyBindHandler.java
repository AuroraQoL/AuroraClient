package me.aurora.client.krypton;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KeyBindHandler {
    public static int getKeyCode(ArrayList<String> Script){
        return Integer.parseInt(Script.get(Script.indexOf("KEYBIND:")+1));
    }
}
