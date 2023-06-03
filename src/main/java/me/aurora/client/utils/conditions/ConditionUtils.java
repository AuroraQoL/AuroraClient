package me.aurora.client.utils.conditions;

import me.aurora.client.utils.string.StringUtils;
import me.cephetir.bladecore.core.listeners.SkyblockIsland;
import me.cephetir.bladecore.core.listeners.SkyblockListener;
import net.minecraft.scoreboard.ScoreObjective;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * @brief Condition Utilities
 */
public class ConditionUtils {
    public static boolean inGame() {
        return (mc.thePlayer != null && mc.theWorld != null);
    }

    public static boolean inSkyblock() {
        if (!inGame()) return false;
        ScoreObjective scoreboardObj = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        return scoreboardObj != null && (StringUtils.removeFormatting(scoreboardObj.getDisplayName()).contains("SKYBLOCK") || StringUtils.removeFormatting(scoreboardObj.getDisplayName()).contains("SKIBLOCK"));
    }

    public static boolean inCoalMine() {
        if (!inGame()) return false;
        return SkyblockListener.INSTANCE.getLocation().equals("Coal Mine");
    }

    public static boolean inEnd() {
        if (!inGame()) return false;
        return SkyblockListener.INSTANCE.getIsland() == SkyblockIsland.TheEnd;
    }

    public static boolean inDragonsNest() {
        if (!inGame()) return false;
        return SkyblockListener.INSTANCE.getLocation().equals("Dragon's Nest");
    }
}
