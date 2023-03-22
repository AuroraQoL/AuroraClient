package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.events.TickEndEvent;
import me.aurora.client.utils.conditions.ConditionUtils;
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

import java.util.List;

import static me.aurora.client.Aurora.mc;

/**
 * Modified code from (I think) ShadyAddons.
 * @todo Rework (@Gabagooooooooooool)
 * */
public class AutoSell implements Module {

    public String name() {
        return "AutoSell";
    }

    public boolean toggled() {
        return Config.autoSell;
    }
    private boolean sellable = false;

    private static final String[] dungeonShit = new String[]{
            "Training Weight",
            "Enchanted Rotten Flesh",
            "Enchanted Bone",
            "Journal Entry"
    };

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (sellable && toggled() && ConditionUtils.inSkyblock() && mc.currentScreen instanceof GuiChest) {
            List<Slot> chestInventory = ((GuiChest) mc.currentScreen).inventorySlots.inventorySlots;
            if(chestInventory.get(49).getStack() != null && chestInventory.get(49).getStack().getItem() != Item.getItemFromBlock(Blocks.barrier)) {
                for (Slot slot : mc.thePlayer.inventoryContainer.inventorySlots) {
                    if (properItem(slot.getStack())) {
                        mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 45 + slot.slotNumber, 2, 3, mc.thePlayer);
                        MessageUtils.sendClientMessage("Auto Selling Useless Items");
                        break;
                    }
                }
            }
        }
    }


    @SubscribeEvent
    public void onRenderGuiBackground(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (ConditionUtils.inSkyblock() && Config.autoSell) {
            if (event.gui instanceof GuiChest) {
                GuiChest guiChest = (GuiChest) event.gui;
                Container inventorySlots = guiChest.inventorySlots;

                IInventory inventory = inventorySlots.getSlot(0).inventory;
                sellable = inventory.getName().contains("Trades");
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

