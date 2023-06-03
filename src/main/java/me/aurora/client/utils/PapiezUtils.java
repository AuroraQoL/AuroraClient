package me.aurora.client.utils;

import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.time.LocalTime;

public class PapiezUtils {
    private boolean sent = false;

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (!ConditionUtils.inGame()) return;
        LocalTime localTime = LocalTime.now();
        if (localTime.getHour() == 21 && localTime.getMinute() == 37) {
            if (!sent) {
                MessageUtils.sendClientMessage("PAPIEŻOWA");
                sent = true;
            }
        } else {
            sent = false;
        }
    }
}
