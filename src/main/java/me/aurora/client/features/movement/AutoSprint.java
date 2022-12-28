package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.aurora.client.Aurora.mc;

public class AutoSprint {

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            if (Config.AutoSprint && Minecraft.getMinecraft().thePlayer.onGround && (GameSettings.isKeyDown(mc.gameSettings.keyBindRight) || GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) || GameSettings.isKeyDown(mc.gameSettings.keyBindForward) || GameSettings.isKeyDown(mc.gameSettings.keyBindBack))) {
                mc.thePlayer.setSprinting(true);
            }
        }
    }
}
