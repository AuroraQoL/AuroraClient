package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.MessageUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.aurora.client.Aurora.mc;

public class MelodyThrottle  implements Module {
    public String name() {
        return "MelodyThrottle";
    }

    public boolean toggled() {
        return Config.melodyThrottle;
    }
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(Config.melodyThrottle && event.type == 0) {
            String message = event.message.getFormattedText().replaceAll("\u00a7.", "");
            if(message.contains("This menu has been throttled!")) {
                mc.thePlayer.sendChatMessage("/pc i am being throttled");
                MessageUtils.sendClientMessage("Melody Is Being Throttled");
            }
        }
    }
}
