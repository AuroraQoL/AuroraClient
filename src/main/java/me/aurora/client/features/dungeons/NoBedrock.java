package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.conditions.ConditionUtils;
import me.aurora.client.utils.iteration.LoopUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * @brief Removes bedrock
 */

public class NoBedrock implements Module {

    private boolean readyToScan = true;

    public String name() {
        return "SecretsUnblock";
    }

    public boolean toggled() {
        return Config.ghost_secretsUnblock;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (toggled() && ((mc.theWorld.getTotalWorldTime() % 128L == 0) && readyToScan && ConditionUtils.inGame()))
            new Thread(() -> scanBlocks((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ), "NoBedrock_thread").start();
    }

    public void scanBlocks(int StartX, int StartY, int StartZ) {
        readyToScan = false;
        LoopUtils.brLoop(StartX, StartY, StartZ, 128, (x, y, z) -> {
            BlockPos checked = new BlockPos(x, y, z);
            if (mc.theWorld.getBlockState(checked).getBlock() == Blocks.bedrock)
                mc.theWorld.setBlockToAir(checked);
        });
        readyToScan = true;
    }
}

