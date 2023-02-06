package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ClientMessages;
import me.aurora.client.utils.InventoryUtils;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

public class AutoSellBz {

    private int delay = 0;
    private boolean inBazaar = false;
    private boolean areYouSure = false;
    private boolean readyToSell = false;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(Config.autoSellBz && event.type == 0) {
            String message = event.message.getFormattedText().replaceAll("\u00a7.", "");
            if(message.equals("Your inventory is full!")) {
                mc.thePlayer.sendChatMessage("/bz");
                ClientMessages.sendClientMessage("Selling Items on Bazaar...");
                readyToSell = true;
            }
        }
    }

    @SubscribeEvent
    public void onBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        String chestName = InventoryUtils.getGuiName(event.gui);
        inBazaar = chestName.contains("Bazaar");
        areYouSure = chestName.contains("Are you sure?");
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (delay % 5 == 0) {
            if (mc.currentScreen instanceof GuiChest) {
                if (inBazaar && Config.autoSellBz && readyToSell) {
                    mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 48, 2, 3, mc.thePlayer);
                }
                else if (areYouSure && Config.autoSellBz) {
                    mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 12, 2, 3, mc.thePlayer);
                    readyToSell = false;
                }
            }
        }
        delay++;
    }
}
