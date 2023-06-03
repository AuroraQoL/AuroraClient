package me.aurora.client.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * IMPLEMENTED FROM SHADYADDONS
 *
 * @author jxee
 */
public class ReflectionUtils {

    public static boolean invoke(Object object, String methodName) {
        try {
            final Method method = object.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(object);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static Object field(Object object, String name) {
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception ignored) {
        }
        return null;
    }

}