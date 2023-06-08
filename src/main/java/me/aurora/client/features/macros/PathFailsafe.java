package me.aurora.client.features.macros;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.MessageUtils;

import me.aurora.client.utils.RotationUtils;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

import static me.aurora.client.utils.BlockDirUtils.*;

public class PathFailsafe {
    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if(Config.path_failsafe) {
                if (Config.f11Macro) {
                    if (getLeftBlock() == Blocks.dirt || getRightBlock() == Blocks.dirt ||
                            getRightTopBlock() == Blocks.dirt || getLeftTopBlock() == Blocks.dirt) {
                        MessageUtils.sendClientMessage("Detected path block, stopping macro and triggering failsafe..");
                    }
                }
        }
    }

    public void fake() {
        Random rPitch = new Random();
        Random rYaw = new Random();
        int randomPitch = rPitch.nextInt(90);
        int randomYaw = rYaw.nextInt(180);
        RotationUtils.smoothLook(new RotationUtils.Rotation(randomPitch, randomYaw), 20, null);
        Aurora.mc.thePlayer.sendChatMessage("wtf");
    }
}
