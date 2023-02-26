package me.aurora.client.utils;

import static me.aurora.client.Aurora.mc;

/**
 * IMPLEMENTED FROM SHADYADDONS
 * @author jxee
 */
public class KeyUtils {

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
