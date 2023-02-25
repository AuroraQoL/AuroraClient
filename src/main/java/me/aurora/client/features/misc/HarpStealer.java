package me.aurora.client.features.misc;

import com.electronwill.nightconfig.core.AbstractCommentedConfig;
import com.electronwill.nightconfig.core.AbstractConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ClientMessages;
import me.aurora.client.events.TickEndEvent;
import me.aurora.client.utils.InventoryUtils;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class HarpStealer  implements Module {
    public String name() {
        return "HarpStealer";
    }

    public boolean toggled() {
        return Config.harpStealer;
    }
    boolean inHarp = false;

    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if(event.gui instanceof GuiChest && Conditions.inSkyblock() && Config.harpStealer) {
            if(InventoryUtils.getGuiName(event.gui).startsWith("Harp -")) {
                AbstractCommentedConfig lastInventory = null;
                lastInventory.clear();
                boolean inHarp = true;
                ClientMessages.sendClientMessage("Stealing Harp");
            }
        }
    }
    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if(!inHarp || !Config.harpStealer || mc.thePlayer == null) return;
        String inventoryName = InventoryUtils.getInventoryName();
        if(inventoryName == null || !inventoryName.startsWith("Harp -")) inHarp = false;

        UnmodifiableConfig thisInventory = null;
        for(Slot slot : mc.thePlayer.openContainer.inventorySlots) {
            if(slot.getStack() != null) thisInventory.get((List<String>) slot.getStack().getItem());
        }

        AbstractConfig lastInventory = null;
        if(!lastInventory.toString().equals(thisInventory.toString())) {
            for(Slot slot : mc.thePlayer.openContainer.inventorySlots) {
                if(slot.getStack() != null && slot.getStack().getItem() instanceof ItemBlock && ((ItemBlock) slot.getStack().getItem()).getBlock() == Blocks.quartz_block) {
                    break;
                }
            }
        }

        lastInventory.clear();
        lastInventory.addAll(thisInventory);
    }

}
