package me.aurora.client.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author jxee Gabagooooooooooool
 * @version 1.1
 * @credit ShadyAddons (jxee)
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
