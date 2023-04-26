package me.aurora.client.features.garden;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BlockRenderUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import me.aurora.client.utils.iteration.LoopUtils;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * @brief Grass ESP
 * Back to the original state
 */
public class GrassESP implements Module {
    public String name() {
        return "GrassESP";
    }

    public boolean toggled() {
        return Config.grassEsp;
    }

    private Set<BlockPos> grassBlocks = ConcurrentHashMap.newKeySet();
    private Set<BlockPos> tempGrassBlocks = ConcurrentHashMap.newKeySet();
    boolean readyToScan = true;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (Config.grassEsp && readyToScan && ConditionUtils.inGame()) {
            readyToScan = false;
            new Thread(() -> scanBlocks((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ), "GrassScan").start();
        }

    }

    public void scanBlocks(int StartX, int StartY, int StartZ) {
        tempGrassBlocks.clear();
        grassBlocks.stream().filter(b -> !blockIsGrass(b)).forEach(tempGrassBlocks::add);
        tempGrassBlocks.forEach(b -> {
            grassBlocks.remove(b);
        });
        LoopUtils.brLoop(StartX, StartY, StartZ, 50, (x, y, z) -> {
            BlockPos block = new BlockPos(x, y, z);
            if (blockIsGrass(block)) grassBlocks.add(block);
        });
        readyToScan = true;
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (Config.grassEsp)
            grassBlocks.stream().filter(this::blockIsGrass).forEach(b -> BlockRenderUtils.drawOutlinedBoundingBox(b, new Color(146, 255, 65, 255), 3, event.partialTicks));

    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        readyToScan = true;
        grassBlocks.clear();
    }

    private boolean blockIsGrass(BlockPos b) {
        if (mc.theWorld.getBlockState(b).getBlock() == Blocks.tallgrass)
            return (mc.theWorld.getBlockState(b).getValue(BlockTallGrass.TYPE) == BlockTallGrass.EnumType.GRASS);
        return false;
    }
}