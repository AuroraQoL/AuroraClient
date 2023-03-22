package me.aurora.client.features.misc;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.string.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author OctoSplash01 Gabagooooooooooool
 * @version 1.1
 * @brief AntiLimbo
 */
public class AntiLimbo  implements Module {
    public String name() {
        return "AntiLimbo";
    }

    public boolean toggled() {
        return Config.antiLimbo;
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(toggled() && event.type == 0 && StringUtils.removeFormatting(event.message.getFormattedText()).contains("You are playing on profile")) {
            Aurora.mc.thePlayer.sendChatMessage("/is");
            MessageUtils.sendClientMessage("Warping back to island");
        }
    }
}
