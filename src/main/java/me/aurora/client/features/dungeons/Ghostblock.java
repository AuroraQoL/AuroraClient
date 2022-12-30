package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.ClickEvent;
import me.dailydungeons.client.events.DailyDungeonsMessages;
import me.dailydungeons.client.utils.SkyBlockID;
import me.dailydungeons.client.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;


public class Ghostblock {


    Minecraft mc = Minecraft.getMinecraft();



  /*  @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (mc.gameSettings.isKeyDown(key))


    } */

  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
      if(Keyboard.isKeyDown(Keyboard.KEY_G) && Config.ghostblock) {
          MovingObjectPosition POSITIONOFBLOCK = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
          mc.theWorld.setBlockToAir(POSITIONOFBLOCK.getBlockPos());
      }
  }

}

