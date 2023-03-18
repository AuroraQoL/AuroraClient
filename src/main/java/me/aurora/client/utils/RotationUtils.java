package me.aurora.client.utils;

import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

public class RotationUtils {

    private static float pitchDifference;
    public static float yawDifference;
    private static int ticks = -1;
    private static int tickCounter = 0;
    private static Runnable callback = null;
    public static class Rotation {
        public float pitch;
        public float yaw;

        public Rotation(float pitch, float yaw) {
            this.pitch = pitch;
            this.yaw = yaw;
        }
    }

    private static double wrapAngleTo180(double angle) {
        return angle - Math.floor(angle / 360 + 0.5) * 360;
    }

    public static Rotation getRotationToBlock(BlockPos block) {
        double diffX = block.getX() - mc.thePlayer.posX + 0.5;
        double diffY = block.getY() - mc.thePlayer.posY + 0.5 - mc.thePlayer.getEyeHeight();
        double diffZ = block.getZ() - mc.thePlayer.posZ + 0.5;
        double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);

        float pitch = (float) -Math.atan2(dist, diffY);
        float yaw = (float) Math.atan2(diffZ, diffX);
        pitch = (float) wrapAngleTo180((pitch * 180F / Math.PI + 90)*-1);
        yaw = (float) wrapAngleTo180((yaw * 180 / Math.PI) - 90);

        return new Rotation(pitch, yaw);
    }

    public static void smoothLook(Rotation rotation, int ticks, Runnable callback) {
        if(ticks == 0) {
            look(rotation);
            if(callback != null) callback.run();
            return;
        }

        RotationUtils.callback = callback;

        pitchDifference = rotation.pitch - mc.thePlayer.rotationPitch;
        yawDifference = rotation.yaw - mc.thePlayer.rotationYaw;

        RotationUtils.ticks = ticks * 20;
        RotationUtils.tickCounter = 0;
    }

    public static void smartLook(Rotation rotation, int ticksPer180, Runnable callback) {
        float rotationDifference = Math.max(
                Math.abs(rotation.pitch - mc.thePlayer.rotationPitch),
                Math.abs(rotation.yaw - mc.thePlayer.rotationYaw)
        );
        smoothLook(rotation, (int) (rotationDifference / 180 * ticksPer180), callback);
    }

    public static void look(Rotation rotation) {
        mc.thePlayer.rotationPitch = rotation.pitch;
        mc.thePlayer.rotationYaw = rotation.yaw;
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if(tickCounter < ticks) {
            mc.thePlayer.rotationPitch += pitchDifference / ticks;
            mc.thePlayer.rotationYaw += yawDifference / ticks;
            tickCounter++;
        } else if(callback != null) {
            callback.run();
            callback = null;
        }
    }

}