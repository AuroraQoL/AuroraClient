package me.aurora.client.features.troll;

import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SlayerDrops {
    @SubscribeEvent
    public void chatRecieved(ClientChatReceivedEvent event) {
        String msg = event.message.getUnformattedText();
        if (msg.contains("SLAIN") && ConditionUtils.inCoalMine()) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§d§lCRAZY RARE DROP! §r§7(§r§6Warden Heart§r§7) §r§b(+184% ✯ Magic Find)"));
        }
    }
}
