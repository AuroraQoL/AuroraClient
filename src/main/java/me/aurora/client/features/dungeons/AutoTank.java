package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.utils.KeybindUtils;
import me.aurora.client.utils.PacketUtils;
import me.aurora.client.utils.RotationUtils;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

/**
 * MODIFIED FROM SHADYADDONS
 * @author jxee
 */
public class AutoTank {
    private static boolean tped = false;
    private static boolean sentSneak = false;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (Config.AutoTank && !tped && Conditions.inSkyblock()) {
            if (mc.thePlayer.posX == 73.5 && mc.thePlayer.posZ == 14.5) {
                RotationUtils.Rotation rotation = RotationUtils.getRotationToBlock(new BlockPos(73.5, 224, 70.5));
                if (!sentSneak) {
                    mc.thePlayer.movementInput.sneak = true;
                    PacketUtils.sendPacket(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.START_SNEAKING));
                    RotationUtils.look(rotation);
                    sentSneak = true;
                }

                tped = true;
            }
        }

        if (sentSneak) {
            sentSneak = false;
            KeybindUtils.rightClick();
            mc.thePlayer.movementInput.sneak = false;
            PacketUtils.sendPacket(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        tped = false;
    }
}