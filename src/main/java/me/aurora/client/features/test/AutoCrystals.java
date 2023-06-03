package me.aurora.client.features.test;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.MouseUtils;
import me.aurora.client.utils.PacketUtils;
import me.aurora.client.utils.RotationUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

/**
 * @author jxee OctoSplash01 Gabagooooooooooool
 * @credit ShadyAddons (jxee)
 * @brief Auto F7 Crystal Pickup
 */
public class AutoCrystals implements Module {

    private static boolean teleported = false;
    private static boolean sentSneak = false;

    public String name() {
        return "AutoCrystal";
    }

    public boolean toggled() {
        return Config.autoCrystals;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (toggled() && !teleported && ConditionUtils.inSkyblock()) {
            if (mc.thePlayer.posX == 73.5 && mc.thePlayer.posZ == 14.5) {
                RotationUtils.Rotation rotation = RotationUtils.getRotationToBlock(new BlockPos(66.5, 237.5, 49.5));
                if (!sentSneak) {
                    setSneak(true);
                    PacketUtils.sendPacket(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
                    RotationUtils.look(rotation);
                }
                teleported = true;
            }
        }
        if (sentSneak) {
            MouseUtils.click(MouseUtils.ClickType.RIGHT);
            setSneak(false);
            PacketUtils.sendPacket(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        teleported = false;
    }

    private void setSneak(boolean status) {
        sentSneak = status;
        mc.thePlayer.movementInput.sneak = status;
    }
}
