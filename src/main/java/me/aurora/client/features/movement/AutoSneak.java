package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.events.MotionUpdateEvent;
import me.aurora.client.utils.font.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

import static me.aurora.client.Aurora.mc;

public class AutoSneak {
    @SubscribeEvent
    public void MotionUpdateEvent(MotionUpdateEvent event) {
        if (event.onGround) {
            if (Config.AutoSneak) {
                if (Minecraft.getMinecraft().thePlayer.onGround) {
                    if (GameSettings.isKeyDown(mc.gameSettings.keyBindRight) || GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) || GameSettings.isKeyDown(mc.gameSettings.keyBindForward) || GameSettings.isKeyDown(mc.gameSettings.keyBindBack)) {
                        event.sneaking = true;
                    }
                }
            }
        }
    }
}