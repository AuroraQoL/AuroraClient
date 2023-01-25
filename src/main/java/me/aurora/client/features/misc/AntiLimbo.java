package me.aurora.client.features.misc;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.ClientMessages;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiLimbo {
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(Config.antiLimbo && event.type == 0) {
            String message = event.message.getFormattedText().replaceAll("[§|&][0-9,a-f,k-o,r]", "");
            if(message.contains("You are playing on profile")) {
                Aurora.mc.thePlayer.sendChatMessage("/is");
                ClientMessages.sendClientMessage("Warping back to island");
            }
        }
    }
}
