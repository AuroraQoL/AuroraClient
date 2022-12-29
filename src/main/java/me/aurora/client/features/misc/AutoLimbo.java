package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ClientMessages;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/// ?? wtf is that

public class AutoLimbo {
    static Minecraft mc = Minecraft.getMinecraft();

    public static String removeFormatting(String input) {
        return input.replaceAll("[§|&][0-9,a-f,k-o,r]", "");
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Config.AutoLimbo && Conditions.inSkyblock()) {
            mc.thePlayer.sendChatMessage("/limbo");
        }
    }
}

