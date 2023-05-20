package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.Timer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Locale;

import static me.aurora.client.Aurora.mc;

public class AutoSex implements Module {
    public String name() {
        return "SexAura";
    }

    public boolean toggled() {
        return Config.sexAura;
    }

    String ign = Config.sexAuraKurwa;

    Timer timer = new Timer();

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent e) {
        if (toggled() && timer.timeBetween(5000, true)) {
            switch (Config.sexAuraMode) {
                case 0:
                    mc.thePlayer.sendChatMessage("/msg Aleksiiiio oj oj oj aleksio raaaaaawr kurrrrrrrrrcze pieczone");
                    mc.thePlayer.sendChatMessage("/f Aleksiiiio");
                    mc.thePlayer.sendChatMessage("/p Aleksiiiio");
                case 1:
                    mc.thePlayer.sendChatMessage("/msg " + ign + " oj oj oj " + ign + " raaaaaawr kurrrrrrrrrcze pieczone");
                    mc.thePlayer.sendChatMessage("/f " + ign);
                    mc.thePlayer.sendChatMessage("/p " + ign);
            }
        }
    }
}
