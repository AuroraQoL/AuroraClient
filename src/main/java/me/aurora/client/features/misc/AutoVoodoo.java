package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ClientMessages;
import me.aurora.client.utils.SkyBlockID;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class AutoVoodoo {

    static Minecraft mc = Minecraft.getMinecraft();

    private final ArrayList<String> swords = new ArrayList<>(Arrays.asList(
            "VALKYRIE"
    ));

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if (Config.AutoVoodoo && Conditions.inSkyblock() && mc.thePlayer.inventory.currentItem == 0 && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            if (mc.thePlayer.getHeldItem() != null) {
                String itemID = SkyBlockID.getSkyBlockID(mc.thePlayer.getHeldItem());
                if (swords.contains(itemID)) {
                    ItemStack item = mc.thePlayer.inventory.getCurrentItem();
                    if ((itemID.equals("VOODOO_DOLL"))) {
                        event.setCanceled(true);
                        mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, item);
                        mc.thePlayer.inventory.currentItem = 0;
                        ClientMessages.sendClientMessage("Auto Voodoo Used");
                    }
                }
            }
        }
    }
}
