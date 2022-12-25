package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import static me.dailydungeons.client.config.Config.vclipdistance;

public class vclip {

    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_K) && Config.vclip) {
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY - vclipdistance, mc.thePlayer.posZ);
        }
    }
}
