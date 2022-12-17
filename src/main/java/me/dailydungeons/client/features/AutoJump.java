package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.TickEndEvent;
import me.dailydungeons.client.utils.ColorUtils;
import me.dailydungeons.client.utils.FontUtil;
import me.dailydungeons.client.utils.PacketUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;

public class AutoJump {

    //  private static final Minecraft mc = Minecraft.getMinecraft();

    public static FontUtil mainFont;
    public static FontUtil jelloarray2;

    Font d;

    ResourceLocation c = new ResourceLocation("dailydungeons:font/rat.png");


    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving != null && event.entityLiving instanceof EntityPlayer) {

            if (Config.AutoJump) {
                if (Minecraft.getMinecraft().thePlayer.onGround) {
                    if (GameSettings.isKeyDown(mc.gameSettings.keyBindForward)) {
                        mc.thePlayer.jump();
                        mc.thePlayer.motionX *= 1.1708F;
                        mc.thePlayer.motionZ *= 1.1708F;
                    }
                }
            }
        }
    }
}

