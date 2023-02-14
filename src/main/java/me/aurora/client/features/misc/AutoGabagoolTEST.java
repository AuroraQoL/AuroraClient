package me.aurora.client.features.misc;

import me.aurora.client.utils.ClientMessages;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Timer;
import java.util.TimerTask;

public class AutoGabagoolTEST {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.toString().contains("gabagool"))
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ClientMessages.sendClientMessage("T");
                }
            }, 50);
    }

}
