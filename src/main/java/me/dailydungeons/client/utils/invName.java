package me.dailydungeons.client.utils;
// implemented from https://github.com/ShadyAddons/ShadyAddons on GPLv3

import net.minecraft.client.Minecraft;

public class invName {

    static Minecraft mc = Minecraft.getMinecraft();

    public static String getInventoryName() {
        if(mc.thePlayer == null || mc.theWorld == null) return "null";
        return mc.thePlayer.openContainer.inventorySlots.get(0).inventory.getName();
    }
}
