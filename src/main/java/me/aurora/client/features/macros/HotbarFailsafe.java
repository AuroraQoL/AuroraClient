package me.aurora.client.features.macros;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.MessageUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;
import java.util.Random;

public class HotbarFailsafe {

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if(event.player != null && Config.hotbar_failsafe && Config.f11Macro) {
            if(Aurora.mc.thePlayer.inventory.currentItem != Config.farming_tool_slot) {
                failsafe();
            }
        }
    }

    public void failsafe() {
        new Thread(()-> {
            try {
                Random random = new Random();
                int randomMessage = random.nextInt(MessageUtils.fakeMacroMessages.length);
                Config.f11Macro = false;
                F11.stopAllMovement();
                Thread.sleep(1500);
                Aurora.mc.thePlayer.sendChatMessage(MessageUtils.fakeMacroMessages[randomMessage]);
                Aurora.mc.thePlayer.inventory.currentItem = Config.farming_tool_slot;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
