package me.aurora.client.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static me.aurora.client.Aurora.mc;

public class LookupBlockUtils {

    public static boolean blocksAbove(BlockPos baseBlock, Block[] blockList, BlockStone.EnumType[] stoneParameters){
        for (int i = 0; i < blockList.length; i++) {
            if (mc.theWorld.getBlockState(new BlockPos(baseBlock.getX(), baseBlock.getY() + i, baseBlock.getZ())).getBlock() == blockList[i]) {
                if (stoneParameters[i] != null && mc.theWorld.getBlockState(new BlockPos(baseBlock.getX(), baseBlock.getY() + i, baseBlock.getZ())).getBlock() == Blocks.stone) {
                    if (mc.theWorld.getBlockState(new BlockPos(baseBlock.getX(), baseBlock.getY() + i, baseBlock.getZ())).getValue(BlockStone.VARIANT) != stoneParameters[i]){
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
