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

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author Gabagooooooooooool
 * @version 2.2
 * Structure Scanner.
 */
public class StructureScanner {
    private final ConcurrentHashMap<BlockPos, String> structures = new ConcurrentHashMap<>();
    boolean readyToScan = true;
    Minecraft mc = Minecraft.getMinecraft();
    Block[] blocksSpiralPillarA = {Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone_brick_stairs};
    BlockStone.EnumType[] stonePropSpiralPillarA = {BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.ANDESITE_SMOOTH, null};
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
        int rang =  Config.structureScanner_ParameterRange;
        if ((( (!Config.structureScanner_ParameterAggressiveScan) ? (((mc.theWorld.getTotalWorldTime()) % (4L * rang)) == 0) : (((mc.theWorld.getTotalWorldTime()) % ((int) (Config.structureScanner_ParameterRange/8))) == 0) ) && Config.structureScanner && readyToScan && Conditions.inGame())) {
            readyToScan = false;
            CompletableFuture.runAsync(() -> {
                Thread T = new Thread(() -> scanBlocks((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ, rang), "ScannerThread+" + new Random().nextInt(10000 - 5 + 1) + 5);
                new ScheduledThreadPoolExecutor(1).schedule(() -> {
                    if (T.isAlive()) {
                        T.stop();
                        readyToScan = true;
                    }
                }, 30, TimeUnit.SECONDS);
                T.start();
            });
        }
    }

    public void scanBlocks(int StartX, int StartY, int StartZ, int range) {
        for (int x = StartX -range; x <= StartX +range; x++) {
            for (int y = StartY -range; y <= StartY +range; y++) {
                for (int z = StartZ -range; z <= StartZ +range; z++) {
                    String structureCheckResult = checkForStructureOnBlock(x, y, z);
                    if(!Objects.equals(structureCheckResult, "null")) structures.put(new BlockPos(x,y,z), structureCheckResult);
                }
            }
        }
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
        if (Config.structureScanner) structures.forEach((key, value) -> ScannerUtils.renderBeaconText(String.format("%s - %d %d %d", value, key.getX(), key.getY(), key.getZ()), key, event.partialTicks));
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        structures.clear();
    }
}
