package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.iteration.LoopUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 3.1
 * @brief This automaticly removes wither doors
 */
public class WitherDoorRemover implements Module {

    public String name() {
        return "WitherDoorRemover";
    }

    public boolean toggled() {
        return Config.witherDoorRemover;
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_H) && toggled()) {
            MovingObjectPosition keyBlock = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
            LoopUtils.brLoop(keyBlock.getBlockPos().getX(), keyBlock.getBlockPos().getY(), keyBlock.getBlockPos().getZ(), 5,
                    (x, y, z) -> {
                BlockPos tempBlockPos = new BlockPos(x,y,z);
                if (mc.theWorld.getBlockState(tempBlockPos).getBlock() == Blocks.coal_block) mc.theWorld.setBlockToAir(tempBlockPos);
            });
        }
    }
}

