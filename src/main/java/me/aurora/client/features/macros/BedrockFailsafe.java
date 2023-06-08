package me.aurora.client.features.macros;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.RotationUtils;
import me.aurora.client.utils.iteration.LoopUtils;
import me.aurora.client.utils.rotation.Rotation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

import static me.aurora.client.Aurora.mc;
import static me.aurora.client.utils.MessageUtils.fakeMacroMessages;

public class BedrockFailsafe {

    public boolean hasBeenStopped = false;
    private int ticks;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if(Config.BedrockFailsafe && Config.f11Macro) {
            new Thread(() -> scanBlocks((int) mc.thePlayer.posX, (int) mc.thePlayer.posY, (int) mc.thePlayer.posZ), "bedrockCheck_thread").start();
        }
    }

    public void scanBlocks(int StartX, int StartY, int StartZ) {
        int playerX = Aurora.mc.thePlayer.getPosition().getX();
        int playerY = Aurora.mc.thePlayer.getPosition().getY();
        int playerZ = Aurora.mc.thePlayer.getPosition().getZ();
        LoopUtils.brLoop(StartX, StartY, StartZ, 5, (x, y, z) -> {
            BlockPos checked = new BlockPos(x, y, z);
            if (mc.theWorld.getBlockState(checked).getBlock() == Blocks.bedrock && !hasBeenStopped) {
                if (++ticks < 100) return;
                ticks = 0;
                mc.theWorld.playSound(playerX, playerY, playerZ, "random.orb", 100, 5, false);
                    MessageUtils.sendClientMessage("Bedrock Box Detected! Stopping macro..");
                    Config.f11Macro = false;
                    hasBeenStopped = true;
                    fakeMovements();
            }
        });
    }

    public void fakeMovements() {
        Config.f11Macro = false;
        F11.stopAllMovement();
        int chuj = MessageUtils.random.nextInt(fakeMacroMessages.length);
        String randomString = fakeMacroMessages[chuj];
        mc.thePlayer.sendChatMessage(randomString);

        new Thread(()-> {
            try {
                Thread.sleep(2000);
                mc.thePlayer.sendChatMessage(fakeMacroMessages[chuj]);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ).start();

        Random rYaw = new Random();
        Random rPitch = new Random();
        int randomYaw = rYaw.nextInt(180);
        int randomPitch = rPitch.nextInt(90);
        RotationUtils.smoothLook(new RotationUtils.Rotation(randomPitch, randomYaw), 20, null);
    }
}
