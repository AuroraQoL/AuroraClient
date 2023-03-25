package me.aurora.client.utils;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.aurora.client.utils.string.StringUtils;
import net.minecraft.scoreboard.ScoreObjective;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * @brief Condition Utilities
 */
public class Condition {
    private static final ConditionalExecutor executor = new ConditionalExecutor(true);
    public static boolean inGame() { return (mc.thePlayer != null && mc.theWorld != null); }

    public static boolean inSkyblock() {
        if (!inGame()) return false;
        ScoreObjective scoreboardObj = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        return scoreboardObj != null && StringUtils.removeFormatting(scoreboardObj.getDisplayName()).contains("SKYBLOCK");
    }

    public static void executeAtCoords(double x, double y, double z, Runnable r){
        executor.executeAtCoords(x, y, z, r);
    }

    public static void executeAtCoords(double x, double z, Runnable r){
        executor.executeAtCoords(x, z, r);
    }

    public static void executeAtCoords(int x, int y, int z, Runnable r){
        executor.executeAtCoords((double) x, (double) y, (double) z, r);
    }

    public static void executeAtCoords(int x, int z, Runnable r){
        executor.executeAtCoords((double) x, (double) z, r);
    }

    @SneakyThrows
    public static ConditionalExecutor executeIf(Boolean... b){
        for (Boolean aBoolean : b) if (!aBoolean) return new ConditionalExecutor(true);
        return new ConditionalExecutor(true);
    }

    @AllArgsConstructor
    public static class ConditionalExecutor {
        private boolean execute;
        public void executeAtCoords(double x, double y, double z, Runnable r){
            if (!execute) return;
            if (mc.thePlayer.posX == x && mc.thePlayer.posY == y && mc.thePlayer.posZ == z )
                executeAtCoords(x,y,z,r);
        }

        public void executeAtCoords(double x, double z, Runnable r){
            if (!execute) return;
            if (mc.thePlayer.posX == x && mc.thePlayer.posZ == z)
                r.run();
        }

        public void executeAtCoords(int x, int y, int z, Runnable r){
            if (!execute) return;
            executeAtCoords((double) x, (double) y, (double) z, r);
        }

        public void executeAtCoords(int x, int z, Runnable r){
            if (!execute) return;
            executeAtCoords((double) x, (double) z, r);
        }

        public void execute(Runnable r){
            if (!execute) return;
            r.run();
        }
    }
}
