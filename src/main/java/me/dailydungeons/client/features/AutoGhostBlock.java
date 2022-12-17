package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;


public class AutoGhostBlock {


    Minecraft mc = Minecraft.getMinecraft();



  /*  @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (mc.gameSettings.isKeyDown(key))


    } */

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_H) && Config.witherDoorSmasher) {
            MovingObjectPosition POSITIONOFBLOCK = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
            int StartX = POSITIONOFBLOCK.getBlockPos().getX();
            int StartY = POSITIONOFBLOCK.getBlockPos().getY();
            int StartZ = POSITIONOFBLOCK.getBlockPos().getZ();
            List<BlockPos> blocksToRemove = new ArrayList<BlockPos>();

            for (int x = (StartX-4); x<StartX+4; x++) {
                for (int y = (StartY-4); y<StartY+4; y++) {
                    for (int z = (StartZ-4); z<StartZ+4; z++) {
                        Block tempPosOfRandomBlock = mc.theWorld.getBlockState(new BlockPos(x,y,z)).getBlock();
                        if (tempPosOfRandomBlock.getBlockState().getBlock() == Blocks.coal_block){
                            blocksToRemove.add(new BlockPos(x,y,z));
                        }
                    }
                }
            }

            for (BlockPos b: blocksToRemove) {
                mc.theWorld.setBlockToAir((b));
            }
        }
    }

}

