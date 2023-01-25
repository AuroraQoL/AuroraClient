package me.aurora.client.utils;

//FROM SHADY ADDONS

public class ThreadUtils {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ignored) {}
    }

}
