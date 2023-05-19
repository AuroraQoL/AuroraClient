package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ItemUtils;
import me.aurora.client.utils.Timer;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.aurora.client.Aurora.mc;

/**
 * @credit ShadyAddons (jxee)
 * @author jxee OctoSplash01 Gabagooooooooooool
 * @version 1.1
 * @brief Rogue Sword Aura
 */
public class AutoRogue implements Module {
    public String name() {
        return "RogueSwordAura";
    }

    public boolean toggled() {
        return Config.rogueSwordAura;
    }

    Timer timer = new Timer();

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer && toggled() && ConditionUtils.inSkyblock() && (timer.timeBetween(30000, true))) {
            useItem("ROGUE_SWORD");
        }
    }

    public static void useItem(String itemId) {
        for(int i = 0; i < 8; i++) {
            ItemStack item = mc.thePlayer.inventory.getStackInSlot(i);
            if(itemId.equals(ItemUtils.getSkyBlockID(item))) {
                int previousItem = mc.thePlayer.inventory.currentItem;
                mc.thePlayer.inventory.currentItem = i;
                mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, item);
                mc.thePlayer.inventory.currentItem = previousItem;
                return;
            }
        }
    }
}




