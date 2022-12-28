package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.utils.font.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

import static me.aurora.client.Aurora.mc;

public class InventoryWalk {

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            if (Config.InventoryWalk) {
                if (Minecraft.getMinecraft().thePlayer.onGround) {
                    if (GameSettings.isKeyDown(mc.gameSettings.keyBindForward)) {
                        mc.thePlayer.moveForward = 5F;
                    }
                }
            }
        }
    }

}
