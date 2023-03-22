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

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent event){
        if (half) lastFps.add(System.currentTimeMillis());
        half =! half;
    }

    public static Integer getFPS(){
        removeOutdated();
        return lastFps.size();
    }

    private static void removeOutdated(){
        lastFps.removeIf(x -> System.currentTimeMillis() - x > 1000);
    }
}
