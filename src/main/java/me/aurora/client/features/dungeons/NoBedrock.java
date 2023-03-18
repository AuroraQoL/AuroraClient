package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.stream.IntStream;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * Removes bedrock
 */

public class NoBedrock implements Module {

    public String name() {
        return "SecretsUnblock";
    }

    public boolean toggled() {
        return Config.ghost_secretsUnblock;
    }

    private boolean readyToScan = true;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (Config.ghost_secretsUnblock && ((mc.theWorld.getTotalWorldTime() % 128L == 0) && readyToScan && ConditionUtils.inGame())) {
            readyToScan = false;
            new Thread(() -> scanBlocks((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ), "NoBedrock_thread").start();
        }
    }

    public void scanBlocks(int StartX, int StartY, int StartZ) {
        IntStream.range(StartX - Config.gemstoneEsp_ParameterRange, StartX + Config.gemstoneEsp_ParameterRange).forEach(x -> {
            IntStream.range(StartY - Config.gemstoneEsp_ParameterRange, StartY + Config.gemstoneEsp_ParameterRange).forEach(y -> {
                IntStream.range(StartZ - Config.gemstoneEsp_ParameterRange, StartZ + Config.gemstoneEsp_ParameterRange)
                         .filter(z -> mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.bedrock)
                         .forEach(z -> mc.theWorld.setBlockToAir(new BlockPos(x, y ,z)));
            });
        });
        readyToScan = true;
    }
}

