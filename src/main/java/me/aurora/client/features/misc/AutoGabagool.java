package me.aurora.client.features.misc;

import me.aurora.client.utils.ClientMessages;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoGabagool {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.toString().contains("T")) ClientMessages.sendClientMessage("fr");
    }

}
