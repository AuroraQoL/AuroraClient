package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.DailyDungeonsMessages;
import me.dailydungeons.client.utils.SkyBlockID;
import me.dailydungeons.client.utils.Utils;
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
        if (Config.AutoVoodoo && Utils.inSkyBlock && mc.thePlayer.inventory.currentItem == 0 && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
            if (mc.thePlayer.getHeldItem() != null) {
                String itemID = SkyBlockID.getSkyBlockID(mc.thePlayer.getHeldItem());
                if (swords.contains(itemID)) {
                    ItemStack item = mc.thePlayer.inventory.getCurrentItem();
                    if ((itemID.equals("VOODOO_DOLL"))) {
                        event.setCanceled(true);
                        mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, item);
                        mc.thePlayer.inventory.currentItem = 0;
                        DailyDungeonsMessages.sendModMessage("Auto Voodoo Used");
                    }
                }
            }
        }
    }
}
