package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.InventoryUtils;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

public class AutoSellBz  implements Module {
    public String name() {
        return "AutoSellBZ";
    }

    public boolean toggled() {
        return Config.autoSellBz;
    }
    private int delay = 0;
    private boolean inBazaar = false;
    private boolean areYouSure = false;
    private boolean readyToSell = false;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(Config.autoSellBz && event.type == 0) {
            String message = event.message.getFormattedText().replaceAll("\u00a7.", "");
            if (message.equals("Your inventory is full!") | message.contains("Inventory full? Don't forget to check out your Storage inside the SkyBlock Menu!") && Config.autoSellBzType == 1) {
                readyToSell = true;
                mc.thePlayer.sendChatMessage("/bz");
                MessageUtils.sendClientMessage("Selling Items on Bazaar...");
            }
            else if (message.equals("[Bazaar] Executing instant sell...")) {
                readyToSell = false;
                mc.thePlayer.closeScreen();
                MessageUtils.sendClientMessage("Sold Items on Bazaar");
            }
            else if (message.equals("[Bazaar] You don't have anything to sell!")) {
                readyToSell = false;
                mc.thePlayer.closeScreen();
                MessageUtils.sendClientMessage("No Items to Sell");
            }
        }
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (Config.autoSellBz && Config.autoSellBzType == 0 && BindUtils.isBindPressed("AutoSellBazaar")) {
            readyToSell = true;
            mc.thePlayer.sendChatMessage("/bz");
            MessageUtils.sendClientMessage("Selling Items on Bazaar...");
        }
    }

    @SubscribeEvent
    public void onBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        String chestName = InventoryUtils.getGuiName(event.gui);
        inBazaar = chestName.contains("Bazaar");
        areYouSure = chestName.contains("Are you sure");
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (delay % 20 == 0) {
            if (mc.currentScreen instanceof GuiChest) {
                if (inBazaar && Config.autoSellBz && readyToSell) {
                    mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 47, 1, 0, mc.thePlayer);
                }
                else if (areYouSure && Config.autoSellBz && readyToSell) {
                    mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 11, 1, 0, mc.thePlayer);
                }
            }
        }
        delay++;
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        readyToSell = false;
    }
}