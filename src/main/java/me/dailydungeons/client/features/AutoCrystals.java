package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.TickEndEvent;
import me.dailydungeons.client.utils.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class AutoCrystals {

    //SHADY ADDONS

    static Minecraft mc = Minecraft.getMinecraft();
    private static boolean tped = false;
    private static boolean sentSneak = false;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (Config.AutoCrystals && !tped && inDungeon.inDungeon) {
            if (mc.thePlayer.posX == 272.5 && mc.thePlayer.posZ == 213.5) {
                RotationUtils.Rotation rotation = RotationUtils.getRotationToBlock(new BlockPos(
                        Config.crystalSide == 0 ? 280 : 264, 237, 248
                ));

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
