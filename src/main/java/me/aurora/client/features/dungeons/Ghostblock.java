package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;


public class    Ghostblock {
    Minecraft mc = Minecraft.getMinecraft();
    
    //po co ty to zmieniales, tak to trzeba caly czas klikac zamiast normalnie trzymac

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_G) && Config.ghostblock) {
            MovingObjectPosition POSITIONOFBLOCK = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
                mc.theWorld.setBlockToAir(POSITIONOFBLOCK.getBlockPos());
        }
    }

}

