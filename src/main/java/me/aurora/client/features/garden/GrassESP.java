package me.aurora.client.features.garden;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ScannerUtils;
import me.aurora.client.utils.conditions.Conditions;
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
import java.util.stream.IntStream;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 2.1
 * Gemstone Scanner (ESP)
 */

public class GrassESP  implements Module {
    public String name() {
        return "GrassESP";
    }

    public boolean toggled() {
        return Config.grassEsp;
    }

    boolean enquery;
    private Set<BlockPos> grassBlocks = ConcurrentHashMap.newKeySet();
    private Set<BlockPos> tempGrassBlocks = ConcurrentHashMap.newKeySet();
    boolean readyToScan = true;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (Config.grassEsp && readyToScan && Conditions.inGame()) {
            readyToScan = false;
            new Thread(() -> scanBlocks((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ), "GrassScan").start();
        }

    }

    public void scanBlocks(int StartX, int StartY, int StartZ) {
            tempGrassBlocks.clear();
            grassBlocks.stream().filter(b -> !blockIsGrass(b)).forEach(b -> tempGrassBlocks.add(b));
            tempGrassBlocks.forEach(b->{grassBlocks.remove(b);});
            IntStream.range(StartX - 50, StartX + 50).forEach(x -> {
                IntStream.range(StartY - 50, StartY + 50).forEach(y -> {
                    IntStream.range(StartZ - 50, StartZ + 50).filter(z -> (blockIsGrass(new BlockPos(x, y, z)))).forEach(z -> {
                        grassBlocks.add(new BlockPos(x, y, z));
                    });
                });
            });
            readyToScan = true;
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (Config.grassEsp) {
            grassBlocks.stream().filter(this::blockIsGrass).forEach(b -> {
                ScannerUtils.drawOutlinedBoundingBox(b, new Color(146, 255, 65, 255), 3, event.partialTicks);
            });
        }
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        readyToScan = true;
        grassBlocks.clear();
    }

    private boolean blockIsGrass(BlockPos b){
        if (mc.theWorld.getBlockState(b).getBlock() == Blocks.tallgrass){
            return (mc.theWorld.getBlockState(b).getValue(BlockTallGrass.TYPE) == BlockTallGrass.EnumType.GRASS);
        } else {
            return false;
        }
    }
    
}

