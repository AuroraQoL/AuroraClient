package me.aurora.client.utils;

import me.aurora.client.Aurora;
import net.minecraft.util.ChatComponentText;

import java.util.concurrent.CompletableFuture;

import static me.aurora.client.Aurora.mc;

public class MessageUtils {

    public static void sendClientMessage(String message) {
        Aurora.mc.thePlayer.addChatMessage(new ChatComponentText( "\2479\247lAURORA \2477| \247r" + message.replace("&", "\247")));
    }

    public static void sendMultilineClientMessage(String... messages){
        for (String message : messages) {
            sendClientMessage(message);
        }
    }
    public static void sendDelayedMultilinePlayerMessage(int delay, String... msgs){
        CompletableFuture.runAsync(() -> {
            for (String msg : msgs) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {
                } finally {
                    mc.thePlayer.sendChatMessage(msg);
                }
            }
        });
    }
}
