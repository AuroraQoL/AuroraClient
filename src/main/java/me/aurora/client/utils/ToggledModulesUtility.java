package me.aurora.client.utils;

import me.aurora.client.Aurora;
import me.aurora.client.features.Module;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static me.aurora.client.Aurora.fontLocation;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * Very basic utility to get currently toggled modules and add them to the arraylist.
 */

public class ToggledModulesUtility {

    static MinecraftFontRenderer kanitFontRenderer;

    static {
        try {
            kanitFontRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(fontLocation).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
        } catch (FontFormatException | IOException ignored) {
        }
    }

    public static List<String> toggled() {
        List<String> test = new ArrayList<>();
        for (Module module : Aurora.getModules()) {
            if (module.toggled()) test.add(module.name());
        }
        test.sort((String str1, String str2) -> (int) (kanitFontRenderer.getStringWidth(str2) - kanitFontRenderer.getStringWidth((String) str1)));
        return test;
    }

}
