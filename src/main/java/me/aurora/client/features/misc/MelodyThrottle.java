package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ClientMessages;
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
                ClientMessages.sendClientMessage("Melody Is Being Throttled");
            }
        }
    }
}
