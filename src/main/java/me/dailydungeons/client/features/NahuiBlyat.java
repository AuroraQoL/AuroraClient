package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.utils.SCUTIL;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;

import static me.dailydungeons.client.config.Config.*;

public class NahuiBlyat {
    private final ConcurrentHashMap<BlockPos, Color> btrneu = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<BlockPos, String> betabtr = new ConcurrentHashMap<>();
    Block[] gemstoneBlocks = {Blocks.stained_glass, Blocks.stained_glass_pane};
    EnumDyeColor[] gemstoneColors = {EnumDyeColor.LIGHT_BLUE, EnumDyeColor.RED, EnumDyeColor.YELLOW, EnumDyeColor.LIME, EnumDyeColor.PURPLE, EnumDyeColor.MAGENTA};
    boolean ready = true;
    boolean used = false;
    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (scanner) {
            if ((((mc.theWorld.getTotalWorldTime()) % (2L *scanrange)) == 0)) {
                if (ready && !used && mc.theWorld != null && mc.thePlayer != null) {
                    ready = false;
                    Thread scan = new Thread(() -> scanBlocks(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), "SCANNERKURWA");
                    scan.start();

                }
            }
        }
    }


    public void scanBlocks(Double StartX, Double StartY, Double StartZ) {
        ready = false;
        if (!experimentalExtendedScanning) {
            btrneu.clear();
            betabtr.clear();
        }
            for (int x = (int) (StartX - scanrange); x < StartX + scanrange; x++) {
                for (int y = (int) (StartY - scanrange); y < StartY + scanrange; y++) {
                    for (int z = (int) (StartZ - scanrange); z < StartZ + scanrange; z++) {
                        Block tempPosOfRandomBlock = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                       if ((tempPosOfRandomBlock.getBlockState().getBlock() == Blocks.stained_glass || tempPosOfRandomBlock.getBlockState().getBlock() == Blocks.stained_glass_pane) && Config.colorsTwo == 2) {

                           EnumDyeColor sd = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getValue(BlockStainedGlass.COLOR);
                           String bcolor = null;
                           if (sd == EnumDyeColor.RED) {
                               bcolor = "\247cR";
                           } else if (sd == EnumDyeColor.PURPLE) {
                               bcolor = "\2475A";
                           } else if (sd == EnumDyeColor.LIME) {
                               bcolor = "\247aJ";
                           } else if (sd == EnumDyeColor.LIGHT_BLUE) {
                               bcolor = "\2479A";
                           } else if (sd == EnumDyeColor.ORANGE) {
                               bcolor = "\2476A";
                           } else if (sd == EnumDyeColor.YELLOW) {
                               bcolor = "\247eT";
                           } else if (sd == EnumDyeColor.MAGENTA) {
                               bcolor = "\247dJ";
                           }
                           if (bcolor != null) {
                               betabtr.put(new BlockPos(x, y, z), bcolor);
                           }

                       } else if ((tempPosOfRandomBlock.getBlockState().getBlock() == Blocks.stained_glass || tempPosOfRandomBlock.getBlockState().getBlock() == Blocks.stained_glass_pane) ) {
                           //         if (tempPosOfRandomBlock.getBlockState().getBlock() == Blocks.stained_glass) {

                                EnumDyeColor sd = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getValue(BlockStainedGlass.COLOR);
                            Color bcolor = null;
                            if (sd == EnumDyeColor.RED) {
                                bcolor = new Color(165, 59, 62, 150);
                            } else if (sd == EnumDyeColor.PURPLE) {
                                bcolor = new Color(180, 12, 246, 150);
                            } else if (sd == EnumDyeColor.LIME) {
                                bcolor = new Color(177, 250, 69, 150);
                            } else if (sd == EnumDyeColor.LIGHT_BLUE) {
                                bcolor = new Color(19, 119, 217, 150);
                            } else if (sd == EnumDyeColor.ORANGE) {
                                bcolor = new Color(246, 131, 0, 150);
                            } else if (sd == EnumDyeColor.YELLOW) {
                                bcolor = new Color(198, 183, 45, 150);
                            } else if (sd == EnumDyeColor.MAGENTA) {
                                bcolor = new Color(223, 23, 173, 150);
                            }
                            if (bcolor != null) {
                                btrneu.put(new BlockPos(x, y, z), bcolor);
                            }
                        }
                    }
                }
        }
            ready = true;

    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if(scanner && Config.colorsTwo != 2) {
            used = true;
            for (ConcurrentHashMap.Entry<BlockPos, Color> b : btrneu.entrySet()) {
                if ( mc.theWorld.getBlockState(b.getKey()).getBlock() != Blocks.air && Config.colorsTwo==0) {
                    SCUTIL.drawOutlinedBoundingBox(b.getKey(), b.getValue(), thicc, event.partialTicks);

                } else if ( mc.theWorld.getBlockState(b.getKey()).getBlock() != Blocks.air) {
                    SCUTIL.highlightBlock(b.getKey(), b.getValue(), event.partialTicks);
                }

            }
            used = false;
       } else if (scanner){
            used = true;
            for (ConcurrentHashMap.Entry<BlockPos, String> b : betabtr.entrySet()) {
                SCUTIL.renderBeaconText( b.getValue(), b.getKey(), event.partialTicks);
            }
            used = false;
        }
    }




    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        used = true;
        btrneu.clear();
        used = false;
    }


}

