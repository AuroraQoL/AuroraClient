package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.DailyDungeonsMessages;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MelodyThrottle {

    static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(Config.MelodyThrottle && event.type == 0) {
            String message = event.message.getFormattedText().replaceAll("\u00a7.", "");
            if(message.contains("This menu has been throttled!")) {
                mc.thePlayer.sendChatMessage("/pc i am being throttled");
                DailyDungeonsMessages.sendModMessage("Melody Is Being Throttled");
            }
        }
    }
}
