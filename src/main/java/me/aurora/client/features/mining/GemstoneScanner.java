package me.aurora.client.features.mining;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ScannerUtils;
import me.aurora.client.utils.conditions.Conditions;
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
import java.util.stream.IntStream;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * Gemstone Scanner (ESP)
 */

public class GemstoneScanner {
    private final ConcurrentHashMap<BlockPos, Color> espModeMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<BlockPos, String> textModeMap = new ConcurrentHashMap<>();
    boolean readyToScan = true;
    Minecraft mc = Minecraft.getMinecraft();
    private ConcurrentHashMap<BlockPos, Color> espModeTemportaryMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<BlockPos, String> textModeTemportaryMap = new ConcurrentHashMap<>();

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (Config.scanner && ((mc.theWorld.getTotalWorldTime() % (2L * Config.scanrange)) == 0) && readyToScan && Conditions.inGame()) {
            readyToScan = false;
            Thread scan = new Thread(() -> scanBlocks(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), "ScannerThread");
            scan.start();
        }

    }

    public void scanBlocks(Double StartX, Double StartY, Double StartZ) {
        if (!Config.experimentalExtendedScanning) {
            espModeMap.clear();
            textModeMap.clear();
        }
        IntStream.range((int) (StartX - Config.scanrange), (int) (StartX + Config.scanrange)).forEach(x -> {
            IntStream.range((int) (StartY - Config.scanrange), (int) (StartY + Config.scanrange)).forEach(y -> {
                IntStream.range((int) (StartZ - Config.scanrange), (int) (StartZ + Config.scanrange)).filter(z -> (mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.stained_glass_pane || mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.stained_glass)).forEach(z -> {
                    EnumDyeColor blockColor = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getValue(BlockStainedGlass.COLOR);
                    if (Config.colorsTwo == 2) {
                        switch (blockColor) {
                            case RED:
                                textModeMap.put(new BlockPos(x, y, z), "\247cR");
                                break;
                            case PURPLE:
                                textModeMap.put(new BlockPos(x, y, z), "\2475A");
                                break;
                            case LIME:
                                textModeMap.put(new BlockPos(x, y, z), "\247aJ");
                                break;
                            case LIGHT_BLUE:
                                textModeMap.put(new BlockPos(x, y, z), "\2479A");
                                break;
                            case ORANGE:
                                textModeMap.put(new BlockPos(x, y, z), "\2476A");
                                break;
                            case YELLOW:
                                textModeMap.put(new BlockPos(x, y, z), "\247eT");
                                break;
                            case MAGENTA:
                                textModeMap.put(new BlockPos(x, y, z), "\247dJ");
                                break;
                        }
                    } else {
                        switch (blockColor) {
                            case RED:
                                espModeMap.put(new BlockPos(x, y, z), new Color(165, 59, 62, 150));
                                break;
                            case PURPLE:
                                espModeMap.put(new BlockPos(x, y, z), new Color(180, 12, 246, 150));
                                break;
                            case LIME:
                                espModeMap.put(new BlockPos(x, y, z), new Color(177, 250, 69, 150));
                                break;
                            case LIGHT_BLUE:
                                espModeMap.put(new BlockPos(x, y, z), new Color(19, 119, 217, 150));
                                break;
                            case ORANGE:
                                espModeMap.put(new BlockPos(x, y, z), new Color(246, 131, 0, 150));
                                break;
                            case YELLOW:
                                espModeMap.put(new BlockPos(x, y, z), new Color(198, 183, 45, 150));
                                break;
                            case MAGENTA:
                                espModeMap.put(new BlockPos(x, y, z), new Color(223, 23, 173, 150));
                                break;
                        }
                    }
                });
            });
        });
        readyToScan = true;
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (Config.scanner) {
            if (Config.colorsTwo == 0) {
                if (espModeMap.entrySet().size() != 0) espModeTemportaryMap = espModeMap;
                espModeTemportaryMap.entrySet().stream().filter(b -> {
                    return (mc.theWorld.getBlockState(b.getKey()).getBlock() != Blocks.air);
                }).forEach(b -> {
                    ScannerUtils.drawOutlinedBoundingBox(b.getKey(), b.getValue(), Config.thicc, event.partialTicks);
                });
            } else if (Config.colorsTwo == 1) {
                if (espModeMap.entrySet().size() != 0) espModeTemportaryMap = espModeMap;
                espModeTemportaryMap.entrySet().stream().filter(b -> {
                    return (mc.theWorld.getBlockState(b.getKey()).getBlock() != Blocks.air);
                }).forEach(b -> {
                    ScannerUtils.highlightBlock(b.getKey(), b.getValue(), event.partialTicks);
                });
            } else {
                if (textModeMap.entrySet().size() != 0) textModeTemportaryMap = textModeMap;
                textModeTemportaryMap.entrySet().stream().filter(b -> {
                    return (mc.theWorld.getBlockState(b.getKey()).getBlock() != Blocks.air);
                }).forEach(b -> {
                    ScannerUtils.renderBeaconText(b.getValue(), b.getKey(), event.partialTicks);
                });
            }
        }
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        espModeMap.clear();
        textModeMap.clear();
    }
    
}

