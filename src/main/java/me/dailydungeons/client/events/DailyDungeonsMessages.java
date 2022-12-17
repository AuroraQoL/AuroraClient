package me.dailydungeons.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class DailyDungeonsMessages {

    static Minecraft mc = Minecraft.getMinecraft();

    public static void sendMessage(String message) {
        if(mc.thePlayer != null && mc.theWorld != null) {
            if(!message.contains("§")) {
                message = message.replace("&", "§");
            }
            mc.thePlayer.addChatMessage(new ChatComponentText(message));
        }
    }
    public static void sendModMessage(String message) {
        if(message.contains("§")) {
            sendMessage("\2479\247lAURORA \2477| \247r" + message);
        } else {
            sendMessage("\2479\247lAURORA \2477| \247r" + message);
        }
    }
}
