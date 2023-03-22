package me.aurora.client.utils;

import net.minecraft.util.BlockPos;

public class CalculationUtils {
    public static double blockEuclideanDistance(BlockPos a, BlockPos b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getZ() - b.getZ(), 2));
    }
}
