package me.aurora.client.utils;

import net.minecraft.util.BlockPos;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * @brief Calculation Utilities
 */
public class CalculationUtils {
    public static double blockEuclideanDistance(BlockPos a, BlockPos b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getZ() - b.getZ(), 2));
    }
}
