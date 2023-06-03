package me.aurora.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;

public class BlockDirUtils {
    public static int PlayerX = Minecraft.getMinecraft().thePlayer.getPosition().getX();
    public static int PlayerY = Minecraft.getMinecraft().thePlayer.getPosition().getY();
    public static int PlayerZ = Minecraft.getMinecraft().thePlayer.getPosition().getZ();

    public EnumFacing getDirection() {
        return Minecraft.getMinecraft().thePlayer.getHorizontalFacing();
    }

    public static enum BLOCK_DIRECTION {
        LEFT,
        RIGHT,
        FORWARD,
        BACK
    }
}
