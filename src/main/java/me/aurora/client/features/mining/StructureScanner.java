package me.aurora.client.features.mining;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BlockRenderUtils;
import me.aurora.client.utils.CalculationUtils;
import me.aurora.client.utils.LookupBlockUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import me.aurora.client.utils.iteration.LoopUtils;
import me.cephetir.bladecore.core.listeners.SkyblockIsland;
import me.cephetir.bladecore.core.listeners.SkyblockListener;
import me.cephetir.communistscanner.CommunistScanners;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static me.aurora.client.Aurora.mc;
import static me.aurora.client.config.Config.scanType;

/**
 * @author Gabagooooooooooool
 * @version 4.0
 * Structure Scanner.
 */

public class StructureScanner implements Module {

    private static final ConcurrentLinkedQueue<Structure> structures = new ConcurrentLinkedQueue<>();
    private final Set<BlockPos> checked = ConcurrentHashMap.newKeySet();
    private final HashSet<Check> checks = new HashSet<Check>() {{
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
        add(new Check(new Block[]{Blocks.stained_hardened_clay, Blocks.stained_hardened_clay, Blocks.wool, Blocks.air, Blocks.air, Blocks.stained_hardened_clay, Blocks.stained_hardened_clay, Blocks.stained_hardened_clay, Blocks.air, Blocks.stained_hardened_clay, Blocks.stained_hardened_clay},
                new BlockStone.EnumType[]{null, null, null, null, null, null, null, null, null, null, null},
                false, "GOLDEN_DRAGON-A"));
    }};
    volatile boolean readyToScan = true;

    public static void addStructure(String server, BlockPos checkPos, String checkName, boolean remote) {
        if (structures.stream().anyMatch(structure -> structure.getServer().equals(server) && structure.getName().equals(checkName)))
            return;
        for (Structure structure : structures)
            if (structure.getServer().equals(server) && structure.getName().equals(checkName) && CalculationUtils.blockEuclideanDistance(checkPos, structure.getPos()) < 16)
                return;

        if (!remote)
            CommunistScanners.addStructure(server, checkName, checkPos, mc.theWorld.getWorldTime());
        structures.add(new Structure(server, checkName, checkPos));
        if (SkyblockListener.INSTANCE.getLocraw() != null && mc.theWorld != null && scanType % 2 == 1 && SkyblockListener.INSTANCE.getLocraw().getServer().equals(server))
            MessageUtils.sendMultilineClientMessage(
                    "* * * * * * * * * *",
                    "\247lFOUND STRUCTURE",
                    String.format("%s - %d %d %d", checkName, checkPos.getX(), checkPos.getY(), checkPos.getZ()),
                    "* * * * * * * * * *");
    }

    public String name() {
        return "StructureScanner";
    }

    public boolean toggled() {
        return Config.structureScanner;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (SkyblockListener.INSTANCE.getIsland() != SkyblockIsland.CrystalHollows) return;
        int range = Config.structureScanner_ParameterRange;
        long time = mc.theWorld.getTotalWorldTime();
        if (time % ((Config.structureScanner_ParameterAggressiveScan) ? (range / 8) : (range * 4L)) == 0 && Config.structureScanner && readyToScan && ConditionUtils.inGame())
            CompletableFuture.runAsync(() -> scanBlocks(toCoordArray(Config.structureScanner_freecam ? mc.getRenderViewEntity() : mc.thePlayer)));
        if ((time % (16L * range)) == 0 && !readyToScan) readyToScan = true;
    }

    public void scanBlocks(int[] pos) {
        readyToScan = false;
        String server;
        if (SkyblockListener.INSTANCE.getLocraw() != null) {
            server = SkyblockListener.INSTANCE.getLocraw().getServer();
        } else {
            server = null;
        }
        LoopUtils.brLoopBoundChunk(pos[0], pos[1], pos[2], Config.structureScanner_ParameterRange, (x, y, z) -> {
            String structureCheckResult = checkForStructureOnBlock(x, y, z);
            if (!Objects.equals(structureCheckResult, "null")) {
                BlockPos strPos = new BlockPos(x, y, z);
                checked.add(strPos);
                StructureScanner.addStructure(server, strPos, structureCheckResult, false);
            }
        }, 31, 180);
        readyToScan = true;
    }

    public String checkForStructureOnBlock(int x, int y, int z) {
        BlockPos blockToCheck = new BlockPos(x, y, z);
        if (mc.theWorld.getBlockState(blockToCheck).getBlock().equals(Blocks.air)) return "null";
        for (Check check : checks) {
            if (Config.structureScanner_dillo && !check.isDillo()) continue;
            if (LookupBlockUtils.blocksAbove(blockToCheck, check.getBlocks(), check.getBlocks_stoneProp()))
                return check.getCheckname();
        }
        return "null";
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (Config.structureScanner && SkyblockListener.INSTANCE.getIsland() == SkyblockIsland.CrystalHollows && scanType >= 2)
            structures.forEach(structure -> {
                if (SkyblockListener.INSTANCE.getLocraw() != null && SkyblockListener.INSTANCE.getLocraw().getServer().equals(structure.getServer()))
                    BlockRenderUtils.renderBeaconText(String.format("\247l%s\247r - %d %d %d", structure.getName(), structure.getPos().getX(), structure.getPos().getY(), structure.getPos().getZ()), structure.getPos(), event.partialTicks);
            });
    }

    private int[] toCoordArray(Entity e) {
        return new int[]{(int) e.posX, (int) e.posY, (int) e.posZ};
    }
}

@Getter
@AllArgsConstructor
class Structure {
    private final String server;
    private final String name;
    private final BlockPos pos;
}

@Getter
@AllArgsConstructor
class Check {
    private final Block[] blocks;
    private final BlockStone.EnumType[] blocks_stoneProp;
    private final boolean dillo;
    private final String checkname;
}
