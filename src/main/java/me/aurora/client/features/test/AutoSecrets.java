package me.aurora.client.features.test;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.MouseUtils;
import me.aurora.client.utils.PacketUtils;
import me.aurora.client.utils.RotationUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;
import static me.aurora.client.utils.MouseUtils.ClickType.RIGHT;

/**
 * @todo Remove (@OctoSplash01)
 */
public class AutoSecrets implements Module {

    public String name() {
        return "AutoSecrets";
    }

    public boolean toggled() {
        return Config.autoSecrets;
    }
    private static boolean sentSneak = false;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (toggled()) {
            if (mc.thePlayer.posX == 5 && mc.thePlayer.posZ == 0) {
                RotationUtils.Rotation rotation = RotationUtils.getRotationToBlock(new BlockPos(0, 5, 0));
                if (!sentSneak) {
                    mc.thePlayer.movementInput.sneak = true;
                    PacketUtils.sendPacket(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
                    RotationUtils.look(rotation);
                    sentSneak = true;
                }
            }
        }
        if (sentSneak) {
            sentSneak = false;
            MouseUtils.click(RIGHT);
            mc.thePlayer.movementInput.sneak = false;
            PacketUtils.sendPacket(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
        }
    }


}
