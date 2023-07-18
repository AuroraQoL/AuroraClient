package me.aurora.client.features.dungeons;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.InventoryUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.Timer;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.reflect.internal.Names;

import java.util.Set;


public class AutoBuyArrows {
    private final Minecraft mc = Aurora.mc;
    public static boolean buying = false;

    public static boolean inBazaar = false;
    public static boolean inSlimeMenu = false;
    public static boolean inInstaBuy = false;
    public static boolean inSandMenu = false;
    public static boolean inIceMenu = false;
    public static boolean inIceBuyingMenu = false;
    int[] slimeSlots = {12, 10, 14};
    int[] armorShredSlots = {9, 33, 11, 10, 12};
    int[] icySlots = {12, 10, 14};
    private int ticks;

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(BindUtils.isBindPressed("AutoArrows")) {
            buying = true;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if(++ticks < Config.auto_arrow_window_click_delay) return;
        ticks = 0;
        if (buying && ConditionUtils.inSkyblock()) {
            switch(Config.arrowtype) {
                case 0:
                    if(!inSlimeMenu) mc.thePlayer.sendChatMessage("/bz Slimeball");
                    if(inSlimeMenu && !inInstaBuy) {
                        InventoryUtils.clickSlot(slimeSlots[0], 0, 0);
                        InventoryUtils.clickSlot(slimeSlots[1], 0, 0);
                    }
                    if(inInstaBuy) {
                        InventoryUtils.clickSlot(14, 0, 0);
                        mc.thePlayer.closeScreen();

                    }
                    break;
                case 1:
                    if(!inSandMenu) mc.thePlayer.sendChatMessage("/bz Enchanted Sand");
                    if(inSandMenu && !inInstaBuy) {
                        InventoryUtils.clickSlot(armorShredSlots[2], 0, 0);
                        InventoryUtils.clickSlot(armorShredSlots[3], 0, 0);
                    }
                    if(inInstaBuy) {
                            InventoryUtils.clickSlot(armorShredSlots[4], 0, 0);
                            mc.thePlayer.closeScreen();
                    }
                    break;
                case 2: // ICY
                    mc.thePlayer.sendChatMessage("/bz Packed Ice");
                    if(inIceMenu && !inInstaBuy) {
                        InventoryUtils.clickSlot(icySlots[0], 0, 0);
                        InventoryUtils.clickSlot(icySlots[1], 0, 0);
                    }
                    if(inInstaBuy) {
                            InventoryUtils.clickSlot(icySlots[2], 0, 0);
                            mc.thePlayer.closeScreen();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if(message.contains("This server is restarting") || message.contains("This menu has been throttled") || message.contains("volatile market")) {
            new Thread(()-> {
                try {
                    buying = false;
                    mc.thePlayer.closeScreen();
                    MessageUtils.sendClientMessage("Detected interruption in buying, sending to island and trying again..");
                    Thread.sleep(500);
                    mc.thePlayer.sendChatMessage("/is");
                    Thread.sleep(5500);
                    buying = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        if(message.contains("afford this!") || message.contains("occurred in your connection")) {
            MessageUtils.sendClientMessage("Ran out of money! Stopping..");
            mc.thePlayer.closeScreen();
            buying = false;
        }
        if(message.contains("Bought 64x Enchanted Sand")) {
            buying = false;
            mc.thePlayer.closeScreen();
        }
        if(message.contains("Bought ") && message.contains("Slimeball")) {
            buying = false;
            mc.thePlayer.closeScreen();
        }
        if(message.contains("the server is about to restart!")) {
            MessageUtils.sendClientMessage("Server about to restart. stopping..");
            buying = false;
        }
    }


    @SubscribeEvent
    public void onBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        String chestName = InventoryUtils.getGuiName(event.gui);
        inBazaar = chestName.contains("Bazaar");
        inSlimeMenu = chestName.contains("Slimeball");
        inInstaBuy = chestName.contains("Instant Buy");
        inSandMenu = chestName.contains("Enchanted Sand");
        inIceMenu = chestName.contains("Packed Ice");
        inIceBuyingMenu = chestName.contains("Packed Ice");
    }
}