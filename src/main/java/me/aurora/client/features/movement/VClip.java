package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class VClip {

    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_K) && Config.vclip) {
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY - Config.vclipdistance, mc.thePlayer.posZ);
        }
    }
}
