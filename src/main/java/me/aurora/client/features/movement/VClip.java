package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import static me.aurora.client.Aurora.mc;

public class VClip implements Module {
    public String name() {
        return "VClip";
    }

    public boolean toggled() {
        return Config.verticalClip;
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (BindUtils.isBindPressed("VClip") && toggled())
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY - Config.verticalClip_ParameterDistance, mc.thePlayer.posZ);
    }
}
