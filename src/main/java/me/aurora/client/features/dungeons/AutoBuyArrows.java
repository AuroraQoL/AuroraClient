package me.aurora.client.features.dungeons;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.InventoryUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
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
    public static boolean inSlimeBuyingMenu = false;
    public static boolean inSandMenu = false;
    public static boolean inIceMenu = false;
    public static boolean inIceBuyingMenu = false;
    int[] slimeSlots = {18, 20, 10, 10, 14};
    int[] armorShredSlots = {9, 33, 12, 10, 12};
    int[] icySlots = {9, 31, 12, 10, 14};
    private int ticks;

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(BindUtils.isBindPressed("AutoArrows")) {
            buying = true;
            mc.thePlayer.sendChatMessage("/bz");
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (buying && ConditionUtils.inSkyblock()) {
            if (++ticks < Config.auto_arrow_window_click_delay) return;
            ticks = 0;
            switch(Config.arrowtype) {
                case 0:
                    if(inBazaar) {
                        InventoryUtils.clickSlot(slimeSlots[0], 1, 0);
                        InventoryUtils.clickSlot(slimeSlots[1], 1, 0);
                    }
                    if(inSlimeMenu) {
                        InventoryUtils.clickSlot(slimeSlots[2], 1, 0);
                    }
                    if(inSlimeBuyingMenu) {
                        InventoryUtils.clickSlot(slimeSlots[4], 1, 0);
                    }
                    break;
                case 1:
                    if(inBazaar) {
                        InventoryUtils.clickSlot(armorShredSlots[0], 1, 0);
                        InventoryUtils.clickSlot(armorShredSlots[1], 1, 0);
                    }
                    if(inSandMenu) {
                        InventoryUtils.clickSlot(armorShredSlots[2], 1, 0);
                        InventoryUtils.clickSlot(armorShredSlots[3], 1, 0);
                        if(Config.enchanted_sand_stacks == 1) {
                            InventoryUtils.clickSlot(armorShredSlots[4], 1, 0);
                        } else {
                            for (int i = 0; i < Config.enchanted_sand_stacks; i++) {
                                InventoryUtils.clickSlot(armorShredSlots[4], 1, 0);
                            }
                        }
                    }
                    break;
                case 2: // ICY
                    if(inBazaar) {
                        InventoryUtils.clickSlot(icySlots[0], 1, 0);
                        InventoryUtils.clickSlot(icySlots[1], 1, 0);
                    }
                    if(inIceMenu) {
                        InventoryUtils.clickSlot(icySlots[2], 1, 0);
                        InventoryUtils.clickSlot(icySlots[3], 1, 0);
                    }
                    if(inIceBuyingMenu) {
                        InventoryUtils.clickSlot(icySlots[4], 1, 0);
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
        if(message.contains("Bought 64x Enchanted Sand") && Config.enchanted_sand_stacks == 1) {
            MessageUtils.sendClientMessage("Detected 1 enchanted sand in config, stopping buy.");
            buying = false;
            mc.thePlayer.closeScreen();
        }
        if(message.contains("Bought ") && message.contains("Slimeball") && Config.auto_arrow_inventories == 1) {
            MessageUtils.sendClientMessage("Detected 1 arrow inventory in config, stopping buy.");
            buying = false;
            mc.thePlayer.closeScreen();
        }
    }


    @SubscribeEvent
    public void onBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        String chestName = InventoryUtils.getGuiName(event.gui);
        inBazaar = chestName.contains("Bazaar");
        inSlimeMenu = chestName.contains("Slime Drops");
        inSlimeBuyingMenu = chestName.contains("Slimeball");
        inSandMenu = chestName.contains("Sand");
        inIceMenu = chestName.contains("Ice");
        inIceBuyingMenu = chestName.contains("Packed Ice");
    }
}
