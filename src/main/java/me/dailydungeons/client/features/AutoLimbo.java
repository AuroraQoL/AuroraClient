package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.DailyDungeonsMessages;
import me.dailydungeons.client.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoLimbo {
    static Minecraft mc = Minecraft.getMinecraft();

    public static String removeFormatting(String input) {
        return input.replaceAll("[§|&][0-9,a-f,k-o,r]", "");
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Config.AutoLimbo && Utils.inSkyBlock) {
            ScoreObjective scoreboardObj = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
            if (scoreboardObj != null) {
                String scObjName = removeFormatting(scoreboardObj.getDisplayName());
                if (scObjName.contains("SKYBLOCK")) {
                    mc.thePlayer.sendChatMessage("/limbo");
                    DailyDungeonsMessages.sendModMessage("noob xd");
                }
            }
        }
    }
}
