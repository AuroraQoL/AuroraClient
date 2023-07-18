package me.aurora.client.features.dungeons;

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
import static me.aurora.client.utils.MouseUtils.ClickType.RIGHT;
import static net.minecraft.network.play.client.C0BPacketEntityAction.Action.START_SNEAKING;
import static net.minecraft.network.play.client.C0BPacketEntityAction.Action.STOP_SNEAKING;

/**
 * @author jxee OctoSplash01 Gabagooooooooooool
 * @version 2.0
 * @credit ShadyAddons (jxee)
 * @brief Auto Maxor Platform TP
 * @todo Check code
 */
public class AutoTank implements Module {

    private boolean teleported = false;

    public String name() {
        return "AutoTank";
    }

    public boolean toggled() {
        return Config.autoTank;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (toggled() && !teleported && ConditionUtils.inSkyblock() && mc.thePlayer.posX == 73.5 && mc.thePlayer.posZ == 14.5) {
            setSneakStatus(true);
            RotationUtils.look(RotationUtils.getRotationToBlock(new BlockPos(73.5, 224, 70.5)));
            teleported = true;
            MouseUtils.click(RIGHT);
            setSneakStatus(false);
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        teleported = false;
    }

    private void setSneakStatus(boolean status) {
        mc.thePlayer.movementInput.sneak = status;
        PacketUtils.sendPacket(new C0BPacketEntityAction(mc.thePlayer, status ? START_SNEAKING : STOP_SNEAKING));
    }
}
