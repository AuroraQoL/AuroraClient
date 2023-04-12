package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ItemUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

/**
 * @credit ShadyAddons (jxee)
 * @author jxee OctoSplash01 Gabagooooooooooool
 * @version 2.0
 * @brief Automaticly uses Wither Cloak
 * @todo Find more ways to optimize the code (Held Item?)
 */
public class WitherCloakAura implements Module {

    public String name() {
        return "WitherCloakAura";
    }

    public boolean toggled() {
        return Config.witherCloakAura;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (toggled() && ConditionUtils.inSkyblock() && mc.thePlayer.isInLava() && mc.thePlayer.inventory.currentItem == 0) {
            for (int i = 0; i < 8; i++) {
                ItemStack item = mc.thePlayer.inventory.getStackInSlot(i);
                if (ItemUtils.getSkyBlockID(item).matches("WITHER_CLOAK_SWORD")) {
                    event.setCanceled(true);
                    mc.thePlayer.inventory.currentItem = i;
                    mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, item);
                    mc.thePlayer.inventory.currentItem = 0;
                    break;
                }
            }
        }
    }
}