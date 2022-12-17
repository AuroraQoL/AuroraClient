package me.dailydungeons.client.features;

import ibxm.Player;
import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.DailyDungeonsMessages;
import me.dailydungeons.client.utils.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import static net.minecraft.init.Blocks.lapis_block;

public class AutoSecrets {

    static Minecraft mc = Minecraft.getMinecraft();
    private static boolean sentSneak = false;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (Config.AutoSecrets) {
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
            KeybindUtils.rightClick();
            mc.thePlayer.movementInput.sneak = false;
            PacketUtils.sendPacket(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SNEAKING));
        }
    }


}
