package me.dailydungeons.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("NonAtomicOperationOnVolatileField")
public class KFontUtil {
    public static volatile int completed;
    public static MinecraftFontRenderer normal;


    private static Font normal_;


    public static MinecraftFontRenderer huy;

    public static MinecraftFontRenderer tfr;

    ResourceLocation c = new ResourceLocation("dailydungeons:font/sfui.ttf");

    private static Font huy_;



    private static Font getFontFMR( int size) {
        Font font = null;

        try {

                InputStream is = Minecraft.getMinecraft().getResourceManager()
                        .getResource(new ResourceLocation("dailydungeons:font/sfui.ttf")
).getInputStream();
                font = Font.createFont(0, is);
                font = font.deriveFont(Font.PLAIN, size);

        } catch (Exception e) {
            e.printStackTrace();
            font = new Font("default", Font.PLAIN, +16);

        }

        return font;
    }

    private static Font getFont(Map<String, Font> locationMap, String location, int size) {
        Font font = null;

        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(Font.PLAIN, size);
            } else {
                InputStream is = Minecraft.getMinecraft().getResourceManager()
                        .getResource(new ResourceLocation("font/fonts" + location)).getInputStream();
                font = Font.createFont(0, is);
                locationMap.put(location, font);
                font = font.deriveFont(Font.PLAIN, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            font = new Font("default", Font.PLAIN, +16);

        }

        return font;
    }

    public static boolean hasLoaded() {
        return completed >= 3;
    }

    public static void bootstrap() {
        new Thread(() ->
        {
            Map<String, Font> locationMap = new HashMap<>();
            normal_ = getFont(locationMap, "med.ttf", 19); //19
            completed++;
        }).start();
        new Thread(() ->
        {

            Map<String, Font> locationMap = new HashMap<>();
            huy_ = getFont(locationMap, "skeet.ttf", 19); //19

            completed++;
        }).start();
        new Thread(() ->
        {
            Map<String, Font> locationMap = new HashMap<>();
            completed++;
        }).start();

        while (!hasLoaded()) {
            try {
                //noinspection BusyWait
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        normal = new MinecraftFontRenderer(normal_, true, true);
        huy = new MinecraftFontRenderer(normal_, true, true);
        tfr = new MinecraftFontRenderer(getFontFMR( 25), true, false);

    }
}
