package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class VClip implements Module {
    public String name() {
        return "VClip";
    }

    public boolean toggled() {
        return Config.verticalClip;
    }
    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(BindUtils.getBindStatusPressed("VerticalClip") && Config.verticalClip) {
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY - Config.verticalClip_ParameterDistance, mc.thePlayer.posZ);
        }
    }
}
