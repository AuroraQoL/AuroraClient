package me.aurora.client.utils.conditions;

import me.aurora.client.utils.string.StringUtils;
import net.minecraft.scoreboard.ScoreObjective;

import static me.aurora.client.Aurora.mc;

public class ConditionUtils {
    public static boolean inGame() { return (mc.thePlayer != null && mc.theWorld != null); };

    /**
     * Modified code from HyAddons.
     * */
    public static boolean inSkyblock() {
        if (inGame()) {
            ScoreObjective scoreboardObj = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
            return inGame() && scoreboardObj != null && StringUtils.removeFormatting(scoreboardObj.getDisplayName()).contains("SKYBLOCK");
        } else {
            return false;
        }
    }
}
