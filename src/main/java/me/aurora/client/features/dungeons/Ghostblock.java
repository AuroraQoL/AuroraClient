package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;


public class Ghostblock  implements Module {

    public String name() {
        return "Ghostblock";
    }

    public boolean toggled() {
        return Config.ghostblocks;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if(BindUtils.getBindStatusDown("GhostBlocks") && Config.ghostblocks) {
            MovingObjectPosition POSITIONOFBLOCK = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
            mc.theWorld.setBlockToAir(POSITIONOFBLOCK.getBlockPos());
        }
    }
}
