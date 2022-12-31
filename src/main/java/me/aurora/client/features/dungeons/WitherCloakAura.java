package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.utils.SkyBlockID;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WitherCloakAura {

    static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if (Config.witherCloakAura && Conditions.inSkyblock() && mc.thePlayer.isInLava()) {
            if (mc.thePlayer.getHeldItem() != null) {
                String itemID = SkyBlockID.getSkyBlockID(mc.thePlayer.getHeldItem());
                ItemStack item = mc.thePlayer.inventory.getCurrentItem();
                if ((itemID.equals("WITHER_CLOAK_SWORD"))) {
                    event.setCanceled(true);
                    mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, item);
                    mc.thePlayer.inventory.currentItem = 0;
                }
            }
        }
    }
}