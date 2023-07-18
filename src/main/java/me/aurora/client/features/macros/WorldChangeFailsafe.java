package me.aurora.client.features.macros;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldChangeFailsafe {

    @SubscribeEvent
    public void chat(ClientChatReceivedEvent event) {
        if(event.message.getUnformattedText().contains("Sending to server ") && Config.f11Macro) {
            failsafe();
        }
    }
    public void failsafe() {
        new Thread(() -> {
            try {
                Thread.sleep(2500);
                F11.stopAllMovement();
                Thread.sleep(2500);
                Aurora.mc.thePlayer.sendChatMessage("wow");
                Thread.sleep(5000);
                Aurora.mc.thePlayer.sendChatMessage("/warp garden");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
