package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SelfBan {
    static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            if (Config.SelfBan) {
                mc.thePlayer.setVelocity(0, 0, 0);
            }
        }
    }
}
