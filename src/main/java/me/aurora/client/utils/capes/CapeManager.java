package me.aurora.client.utils.capes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.aurora.client.Aurora.mc;

public class CapeManager {
    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer && CapeDatabase.getInstance().userHasCape(event.entity.getName())) {
            mc.gameSettings.setModelPartEnabled(EnumPlayerModelParts.CAPE, true);
            mc.getRenderManager().getSkinMap().values().forEach(p -> p.addLayer(new CapeLayer(p)));
        }
    }
}
