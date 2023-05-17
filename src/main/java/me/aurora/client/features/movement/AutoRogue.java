package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ItemUtils;
import me.aurora.client.utils.Timer;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (toggled() && ConditionUtils.inSkyblock() && mc.thePlayer.inventory.currentItem == 0) {
            for (int i = 0; i < 8; i++) {
                ItemStack item = mc.thePlayer.inventory.getStackInSlot(i);
                if (ItemUtils.getSkyBlockID(item).matches("ROGUE_SWORD")) {
                    if (timer.timeBetween(30000, true)) {
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
}



