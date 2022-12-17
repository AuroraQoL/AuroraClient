package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SelfBan {
    static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving != null && event.entityLiving instanceof EntityPlayer) {
            if (Config.SelfBan) {
                mc.thePlayer.setVelocity(0, 0, 0);
            }
        }
    }
}
