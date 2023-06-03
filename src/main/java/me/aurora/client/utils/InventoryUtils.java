package me.aurora.client.utils;
// implemented from https://github.com/ShadyAddons/ShadyAddons on GPLv3

import me.aurora.client.Aurora;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;

/**
 * MODIFIED FROM SHADYADDONS
 *
 * @author jxee
 */

public class InventoryUtils {
    public static String getGuiName(GuiScreen gui) {
        if (gui instanceof GuiChest) {
            return ((ContainerChest) ((GuiChest) gui).inventorySlots).getLowerChestInventory().getDisplayName().getUnformattedText();
        }
        return "";
    }

    public static String getInventoryName() {
        if (!ConditionUtils.inGame()) return "null";
        return Aurora.mc.thePlayer.openContainer.inventorySlots.get(0).inventory.getName();
    }

    public static void clickSlot(int slotId, int mouseButton, int mode) {
        Aurora.mc.playerController.windowClick(Aurora.mc.thePlayer.openContainer.windowId, slotId, mouseButton, mode, Aurora.mc.thePlayer);
    }
}
