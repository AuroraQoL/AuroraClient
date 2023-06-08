package me.aurora.client.features.macros;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.MessageUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

import static me.aurora.client.Aurora.mc;

public class RotationFailsafe {

    public int playerX = mc.thePlayer.getPosition().getX();
    public int playerY = mc.thePlayer.getPosition().getY();
    public int playerZ = mc.thePlayer.getPosition().getZ();


    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if(event.player != null && Config.rotation_check && Config.f11Macro) {
            if (Aurora.mc.thePlayer.rotationPitch > Config.f11pitch + Config.rotation_sensitivity
            || Aurora.mc.thePlayer.rotationPitch < Config.f11pitch - Config.rotation_sensitivity
            || Aurora.mc.thePlayer.rotationYaw > Config.f11yaw + Config.rotation_sensitivity
            || Aurora.mc.thePlayer.rotationYaw < Config.f11yaw - Config.rotation_sensitivity) {
                failsafe();
            }
        }
    }

    public void failsafe() {
        new Thread(() -> {
            try {
                mc.theWorld.playSound(playerX, playerY, playerZ, "random.orb", 100, 5, false);
                F11.stopAllMovement();
                Thread.sleep(1500);
                Random random = new Random();
                int ran = random.nextInt(MessageUtils.fakeMacroMessages.length);
                Aurora.mc.thePlayer.sendChatMessage(MessageUtils.fakeMacroMessages[ran]);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
