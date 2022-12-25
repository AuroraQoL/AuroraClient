package me.dailydungeons.client.utils;

public class DelayUtils {

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ignored) {}
    }

}
