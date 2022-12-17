package me.dailydungeons.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class inDungeon {
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static boolean inDungeon = false;
    private static int ticks = 0;

    public static String removeFormatting(String input) {
        return input.replaceAll("[§|&][0-9,a-f,k-o,r]", "");
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(mc.thePlayer != null) {
            if(ticks % 20 == 0) {
                ScoreObjective scoreboardObj = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
                if (scoreboardObj != null) {
                    String scObjName = removeFormatting(scoreboardObj.getDisplayName());
                    if (scObjName.contains("The Catacombs")) {
                        inDungeon = true;
                        return;
                    }
                }
                ticks = 0;
            }
            inDungeon = false;
            ticks++;
        }
    }
}
