package me.aurora.client.features.misc;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.ClientMessages;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class AutoJoinSkyblock {
    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_L) && Config.AutoJoinSkyblock) {
            Aurora.mc.thePlayer.sendChatMessage("/play sb");
            ClientMessages.sendClientMessage("Auto Joining SkyBlock...");
        }
    }
}
