package me.aurora.client.features.misc;

import gg.essential.api.EssentialAPI;
import me.aurora.client.config.Config;
import me.aurora.client.utils.ClientMessages;
import me.aurora.client.utils.Timer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UpdateReminder {
    private boolean sendMessage = true;

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
        if (Config.termNotification) {
            if (sendMessage) {
                ClientMessages.sendClientMessage("WE GOT TERMINATED");
                ClientMessages.sendClientMessage("since we got terminated there is a new invite link");
                ClientMessages.sendClientMessage("https://discord.gg/9GJXNKfHkC");
                ClientMessages.sendClientMessage("to turn this message off go to /aurora, Discord and turn off Term Notification");
                EssentialAPI.getNotifications().push("Aurora Client got Terminated", "Please Join the new Server: https://discord.gg/9GJXNKfHkC");
                sendMessage = false;
            }
        }
    }
}
