package me.aurora.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

import static me.aurora.client.utils.rotation.AngleUtils.get360RotationYaw;

public class BlockDirUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final Block[] walkables = { Blocks.air, Blocks.water, Blocks.flowing_water, Blocks.dark_oak_fence_gate, Blocks.acacia_fence_gate, Blocks.birch_fence_gate, Blocks.oak_fence_gate, Blocks.jungle_fence_gate, Blocks.spruce_fence_gate, Blocks.wall_sign, Blocks.reeds, Blocks.pumpkin_stem, Blocks.melon_stem, Blocks.iron_trapdoor, Blocks.stone_stairs, Blocks.carpet, Blocks.stone_slab, Blocks.stone_slab2, Blocks.wooden_slab, Blocks.snow_layer, Blocks.trapdoor };
    private static final Block[] walkablesMushroom = { Blocks.air, Blocks.water, Blocks.flowing_water, Blocks.dark_oak_fence_gate, Blocks.acacia_fence_gate, Blocks.birch_fence_gate, Blocks.oak_fence_gate, Blocks.jungle_fence_gate, Blocks.spruce_fence_gate, Blocks.wall_sign, Blocks.reeds, Blocks.pumpkin_stem, Blocks.melon_stem, Blocks.iron_trapdoor, Blocks.stone_stairs, Blocks.carpet, Blocks.stone_slab, Blocks.stone_slab2, Blocks.wooden_slab, Blocks.snow_layer, Blocks.trapdoor };
    private static final Block[] walkablesCactus = { Blocks.air, Blocks.water, Blocks.flowing_water, Blocks.dark_oak_fence_gate, Blocks.acacia_fence_gate, Blocks.birch_fence_gate, Blocks.oak_fence_gate, Blocks.jungle_fence_gate, Blocks.spruce_fence_gate, Blocks.wall_sign, Blocks.reeds, Blocks.pumpkin_stem, Blocks.melon_stem, Blocks.iron_trapdoor, Blocks.stone_stairs, Blocks.snow_layer, Blocks.trapdoor };

    public static int getUnitX(){
        return getUnitX((mc.thePlayer.rotationYaw % 360 + 360) % 360);
    }

    public static int getUnitZ(){
        return getUnitZ((mc.thePlayer.rotationYaw % 360 + 360) % 360);

    }

    public static int getUnitX(float modYaw) {
        if (get360RotationYaw(modYaw) < 45 || get360RotationYaw(modYaw) > 315) {
            return 0;
        } else if (get360RotationYaw(modYaw) < 135) {
            return -1;
        } else if (get360RotationYaw(modYaw) < 225) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int getUnitZ(float modYaw) {
        if (get360RotationYaw(modYaw) < 45 || get360RotationYaw(modYaw) > 315) {
            return 1;
        } else if (get360RotationYaw(modYaw) < 135) {
            return 0;
        } else if (get360RotationYaw(modYaw) < 225) {
            return -1;
        } else {
            return 0;
        }
    }

    public static Block getBlock(BlockPos blockPos) {
        return mc.theWorld.getBlockState(blockPos).getBlock();
    }

    public static boolean isRelativeBlockPassable(float x, float y, float z) {
        return getRelativeBlock(x, y, z).isPassable(mc.theWorld, getRelativeBlockPos(x, y, z));
    }

    public static Block getRelativeBlock(float x, float y, float z) {
        return (mc.theWorld.getBlockState(
                new BlockPos(
                        mc.thePlayer.posX + (getUnitX() * z) + (getUnitZ() * -1 * x),
                        ((mc.thePlayer.posY % 1) > 0.7 ? Math.ceil(mc.thePlayer.posY) : mc.thePlayer.posY) + y,
                        mc.thePlayer.posZ + (getUnitZ() * z) + (getUnitX() * x)
                )).getBlock());
    }
    public static BlockPos getRelativeBlockPos(float x, float y, float z) {
        return new BlockPos(
                mc.thePlayer.posX + (getUnitX() * z) + (getUnitZ() * -1 * x),
                ((mc.thePlayer.posY % 1) > 0.7 ? Math.ceil(mc.thePlayer.posY) : mc.thePlayer.posY) + y,
                mc.thePlayer.posZ + (getUnitZ() * z) + (getUnitX() * x)
        );
    }

    public static Block getRelativeBlock(float x, float y, float z, float yaw) {
        return (mc.theWorld.getBlockState(
                new BlockPos(
                        mc.thePlayer.posX + (getUnitX(yaw) * z) + (getUnitZ(yaw) * -1 * x),
                        ((mc.thePlayer.posY % 1) > 0.7 ? Math.ceil(mc.thePlayer.posY) : mc.thePlayer.posY) + y,
                        mc.thePlayer.posZ + (getUnitZ(yaw) * z) + (getUnitX(yaw) * x)
                )).getBlock());
    }
    public static BlockPos getRelativeBlockPos(float x, float y, float z, float yaw) {
        return new BlockPos(
                mc.thePlayer.posX + (getUnitX(yaw) * z) + (getUnitZ(yaw) * -1 * x),
                ((mc.thePlayer.posY % 1) > 0.7 ? Math.ceil(mc.thePlayer.posY) : mc.thePlayer.posY) + y,
                mc.thePlayer.posZ + (getUnitZ(yaw) * z) + (getUnitX(yaw) * x)
        );
    }
    public static int bedrockCount() {
        int count = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(getBlock(mc.thePlayer.getPosition().add(i, 1, j)).equals(Blocks.bedrock))
                    count ++;
            }
        }
        return count;
    }
    public static boolean isWater(Block b){
        return b.equals(Blocks.water) || b.equals(Blocks.flowing_water);
    }

    public static Block getLeftBlock(int yaw){
        return getRelativeBlock(-1, 0, 0, mc.thePlayer.rotationYaw - yaw);
    }
    public static Block getRightBlock(int yaw){
        return getRelativeBlock(1, 0, 0, mc.thePlayer.rotationYaw - yaw);
    }

    public static Block getLeftTopBlock(int yaw) {
        return getRelativeBlock(-1, 1, 0, mc.thePlayer.rotationYaw - yaw);
    }

    public static Block getRightTopBlock(int yaw) {
        return getRelativeBlock(1, 1, 0, mc.thePlayer.rotationYaw - yaw);
    }


    public static Block getLeftBlock(){
        return getRelativeBlock(-1, 0, 0, mc.thePlayer.rotationYaw);
    }
    public static Block getRightBlock(){
        return getRelativeBlock(1, 0, 0, mc.thePlayer.rotationYaw);
    }

    public static Block getLeftTopBlock() {
        return getRelativeBlock(-1, 1, 0, mc.thePlayer.rotationYaw);
    }

    public static Block getRightTopBlock() {
        return getRelativeBlock(1, 1, 0, mc.thePlayer.rotationYaw);
    }

    public static Block getBackBlock(){
        return getRelativeBlock(0, 0, -1);
    }
    public static Block getFrontBlock(){
        return getRelativeBlock(0, 0, 1);
    }


    public static boolean isBlockVisible(BlockPos pos) {
        MovingObjectPosition mop = mc.theWorld.rayTraceBlocks(new Vec3(mc.thePlayer.posX, mc.thePlayer.posY + mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ), new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
        return mop == null || (mop.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mop.entityHit.getDistance(pos.getX(), pos.getY(), pos.getZ()) < 2) || mop.getBlockPos().equals(pos);
    }
}
