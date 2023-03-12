package me.aurora.client.utils;

import me.aurora.client.Aurora;
import net.minecraft.util.ChatComponentText;

public class ClientMessages {

    public static void sendClientMessage(String message) {
        Aurora.mc.thePlayer.addChatMessage(new ChatComponentText( "\2479\247lAURORA \2477| \247r" + message.replace("&", "\247")));
    }

    public static void sendMultilineClientMessage(String... messages){
        for (String message : messages) {
            sendClientMessage(message);
        }
    }
}
