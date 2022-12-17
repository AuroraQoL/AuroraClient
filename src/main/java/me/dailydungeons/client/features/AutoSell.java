package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.DailyDungeonsMessages;
import me.dailydungeons.client.events.TickEndEvent;
import me.dailydungeons.client.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;


public class AutoSell {

    private int tickCount = 0;

    private boolean sellable = false;


    private static final String[] dungeonShit = new String[]{
            "Training Weight",
            "Enchanted Rotten Flesh",
            "Enchanted Bone",
            "Journal Entry"
    };

    Minecraft mc = Minecraft.getMinecraft();


    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (sellable && Config.AutoSell && Utils.inSkyBlock && mc.currentScreen instanceof GuiChest) {
            List<Slot> chestInventory = ((GuiChest) mc.currentScreen).inventorySlots.inventorySlots;
            if(chestInventory.get(49).getStack() != null && chestInventory.get(49).getStack().getItem() != Item.getItemFromBlock(Blocks.barrier)) {
                for(Slot slot : mc.thePlayer.inventoryContainer.inventorySlots) {
                    if(properItem(slot.getStack())) {
                        mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 45 + slot.slotNumber, 2, 3, mc.thePlayer);
                        DailyDungeonsMessages.sendModMessage("Auto Selling Useless Items");
                        break;

                    }
                }
            }
            tickCount++;
        }
    }


    @SubscribeEvent
    public void onRenderGuiBackground(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (Utils.inSkyBlock && Config.AutoSell) {
            if (event.gui instanceof GuiChest) {
                GuiChest guiChest = (GuiChest) event.gui;
                Container inventorySlots = guiChest.inventorySlots;

                IInventory inventory = inventorySlots.getSlot(0).inventory;
                if (inventory.getName().contains("Trades")) {
                    sellable = true;
                } else {
                    sellable = false;
                }
            }
        }


    }

    public boolean properItem(ItemStack dosprwdzenia) {
        boolean temp = false;
        for (String s : dungeonShit) {
            if (dosprwdzenia.getDisplayName().contains(s)) {
                return true;
            }

        }
        return temp;
    }

}

