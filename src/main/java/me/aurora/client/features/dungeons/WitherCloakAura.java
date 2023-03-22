package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ItemUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.aurora.client.Aurora.mc;

/**
 * @author OctoSplash01 Gabagooooooooooool
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
    public void onInteract(PlayerInteractEvent event) {
        if (toggled() && ConditionUtils.inSkyblock() && mc.thePlayer.isInLava() && mc.thePlayer.getHeldItem() != null) {
            if (ItemUtils.getSkyBlockID(mc.thePlayer.getHeldItem()).equals("WITHER_CLOAK_SWORD")) {
                event.setCanceled(true);
                mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem());
                mc.thePlayer.inventory.currentItem = 0;
            }
        }
    }
}