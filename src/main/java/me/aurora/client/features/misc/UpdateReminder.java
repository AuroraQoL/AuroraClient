package me.aurora.client.features.misc;

import gg.essential.api.EssentialAPI;
import lombok.SneakyThrows;
import me.aurora.client.features.Module;
import me.aurora.client.utils.RemoteUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.io.IOException;

import static me.aurora.client.Aurora.CURRENT_VERSION_BUILD;

public class UpdateReminder implements Module {
    public String name() {
        return "UpdateReminder";
    }

    public boolean toggled() {
        return false;
    }

    @SubscribeEvent
    @SneakyThrows
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (RemoteUtils.isOutdated(CURRENT_VERSION_BUILD))
            EssentialAPI.getNotifications().push("This Version of Aurora is Outdated", "Please Update!");
    }
}
