package me.aurora.client.features.movement;


import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ClientMessages;
import me.aurora.client.utils.SkyBlockID;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoRogue implements Module {
    public String name() {
        return "RogueSwordAura";
    }

    public boolean toggled() {
        return Config.rogueSwordAura;
    }
    static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if(Config.rogueSwordAura && Conditions.inSkyblock() && mc.thePlayer.inventory.currentItem == 0 && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
                for (int i = 0; i < 8; i++) {
                    ItemStack item = mc.thePlayer.inventory.getStackInSlot(i);
                    String itemID = SkyBlockID.getSkyBlockID(item);
                    if ((itemID.equals("ROGUE_SWORD"))) {
                        event.setCanceled(true);
                        mc.thePlayer.inventory.currentItem = i;
                        mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, item);
                        mc.thePlayer.inventory.currentItem = 0;
                        ClientMessages.sendClientMessage("Used Rogue Sword");
                        break;
                    }
                }
            }
        }
    }




