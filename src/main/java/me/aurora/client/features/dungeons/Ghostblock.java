package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;


public class    Ghostblock {
    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_G) && Config.ghostblock) {
            MovingObjectPosition POSITIONOFBLOCK = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
                mc.theWorld.setBlockToAir(POSITIONOFBLOCK.getBlockPos());
        }
    }

}

