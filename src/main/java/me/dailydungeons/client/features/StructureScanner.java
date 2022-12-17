package me.dailydungeons.client.features;

import com.mojang.realmsclient.dto.WorldDownload;
import com.mojang.realmsclient.util.RealmsTasks;
import me.dailydungeons.client.DailyDungeons;
import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.DailyDungeonsMessages;
import me.dailydungeons.client.utils.SCUTIL;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.ForgeBlockStateV1;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static me.dailydungeons.client.config.Config.*;
import static me.dailydungeons.client.config.Config.scanrange;

/**
 * Heavily inspired by code from Danker's Skyblock Mod, used under GPL 3.0 license
 * <a href="https://github.com/bowser0000/SkyblockMod/blob/master/LICENSE">...</a>
 * @author bowser0000
 */

public class StructureScanner {
    private final HashMap<BlockPos, String> structures = new HashMap<>();
    boolean ready = true;
    boolean used = false;
    Minecraft mc = Minecraft.getMinecraft();


    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if ((((mc.theWorld.getTotalWorldTime()) % (4L * scanrange)) == 0) && scanner2) {
            if (ready && !used && mc.theWorld != null && mc.thePlayer != null) {
                ready = false;
                Thread scan = new Thread(() -> scanBlocks(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), "SCANNERKURWA");
                scan.start();

            }
        }

    }

    public void scanBlocks(Double StartX, Double StartY, Double StartZ) {


        ready = false;
        Double scanrange2 = (double) Config.scanrange2;
        for (int x = (int) (StartX - scanrange2); x < StartX + scanrange2; x++) {
            for (int y = (int) (StartY - scanrange2); y < StartY + scanrange2; y++) {
                for (int z = (int) (StartZ - scanrange2); z < StartZ + scanrange2; z++) {
                    Block tempPosOfRandomBlock = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                    //  mc.thePlayer.sendChatMessage("x");
                    String kekeke = checkForStructureOnBlock(x, y, z, tempPosOfRandomBlock);
                    if (!Objects.equals(kekeke, "null")) {
                        //              mc.thePlayer.sendChatMessage(kekeke);
                        structures.put(new BlockPos(x, y, z), kekeke);
                    }
                }
            }
        }
        ready = true;

    }

    public String checkForStructureOnBlock(int x, int y, int z, Block tempPosOfRandomBlock) {
        if ((tempPosOfRandomBlock.getBlockState().getBlock() == Blocks.stone && (mc.theWorld.getBlockState(new BlockPos(x, y, z)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.ANDESITE))) {
            //       mc.thePlayer.sendChatMessage("x");
            if (mc.theWorld.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == Blocks.stonebrick) {
                if (mc.theWorld.getBlockState(new BlockPos(x, y + 2, z)).getBlock() == Blocks.cobblestone) {
                    if (mc.theWorld.getBlockState(new BlockPos(x, y + 3, z)).getBlock() == Blocks.cobblestone) {
                        if (mc.theWorld.getBlockState(new BlockPos(x, y + 4, z)).getBlock() == Blocks.cobblestone) {

                            if (mc.theWorld.getBlockState(new BlockPos(x, y + 5, z)).getBlock() == Blocks.stone_stairs) {
                                //      mc.thePlayer.sendChatMessage("found struct -> " + String.format("%s %s %s", x, y, z)  );
                                return "Throne-Char-A";
                            }
                        }
                    }

                }
            }

/*                        String bcolor = null;

                        if (bcolor != null){
                            betabtr.put(new BlockPos(x, y, z), bcolor);
                        }*/


        } else if (tempPosOfRandomBlock.getBlockState().getBlock() == Blocks.tripwire_hook) {
            if (mc.theWorld.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == Blocks.stone && mc.theWorld.getBlockState(new BlockPos(x, y + 1, z)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE_SMOOTH) {
                if (mc.theWorld.getBlockState(new BlockPos(x, y + 2, z)).getBlock() == Blocks.stone && mc.theWorld.getBlockState(new BlockPos(x, y + 2, z)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE) {
                    if (mc.theWorld.getBlockState(new BlockPos(x, y + 3, z)).getBlock() == Blocks.stone && mc.theWorld.getBlockState(new BlockPos(x, y + 3, z)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE) {
                        if (mc.theWorld.getBlockState(new BlockPos(x, y + 4, z)).getBlock() == Blocks.stone && mc.theWorld.getBlockState(new BlockPos(x, y + 4, z)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE) {
                            if (mc.theWorld.getBlockState(new BlockPos(x, y + 5, z)).getBlock() == Blocks.stone && mc.theWorld.getBlockState(new BlockPos(x, y + 5, z)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE) {
                                if (mc.theWorld.getBlockState(new BlockPos(x, y + 6, z)).getBlock() == Blocks.stone && mc.theWorld.getBlockState(new BlockPos(x, y + 6, z)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE_SMOOTH) {
                                    if (mc.theWorld.getBlockState(new BlockPos(x, y + 7, z)).getBlock() == Blocks.stone && mc.theWorld.getBlockState(new BlockPos(x, y + 7, z)).getValue(BlockStone.VARIANT) == BlockStone.EnumType.ANDESITE_SMOOTH) {
                                        if (mc.theWorld.getBlockState(new BlockPos(x, y + 8, z)).getBlock() == Blocks.stone_brick_stairs) {
                                            return "Spiral-Pillar-A";
                                            // return "Throne-B";
                                            //     return "Throne-C";
                                        }                                               // return "Throne-B";
                                        //     return "Throne-C";
                                    }                                           // return "Throne-B";
                                    //     return "Throne-C";
                                }                                // return "Throne-B";
                                //     return "Throne-C";
                            }
                        }
                    }
                }
            }
        }
        return "null";

    }


    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (scanner2) {

            used = true;

            for (java.util.Map.Entry<BlockPos, String> aka : structures.entrySet()) {

                SCUTIL.renderBeaconText(String.format("%s - %d %d %d", aka.getValue(), aka.getKey().getX(), aka.getKey().getY(), aka.getKey().getZ()), aka.getKey(), event.partialTicks);

            }

            used = false;
        }
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        used = true;
        structures.clear();
        used = false;
    }


}
