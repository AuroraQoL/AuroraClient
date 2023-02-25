package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.Keybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.function.Consumer;


public class Ghostblock  implements Module {

    public String name() {
        return "Ghostblock";
    }

    public boolean toggled() {
        return Config.ghostblocks;
    }


    Minecraft mc = Minecraft.getMinecraft();



  /*  @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (mc.gameSettings.isKeyDown(key))


    } */

  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
      if(Keybinds.GhostBlocks.isKeyDown() && Config.ghostblocks) {
          MovingObjectPosition POSITIONOFBLOCK = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
          mc.theWorld.setBlockToAir(POSITIONOFBLOCK.getBlockPos());
      }
  }
}

