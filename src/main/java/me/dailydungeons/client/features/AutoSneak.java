package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.MotionUpdateEvent;
import me.dailydungeons.client.utils.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class AutoSneak {

    public static FontUtil mainFont;
    public static FontUtil jelloarray2;

    Font d;

    ResourceLocation c = new ResourceLocation("dailydungeons:font/rat.png");


    Minecraft mc = Minecraft.getMinecraft();

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