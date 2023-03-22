package me.aurora.client.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @credit ShadyAddons (jxee)
 * @author jxee Gabagooooooooooool
 * @version 1.1
 * @brief Item ID Utils
 */
public class ItemUtils {
    public static String getSkyBlockID(ItemStack item) {
        if (item != null) {
            NBTTagCompound extraAttributes = item.getSubCompound("ExtraAttributes", false);
            if (extraAttributes != null && extraAttributes.hasKey("id"))
                return extraAttributes.getString("id");
        }
        return null;
    }
}
