package me.aurora.client.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.HashMap;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * @brief Binding Utils
 */
public class BindUtils {
    private static final HashMap<String, KeyBinding> bindMap = new HashMap<>();
    @AllArgsConstructor @Getter
    public static class Bind {
        private final int bindKey;
        private final String bindName;
    }
    public static void registerBinds(Bind... binds){
        for (Bind bind : binds) {
            String bindName = bind.getBindName();
            bindMap.put(bindName, new KeyBinding(bindName, bind.getBindKey(), "Aurora"));
            ClientRegistry.registerKeyBinding(bindMap.get(bindName));
        }
    }
    public static boolean isBindPressed(String bindName){
        return bindMap.get(bindName).isPressed();
    }
    public static boolean isBindDown(String bindName){
        return bindMap.get(bindName).isKeyDown();
    }
}
