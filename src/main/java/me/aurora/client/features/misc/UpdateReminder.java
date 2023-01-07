package me.aurora.client.features.misc;

import gg.essential.api.EssentialAPI;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.io.IOException;

import static me.aurora.client.Aurora.CURRENTVERSIONBUILD;

public class UpdateReminder {

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) throws IOException {
        if ((me.aurora.client.utils.VersionUtil.isOutdated(Integer.parseInt(CURRENTVERSIONBUILD)))) {
            EssentialAPI.getNotifications().push("This Version of Aurora is Outdated", "%s %s %s\n%s");
        }
    }
}
