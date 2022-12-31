package me.aurora.client.features.dungeons;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.stream.IntStream;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * Wither Door Remover
 */
public class WitherDoorRemover {
    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_H) && Config.witherDoorRemover) {
            MovingObjectPosition keyBlock = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
            IntStream.range(keyBlock.getBlockPos().getX() - 5, keyBlock.getBlockPos().getX() + 5).forEach(x -> {
                IntStream.range(keyBlock.getBlockPos().getY() - 5, keyBlock.getBlockPos().getY() + 5).forEach(y -> {
                    IntStream.range(keyBlock.getBlockPos().getZ() - 5, keyBlock.getBlockPos().getZ() + 5).filter(z -> {
                        return (mc.theWorld.getBlockState(new BlockPos(x,y,z)).getBlock() == Blocks.coal_block);
                    }).forEach(z -> {
                        Aurora.mc.theWorld.setBlockToAir(new BlockPos(x, y ,z));
                    });
                });
            });
        }
    }
}

