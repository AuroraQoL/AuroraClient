package me.aurora.client.utils;

import net.minecraft.util.BlockPos;

public class CalculationUtils {
    public static double blockEuclideanDistance(BlockPos a, BlockPos b){
        double ax = a.getX();
        double ay = a.getY();
        double az = a.getZ();
        double bx = b.getX();
        double by = b.getY();
        double bz = b.getZ();
        return Math.sqrt(Math.pow(ax - bx, 2) + Math.pow(ay - by, 2) + Math.pow(az - bz, 2));
    }
}
