package me.aurora.client.utils.rotation;
import net.minecraft.client.Minecraft;

public class Rotation {
    private final static Minecraft mc = Minecraft.getMinecraft();
    public boolean rotating;
    public boolean completed;
    private long startTime;
    private long endTime;

    private final float[] start = new float[2];
    private final float[] target = new float[2];
    private final float[] difference = new float[2];

    public void easeTo(float yaw, float pitch, long time) {
        completed = false;
        rotating = true;
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis() + time;
        start[0] = (mc.thePlayer.rotationYaw);
        start[1] = (mc.thePlayer.rotationPitch);
        target[0] = (AngleUtils.get360RotationYaw(yaw));
        target[1] = (pitch);
        getDifference();
    }

    public void lockAngle(float yaw, float pitch) {
        if (mc.thePlayer.rotationYaw != yaw || mc.thePlayer.rotationPitch != pitch && !rotating)
            easeTo(yaw, pitch, 1000);
    }

    public void update() {
        if (System.currentTimeMillis() <= endTime) {
            if (shouldRotateClockwise()) {
                mc.thePlayer.rotationYaw = start[0] + interpolate(difference[0]);
            } else {
                mc.thePlayer.rotationYaw = start[0] - interpolate(difference[0]);
            }
            mc.thePlayer.rotationPitch = start[1] + interpolate(difference[1]);
        } else if (!completed) {
            if (shouldRotateClockwise()) {
                System.out.println("Rotation final st - " + start[0] + ", " + mc.thePlayer.rotationYaw);
                mc.thePlayer.rotationYaw = target[0];
                System.out.println("Rotation final - " + start[0] + difference[0]);
            } else {
                mc.thePlayer.rotationYaw = target[0];
                System.out.println("Rotation final - " + (start[0] - difference[0]));
            }
            mc.thePlayer.rotationPitch = start[1] + difference[1];
            completed = true;
            rotating = false;
        }
    }

    private boolean shouldRotateClockwise() {
        return AngleUtils.clockwiseDifference(AngleUtils.get360RotationYaw(start[0]), target[0]) < 180;
    }

    public void reset() {
        completed = false;
        rotating = false;
    }

    private void getDifference() {
        difference[0] = (AngleUtils.smallestAngleDifference(AngleUtils.get360RotationYaw(), target[0]));
        difference[1] = (target[1] - start[1]);
    }

    private float interpolate(float difference) {
        final float spentMillis = System.currentTimeMillis() - startTime;
        final float relativeProgress = spentMillis / (endTime - startTime);
        return (difference) * easeOutSine(relativeProgress);
    }

    private float easeOutCubic(double number) {
        return (float) (1.0 - Math.pow(1.0 - number, 3.0));
    }

    private float easeOutSine(double number) {
        return (float) Math.sin((number * Math.PI) / 2);
    }
}