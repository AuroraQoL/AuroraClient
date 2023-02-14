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

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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
    Block[] precursorCityA = {Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, Blocks.stone_slab, Blocks.quartz_block, Blocks.stone_slab};
    BlockStone.EnumType[] stonePropPrecursorCityA = {null, null, null, null, null, null};
    Block[] minesOfDivanA = {Blocks.cobblestone, Blocks.cobblestone, Blocks.stone, Blocks.stone_brick_stairs, Blocks.stone_brick_stairs};
    BlockStone.EnumType[] stonePropMinesOfDivanA = {null, null, BlockStone.EnumType.ANDESITE_SMOOTH, null, null};
    Block[] jungleTempleA = {Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_stairs};
    BlockStone.EnumType[] stonePropJungleTempleA = {null, null, null, null, null, null, null, null, null, null, null};
    Block[] goblinKingA = {Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.stonebrick, Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.stonebrick, Blocks.stone};
    BlockStone.EnumType[] stonePropGoblinKingA = {null, null, null, null, null, null, BlockStone.EnumType.ANDESITE_SMOOTH};
    Block[] goblinQueenA = {Blocks.stonebrick, Blocks.stonebrick, Blocks.stonebrick, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stonebrick};
    BlockStone.EnumType[] stonePropGoblinQueenA = {null, null, null, BlockStone.EnumType.GRANITE, BlockStone.EnumType.GRANITE_SMOOTH, BlockStone.EnumType.GRANITE_SMOOTH, BlockStone.EnumType.GRANITE, BlockStone.EnumType.GRANITE_SMOOTH, null};
    Block[] balA = {Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.cobblestone_wall};
    BlockStone.EnumType[] stonePropBalA = {null, null, null, null, null, null, null, null, null, null, null, null, null, null};


    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        int rang =  Config.structureScanner_ParameterRange;
        if ((( (!Config.structureScanner_ParameterAggressiveScan) ? (((mc.theWorld.getTotalWorldTime()) % (4L * rang)) == 0) : (((mc.theWorld.getTotalWorldTime()) % ((int) (Config.structureScanner_ParameterRange/8))) == 0) ) && Config.structureScanner && readyToScan && Conditions.inGame())) {
            readyToScan = false;
            if(Config.structureScanner_ParameterThread){
                if(Config.structureScanner_freecam){
                    CompletableFuture.runAsync(() -> scanBlocksFast((int) mc.getRenderViewEntity().posX, (int) mc.getRenderViewEntity().posY, (int) mc.getRenderViewEntity().posZ));
                } else {
                    CompletableFuture.runAsync(() -> scanBlocksFast((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ));
                }
            } else {
                if (Config.structureScanner_freecam) {
                    CompletableFuture.runAsync(() -> scanBlocks((int) mc.getRenderViewEntity().posX, (int) mc.getRenderViewEntity().posY, (int) mc.getRenderViewEntity().posZ));
                } else {
                    CompletableFuture.runAsync(() -> scanBlocks((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ));
                }
            }
        }
        if ( (((mc.theWorld.getTotalWorldTime()) % (16L * rang)) == 0) && !readyToScan) readyToScan=true;
    }

    public void scanBlocks(int StartX, int StartY, int StartZ) {
        IntStream.range(StartY - Config.structureScanner_ParameterRange, StartY + Config.structureScanner_ParameterRange).filter(y -> (y>31 && y<180)).forEach(y -> {
            IntStream.range(StartX - Config.structureScanner_ParameterRange, StartX + Config.structureScanner_ParameterRange).forEach(x -> {
                IntStream.range(StartZ - Config.structureScanner_ParameterRange, StartZ + Config.structureScanner_ParameterRange).forEach(z -> {
                    String structureCheckResult = checkForStructureOnBlock(x, y, z);
                    if(!Objects.equals(structureCheckResult, "null")) structures.put(new BlockPos(x,y,z), structureCheckResult);
                });
            });
        });
        readyToScan = true;
    }

    public void scanBlocksFast(int StartX, int StartY, int StartZ) {
        IntStream.range(StartX - Config.structureScanner_ParameterRange, StartX + Config.structureScanner_ParameterRange).parallel().forEach(x -> {
            IntStream.range(StartY - Config.structureScanner_ParameterRange, StartY + Config.structureScanner_ParameterRange).filter(y -> (y>31 && y<180)).forEach(y -> {
                IntStream.range(StartZ - Config.structureScanner_ParameterRange, StartZ + Config.structureScanner_ParameterRange).forEach(z -> {
                    String structureCheckResult = checkForStructureOnBlock(x, y, z);
                    if(!Objects.equals(structureCheckResult, "null")) structures.put(new BlockPos(x,y,z), structureCheckResult);
                });
            });
        });
        readyToScan = true;
    }
    public String checkForStructureOnBlock(int x, int y, int z) {
        BlockPos check = new BlockPos(x,y,z);
        if (mc.theWorld.getBlockState(check).getBlock().equals(Blocks.air)) return "null";
        if (Config.structureScanner_dillo){
            if (LookupBlockUtils.blocksAbove(check, blocksSpiralPillarA, stonePropSpiralPillarA))
                return "SPIRAL-Pillar-A";
            if (LookupBlockUtils.blocksAbove(check, blocksThronePillarA, stonePropThronePillarA))
                return "THRONE-Pillar-A";
            if (LookupBlockUtils.blocksAbove(check, blocksThronePillarB, stonePropThronePillarB))
                return "THRONE-Pillar-B";
            if (LookupBlockUtils.blocksAbove(check, blocksThronePillarC, stonePropThronePillarC))
                return "THRONE-Pillar-C";
            if (LookupBlockUtils.blocksAbove(check, blocksSpiralPillarB, stonePropSpiralPillarB))
                return "SPIRAL-Pillar-B";
            if (LookupBlockUtils.blocksAbove(check, blocksSpiralPillarC, stonePropSpiralPillarC))
                return "SPIRAL-Pillar-C";
        } else {
            if (LookupBlockUtils.blocksAbove(check, blocksSpiralPillarA, stonePropSpiralPillarA))
                return "SPIRAL-Pillar-A";
            if (LookupBlockUtils.blocksAbove(check, blocksThronePillarA, stonePropThronePillarA))
                return "THRONE-Pillar-A";
            if (LookupBlockUtils.blocksAbove(check, blocksThronePillarB, stonePropThronePillarB))
                return "THRONE-Pillar-B";
            if (LookupBlockUtils.blocksAbove(check, blocksThronePillarC, stonePropThronePillarC))
                return "THRONE-Pillar-C";
            if (LookupBlockUtils.blocksAbove(check, blocksSpiralPillarB, stonePropSpiralPillarB))
                return "SPIRAL-Pillar-B";
            if (LookupBlockUtils.blocksAbove(check, blocksSpiralPillarC, stonePropSpiralPillarC))
                return "SPIRAL-Pillar-C";
            if (LookupBlockUtils.blocksAbove(check, precursorCityA, stonePropPrecursorCityA)) return "PRECURSOR-CITY-A";
            if (LookupBlockUtils.blocksAbove(check, minesOfDivanA, stonePropMinesOfDivanA)) return "MINES-OF-DIVAN-A";
            if (LookupBlockUtils.blocksAbove(check, jungleTempleA, stonePropJungleTempleA)) return "JUNGLE-TEMPLE-A";
            if (LookupBlockUtils.blocksAbove(check, goblinKingA, stonePropGoblinKingA)) return "GOBLIN-KING-A";
            if (LookupBlockUtils.blocksAbove(check, goblinQueenA, stonePropGoblinQueenA)) return "GOBLIN-QUEEN-A";
            if (LookupBlockUtils.blocksAbove(check, balA, stonePropBalA)) return "BAL-A";
        }
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