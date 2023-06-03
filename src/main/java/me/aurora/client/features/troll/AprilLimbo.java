package me.aurora.client.features.troll;

import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Calendar;
import java.util.Random;

public class AprilLimbo {
    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        Random random = new Random();
        int a = random.nextInt(10000);
        if (event.player != null && ConditionUtils.inSkyblock() && Calendar.MONTH == 4 && Calendar.DAY_OF_MONTH == 1 && a == 1) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/limbo");
            //display ban screen
        }
    }
}
