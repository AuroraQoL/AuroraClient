package me.aurora.client.features.misc;

import gg.essential.api.EssentialAPI;
import me.aurora.client.features.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.io.IOException;

import static me.aurora.client.Aurora.CURRENT_VERSION_BUILD;

public class UpdateReminder  implements Module {
    public String name() {
        return "UpdateReminder";
    }

    public boolean toggled() {
        return false;
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) throws IOException {
        if ((me.aurora.client.utils.VersionUtil.isOutdated(CURRENT_VERSION_BUILD)))
            EssentialAPI.getNotifications().push("This Version of Aurora is Outdated", "%s %s %s\n%s");
    }
}
