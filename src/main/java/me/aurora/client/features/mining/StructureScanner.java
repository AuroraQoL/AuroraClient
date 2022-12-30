package me.aurora.client.features.mining;

import me.aurora.client.config.Config;
import me.aurora.client.utils.LookupBlockUtils;
import me.aurora.client.utils.ScannerUtils;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * Structure Scanner.
 */
public class StructureScanner {
    private final ConcurrentHashMap<BlockPos, String> structures = new ConcurrentHashMap<>();
    boolean readyToScan = true;
    Minecraft mc = Minecraft.getMinecraft();
    Block[] blocksSpiralPillarA = {Blocks.tripwire_hook, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone_brick_stairs};
    BlockStone.EnumType[] stonePropSpiralPillarA = {null, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.ANDESITE_SMOOTH, null};
    Block[] blocksSpiralPillarC = {Blocks.double_stone_slab,Blocks.double_stone_slab, Blocks.tripwire_hook, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone_brick_stairs};
    BlockStone.EnumType[] stonePropSpiralPillarC = {null, null, null, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.ANDESITE_SMOOTH, null};
    Block[] blocksSpiralPillarB = {Blocks.double_stone_slab, Blocks.tripwire, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone};
    BlockStone.EnumType[] stonePropSpiralPillarB = {null, null, BlockStone.EnumType.DIORITE_SMOOTH,BlockStone.EnumType.DIORITE_SMOOTH,BlockStone.EnumType.DIORITE_SMOOTH,BlockStone.EnumType.DIORITE,BlockStone.EnumType.DIORITE};
    Block[] blocksThronePillarA = {Blocks.double_stone_slab, Blocks.double_stone_slab, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.air, Blocks.double_stone_slab, Blocks.double_stone_slab, Blocks.stone, Blocks.double_stone_slab, Blocks.stone, Blocks.air};
    BlockStone.EnumType[] stonePropThronePillarA = {null, null, BlockStone.EnumType.DIORITE,BlockStone.EnumType.DIORITE,BlockStone.EnumType.DIORITE, null, null, null, null, null, BlockStone.EnumType.DIORITE, null, BlockStone.EnumType.DIORITE, null};
    Block[] blocksThronePillarB = {Blocks.double_stone_slab, Blocks.stone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.double_stone_slab, Blocks.stone, Blocks.double_stone_slab, Blocks.double_stone_slab, Blocks.double_stone_slab};
    BlockStone.EnumType[] stonePropThronePillarB = {null, BlockStone.EnumType.DIORITE, null, null, null, BlockStone.EnumType.DIORITE, null, null, null};
    Block[] blocksThronePillarC = {Blocks.stone, Blocks.stone_brick_stairs, Blocks.double_stone_slab, Blocks.stone, Blocks.stone, Blocks.stone_slab, Blocks.stone_slab};
    BlockStone.EnumType[] stonePropThronePillarC = {BlockStone.EnumType.ANDESITE_SMOOTH, null, null, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, null, null};



    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if ((((mc.theWorld.getTotalWorldTime()) % (4L * Config.scanrange2)) == 0) && Config.scanner2 && readyToScan && Conditions.inGame()) {
            readyToScan = false;
            Thread scan = new Thread(() -> scanBlocks(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), "StructureScanning");
            scan.start();
        }

    }

    public void scanBlocks(Double StartX, Double StartY, Double StartZ) {
        IntStream.range((int) (StartX - Config.scanrange2), (int) (StartX + Config.scanrange2)).forEach(x -> {
            IntStream.range((int) (StartY - Config.scanrange2), (int) (StartY + Config.scanrange2)).forEach(y -> {
                IntStream.range((int) (StartZ - Config.scanrange2), (int) (StartZ + Config.scanrange2)).forEach(z -> {
                    String structureCheckResult = checkForStructureOnBlock(x, y, z);
                    if(!Objects.equals(structureCheckResult, "null")) structures.put(new BlockPos(x,y,z), structureCheckResult);
                });
            });
        });
        readyToScan = true;
    }

    public String checkForStructureOnBlock(int x, int y, int z) {
        if (LookupBlockUtils.blocksAbove(new BlockPos(x,y,z), blocksSpiralPillarA, stonePropSpiralPillarA)) return "SPIRAL-Pillar-A";
        if (LookupBlockUtils.blocksAbove(new BlockPos(x,y,z), blocksThronePillarA, stonePropThronePillarA)) return "THRONE-Pillar-A";
        if (LookupBlockUtils.blocksAbove(new BlockPos(x,y,z), blocksThronePillarB, stonePropThronePillarB)) return "THRONE-Pillar-B";
        if (LookupBlockUtils.blocksAbove(new BlockPos(x,y,z), blocksThronePillarC, stonePropThronePillarC)) return "THRONE-Pillar-C";
        if (LookupBlockUtils.blocksAbove(new BlockPos(x,y,z), blocksSpiralPillarB, stonePropSpiralPillarB)) return "SPIRAL-Pillar-B";
        if (LookupBlockUtils.blocksAbove(new BlockPos(x,y,z), blocksSpiralPillarC, stonePropSpiralPillarC)) return "SPIRAL-Pillar-C";
        return "null";
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (Config.scanner2) structures.forEach((key, value) -> ScannerUtils.renderBeaconText(String.format("%s - %d %d %d", value, key.getX(), key.getY(), key.getZ()), key, event.partialTicks));
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        structures.clear();
    }
}
