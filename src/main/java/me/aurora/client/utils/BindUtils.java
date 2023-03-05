package me.aurora.client.utils;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.HashMap;

/**
 * @author Gabagool
 * BindUtils
 */
public class BindUtils {
    private static HashMap<String, KeyBinding> bindingHashMap = new HashMap<>();
    public static class Bind{
        private final int bindKey;
        private final String bindName;
        public Bind(int bindKey, String bindName){
            this.bindKey = bindKey;
            this.bindName = bindName;
        }
        public int getBindKey() {
            return bindKey;
        }
        public String getBindName() {
            return bindName;
        }
    }
    public static void registerBinds(Bind... binds){
        for (Bind bind : binds) {
            bindingHashMap.put(bind.getBindName(), new KeyBinding(bind.getBindName(), bind.getBindKey(), "Aurora"));
            ClientRegistry.registerKeyBinding(bindingHashMap.get(bind.getBindName()));
        }
    }
    public static boolean isBindPressed(String bindName){
        return bindingHashMap.get(bindName).isPressed();
    }
    public static boolean isBindDown(String bindName){
        return bindingHashMap.get(bindName).isKeyDown();
    }
}
