package me.aurora.client.features.misc;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.MessageUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * @author OctoSplash01 Gabagooooooooooool
 * @version 2.0
 * @brief Automaticly Joins Skyblock
 */
public class AutoJoinSkyblock implements Module {
    public String name() {
        return "AutoJoinSkyblock";
    }

    public boolean toggled() {
        return Config.fastJoin;
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (BindUtils.isBindDown("FastJoin") && toggled()) {
            Aurora.mc.thePlayer.sendChatMessage("/play sb");
            MessageUtils.sendClientMessage("Auto Joining SkyBlock...");
        }
    }
}
