package me.aurora.client.utils.iteration;

import me.aurora.client.utils.ClientMessages;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

import static me.aurora.client.Aurora.mc;

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

    public static void brLoopBoundChunk(int startX, int startY, int startZ, int radius, BlockOperation lambdaThree, int downBoundary, int upperBoundary) {
        int chunkRadius = (int) Math.ceil(radius / 16);
        Chunk startCh = mc.theWorld.getChunkFromBlockCoords(new BlockPos(startX, startY, startZ));
        int startChX = startCh.xPosition;
        int startChZ = startCh.zPosition;
        for (int chX = startChX - chunkRadius; chX <= startChX + chunkRadius; chX++) {
            for (int chZ = startChZ - chunkRadius; chZ <= startChZ + chunkRadius; chZ++) {
                Chunk checkChunk = mc.theWorld.getChunkFromChunkCoords(chX,chZ);
                if (!checkChunk.isLoaded()) continue;
                for (int x = 0; x < 17; x++) {
                    for (int y = downBoundary; y <= upperBoundary; y++) {
                        for (int z = 0; z < 17; z++) {
                            lambdaThree.activate(x+(checkChunk.xPosition*16), y, z+(checkChunk.zPosition*16));
                        }
                    }
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
