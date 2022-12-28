package me.aurora.client.utils.conditions;

import me.aurora.client.Aurora;
import me.aurora.client.utils.string.StringUtils;
import net.minecraft.scoreboard.ScoreObjective;

public class Conditions {
    public static boolean inGame() { return (Aurora.mc.thePlayer != null && Aurora.mc.theWorld != null); };

    /**
     * Modified code from HyAddons.
     * */
    public static boolean inSkyblock() {
        if (inGame()) {
            ScoreObjective scoreboardObj = Aurora.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
            return inGame() && scoreboardObj != null && StringUtils.removeFormatting(scoreboardObj.getDisplayName()).contains("SKYBLOCK");
        } else {
            return false;
        }
    }
}
