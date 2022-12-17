package me.dailydungeons.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.util.HashMap;

public class KeybindUtils {

    static Minecraft mc = Minecraft.getMinecraft();

    public static HashMap<String, KeyBinding> keyBindings = new HashMap<>();

    public static boolean isPressed(String name) {
        return get(name).isPressed();
    }

    public static KeyBinding get(String name) {
        return keyBindings.get(name);
    }

    public static void rightClick() {
        if(!ReflectionUtils.invoke(mc, "func_147121_ag")) {
            ReflectionUtils.invoke(mc, "rightClickMouse");
        }
    }

    public static void leftClick() {
        if(!ReflectionUtils.invoke(mc, "func_147116_af")) {
            ReflectionUtils.invoke(mc, "clickMouse");
        }
    }

    public static void middleClick() {
        if(!ReflectionUtils.invoke(mc, "func_147112_ai")) {
            ReflectionUtils.invoke(mc, "middleClickMouse");
        }
    }

}
