package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ItemUtils;
import me.aurora.client.utils.PacketUtils;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static me.aurora.client.Aurora.mc;

/**
 * @author OctoSplash01 Gabagooooooooooool
 * @version 1.1
 * @brief No Slowdown
 */
public class NoSlow implements Module {
    // Since set access is O(n) it's better to use it instead of list which access time is higher.
    // Using String[] would also be a good choice but would make a lot of mess.
    private final Set<String> validItems = new HashSet<>(Arrays.asList(
            "HYPERION",
            "VALKYRIE",
            "SCYLLA",
            "ASTRAEA",
            "ASPECT_OF_THE_END",
            "ROGUE_SWORD"));

    public String name() {
        return "NoSlow";
    }

    public boolean toggled() {
        return Config.noSlowdown;
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if (toggled() && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR && validItems.contains(ItemUtils.getSkyBlockID(mc.thePlayer.getHeldItem()))) {
            event.setCanceled(true);
            if (mc.gameSettings.keyBindUseItem.isKeyDown())
                PacketUtils.sendPacket(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.getHeldItem(), 0, 0, 0));
        }
    }
}
