package me.aurora.client.utils;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * @brief Precise FPS Utilities
 */
public class FPSUtils {

    private static final Set<Long> lastFps = ConcurrentHashMap.newKeySet();
    private static boolean half = true;

    public static Integer getFPS() {
        lastFps.removeIf(x -> System.currentTimeMillis() - x > 1000);
        return lastFps.size();
    }

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event) {
        if (half = !half) lastFps.add(System.currentTimeMillis());
    }
}
