package me.aurora.client.utils;

import me.aurora.client.Aurora;
import me.aurora.client.features.Module;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * Very basic utility to get currently toggled modules and add them to the arraylist.
 */

public class FeaturesUtils {

    private static final FontRender fontRenderer = FontDefiner.getFontRenderer();

    public static List<String> toggled() {
        List<String> test = new ArrayList<>();
        for (Module module : Aurora.getModules()) {
            if (module.toggled()) test.add(module.name());
        }
        test.sort((String str1, String str2) -> (int) (fontRenderer.getStringWidth(str2) - fontRenderer.getStringWidth((String) str1)));
        return test;
    }

}
