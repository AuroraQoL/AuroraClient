package me.dailydungeons.client.features;

// implemented from https://github.com/ShadyAddons/ShadyAddons on GPLv3


import com.electronwill.nightconfig.core.AbstractCommentedConfig;
import com.electronwill.nightconfig.core.AbstractConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.TickEndEvent;
import me.dailydungeons.client.utils.Utils;
import me.dailydungeons.client.utils.getGuiName;
import me.dailydungeons.client.utils.invName;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class AutoHarp {

    boolean inHarp = false;

    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if(event.gui instanceof GuiChest && Utils.inSkyBlock && Config.AutoHarp) {
            if(getGuiName.getGuiName(event.gui).startsWith("Harp -")) {
                AbstractCommentedConfig lastInventory = null;
                lastInventory.clear();
                boolean inHarp = true;
            }
        }
    }
    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if(!inHarp || !Config.AutoHarp || mc.thePlayer == null) return;
        String inventoryName = invName.getInventoryName();
        if(inventoryName == null || !inventoryName.startsWith("Harp -")) inHarp = false;

        UnmodifiableConfig thisInventory = null;
        for(Slot slot : mc.thePlayer.openContainer.inventorySlots) {
            if(slot.getStack() != null) thisInventory.get((List<String>) slot.getStack().getItem());
        }

        AbstractConfig lastInventory = null;
        if(!lastInventory.toString().equals(thisInventory.toString())) {
            for(Slot slot : mc.thePlayer.openContainer.inventorySlots) {
                if(slot.getStack() != null && slot.getStack().getItem() instanceof ItemBlock && ((ItemBlock) slot.getStack().getItem()).getBlock() == Blocks.quartz_block) {
                    mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, slot.slotNumber, 2, 3, mc.thePlayer);
                    break;
                }
            }
        }

        lastInventory.clear();
        lastInventory.addAll(thisInventory);
    }

}
