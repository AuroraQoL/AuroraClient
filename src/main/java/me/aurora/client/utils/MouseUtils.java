package me.aurora.client.utils;

import static me.aurora.client.Aurora.mc;

/**
 * IMPLEMENTED FROM SHADYADDONS
 * @author jxee
 */
public class MouseUtils {

    public enum ClickType {
        LEFT, MIDDLE, RIGHT
    }

    public static void click(ClickType click){
        switch (click) {
            case LEFT:
                tryInvoke("func_147116_af", "clickMouse");
                break;
            case MIDDLE:
                tryInvoke("func_147112_ai", "middleClickMouse");
                break;
            case RIGHT:
                tryInvoke("func_147121_ag", "rightClickMouse");
                break;
        }
    }

    @Deprecated
    public static void rightClick() {
        tryInvoke("func_147121_ag", "rightClickMouse");
    }

    @Deprecated
    public static void leftClick() {
        tryInvoke("func_147116_af", "clickMouse");
    }

    @Deprecated
    public static void middleClick() {
        tryInvoke("func_147112_ai", "middleClickMouse");
    }

    private static void tryInvoke(String obfName, String normalName){
        if(!ReflectionUtils.invoke(mc, obfName))
            ReflectionUtils.invoke(mc, normalName);
    }

}
