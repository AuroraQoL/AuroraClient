package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.events.DailyDungeonsMessages;
import me.dailydungeons.client.events.onPacket;
import me.dailydungeons.client.utils.invName;
import net.minecraft.client.Minecraft;
import me.dailydungeons.client.utils.PacketUtils;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class AutoJoinSkyblock {
    Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_L) && Config.AutoJoinSkyblock) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/play sb");
            DailyDungeonsMessages.sendModMessage("Auto Joining SkyBlock...");
        }
    }
}
