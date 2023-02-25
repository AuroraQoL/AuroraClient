package me.aurora.client.utils.iteration;

public class LoopUtils {
    public static void brLoop(int startX, int startY, int startZ, int radius, BlockOperation lambdaThree){
        for (int x = startX-radius; x <= startX + radius; x++) {
            for (int y = startY-radius; y <= startY + radius; y++) {
                for (int z = startZ-radius; z <= startZ + radius; z++) {
                    lambdaThree.activate(x,y,z);
                }
            }
        }
    }

    public static void brLoopBound(int startX, int startY, int startZ, int radius, BlockOperation lambdaThree, int downBoundary, int upperBoundary){
        for (int x = startX-radius; x <= startX + radius; x++) {
            for (int y = Math.max(startY-radius, downBoundary); y <= Math.min(startY + radius, upperBoundary); y++) {
                for (int z = startZ-radius; z <= startZ + radius; z++) {
                    lambdaThree.activate(x,y,z);
                }
            }
        }
    }
}
