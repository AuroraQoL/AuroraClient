package me.aurora.client.krypton;

import java.util.ArrayList;

public class KeyBindHandler {
    public static int getKeyCode(ArrayList<String> Script) {
        return Integer.parseInt(Script.get(Script.indexOf("KEYBIND:") + 1));
    }
}
