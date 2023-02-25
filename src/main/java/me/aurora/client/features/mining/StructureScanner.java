package me.aurora.client.features.mining;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.LookupBlockUtils;
import me.aurora.client.utils.ScannerUtils;
import me.aurora.client.utils.conditions.Conditions;
import me.aurora.client.utils.iteration.LoopUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 3.0
 * Structure Scanner.
 */

public class StructureScanner implements Module {

    public String name() {
        return "StructureScanner";
    }

    public boolean toggled() {
        return Config.structureScanner;
    }
    private final ConcurrentHashMap<BlockPos, String> structures = new ConcurrentHashMap<>();
    boolean readyToScan = true;

    private final HashSet<Check> checks = new HashSet<Check>(){{
        add(new Check(new Block[]{Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone_brick_stairs},
                new BlockStone.EnumType[]{BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.ANDESITE_SMOOTH, null},
                true, "SPIRAL-Pillar-A"));
        add(new Check(new Block[]{Blocks.double_stone_slab, Blocks.tripwire, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone},
                new BlockStone.EnumType[]{null, null, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE},
                true, "SPIRAL-Pillar-B"));
        add(new Check(new Block[]{Blocks.double_stone_slab, Blocks.double_stone_slab, Blocks.tripwire_hook, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone_brick_stairs},
                new BlockStone.EnumType[]{null, null, null, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE_SMOOTH, BlockStone.EnumType.ANDESITE_SMOOTH, null},
                true, "SPIRAL-Pillar-C"));
        add(new Check(new Block[]{Blocks.double_stone_slab, Blocks.double_stone_slab, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.air, Blocks.double_stone_slab, Blocks.double_stone_slab, Blocks.stone, Blocks.double_stone_slab, Blocks.stone, Blocks.air},
                new BlockStone.EnumType[]{null, null, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, null, null, null, null, null, BlockStone.EnumType.DIORITE, null, BlockStone.EnumType.DIORITE, null},
                true, "THRONE-Pillar-A"));
        add(new Check(new Block[]{Blocks.double_stone_slab, Blocks.stone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.double_stone_slab, Blocks.stone, Blocks.double_stone_slab, Blocks.double_stone_slab, Blocks.double_stone_slab},
                new BlockStone.EnumType[]{null, BlockStone.EnumType.DIORITE, null, null, null, BlockStone.EnumType.DIORITE, null, null, null},
                true, "THRONE-Pillar-B"));
        add(new Check(new Block[]{Blocks.stone, Blocks.stone_brick_stairs, Blocks.double_stone_slab, Blocks.stone, Blocks.stone, Blocks.stone_slab, Blocks.stone_slab},
                new BlockStone.EnumType[]{BlockStone.EnumType.ANDESITE_SMOOTH, null, null, BlockStone.EnumType.DIORITE, BlockStone.EnumType.DIORITE, null, null},
                true, "THRONE-Pillar-C"));
        add(new Check(new Block[]{Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, Blocks.stone_slab, Blocks.quartz_block, Blocks.stone_slab},
                new BlockStone.EnumType[]{null, null, null, null, null, null},
                false, "PRECURSOR_CITY-A"));
        add(new Check(new Block[]{Blocks.cobblestone, Blocks.cobblestone, Blocks.stone, Blocks.stone_brick_stairs, Blocks.stone_brick_stairs},
                new BlockStone.EnumType[]{null, null, BlockStone.EnumType.ANDESITE_SMOOTH, null, null},
                false, "MINES_OF_DIVAN-A"));
        add(new Check(new Block[]{Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_stairs},
                new BlockStone.EnumType[]{null, null, null, null, null, null, null, null, null, null, null},
                false, "JUNGLE_TEMPLE-A"));
        add(new Check(new Block[]{Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.stonebrick, Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.stonebrick, Blocks.stone},
                new BlockStone.EnumType[]{null, null, null, null, null, null, BlockStone.EnumType.ANDESITE_SMOOTH},
                false, "GOBLIN_KING-A"));
        add(new Check(new Block[]{Blocks.stonebrick, Blocks.stonebrick, Blocks.stonebrick, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stone, Blocks.stonebrick},
                new BlockStone.EnumType[]{null, null, null, BlockStone.EnumType.GRANITE, BlockStone.EnumType.GRANITE_SMOOTH, BlockStone.EnumType.GRANITE_SMOOTH, BlockStone.EnumType.GRANITE, BlockStone.EnumType.GRANITE_SMOOTH, null},
                false, "GOBLIN_QUEEN-A"));
        add(new Check(new Block[]{Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone, Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.cobblestone_wall},
                new BlockStone.EnumType[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                false, "BAL-A"));
    }};

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        int cachedRange = Config.structureScanner_ParameterRange;
        if ((((!Config.structureScanner_ParameterAggressiveScan) ? (((mc.theWorld.getTotalWorldTime()) % (4L * cachedRange)) == 0) : (((mc.theWorld.getTotalWorldTime()) % ((int) (Config.structureScanner_ParameterRange / 8))) == 0)) && Config.structureScanner && readyToScan && Conditions.inGame())) {
            readyToScan = false;
            CompletableFuture.runAsync(() -> {
                if (Config.structureScanner_freecam) {
                    scanBlocks((int) mc.getRenderViewEntity().posX, (int) mc.getRenderViewEntity().posY, (int) mc.getRenderViewEntity().posZ);
                } else {
                    scanBlocks((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ);
                }
            });
        }
        if ((((mc.theWorld.getTotalWorldTime()) % (16L * cachedRange)) == 0) && !readyToScan) readyToScan = true;
    }

    public void scanBlocks(int StartX, int StartY, int StartZ) {
        LoopUtils.brLoopBound(StartX, StartY, StartZ, Config.structureScanner_ParameterRange, (x, y, z) -> {
            String structureCheckResult = checkForStructureOnBlock(x, y, z);
            if (!Objects.equals(structureCheckResult, "null"))
                structures.put(new BlockPos(x, y, z), structureCheckResult);
        }, 31, 180);
        readyToScan = true;
    }

    public String checkForStructureOnBlock(int x, int y, int z) {
        BlockPos blockToCheck = new BlockPos(x, y, z);
        if (mc.theWorld.getBlockState(blockToCheck).getBlock().equals(Blocks.air)) return "null";
        for (Check check : checks) {
            if(Config.structureScanner_dillo && !check.isDillo()) continue;
            if(LookupBlockUtils.blocksAbove(blockToCheck, check.getBlocks(), check.getBlocks_stoneProp())) return check.getCheckname();
        }
        return "null";
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (Config.structureScanner)
            structures.forEach((key, value) -> ScannerUtils.renderBeaconText(String.format("%s - %d %d %d", value, key.getX(), key.getY(), key.getZ()), key, event.partialTicks));
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        structures.clear();
    }
}

class Check{
    private final Block[] blocks;
    private final BlockStone.EnumType[] blocks_stoneProp;
    private final boolean dillo;
    private final String checkname;

    public Block[] getBlocks() {
        return blocks;
    }

    public BlockStone.EnumType[] getBlocks_stoneProp() {
        return blocks_stoneProp;
    }

    public boolean isDillo() {
        return dillo;
    }

    public String getCheckname() {
        return checkname;
    }
    Check(Block[] blocks, BlockStone.EnumType[] blocks_stoneProp, boolean dillo, String checkname){
        this.blocks = blocks;
        this.blocks_stoneProp = blocks_stoneProp;
        this.dillo = dillo;
        this.checkname = checkname;
    }
}