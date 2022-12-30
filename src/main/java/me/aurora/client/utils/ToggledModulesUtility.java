package me.aurora.client.utils;

import me.aurora.client.config.Config;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * Very basic utility to get currently toggled modules and add them to the arraylist.
 */

public class ToggledModulesUtility {
    static ResourceLocation kanitLocation = new ResourceLocation("dailydungeons:font/kanit.ttf");
    static MinecraftFontRenderer kanitFontRenderer;

    static {
        try {
            kanitFontRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(kanitLocation).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
        } catch (FontFormatException | IOException ignored) {
        }
    }

    public static List<String> toggled() {
        List<String> test = new ArrayList<>();

        if (Config.Healthbar) {
            test.add("Health Display");
        }
        if (Config.watermark) {
            test.add("Watermark");
        }
        if (Config.keystrokes) {
            test.add("Keystrokes");
        }
        if (Config.ghostblock) {
            test.add("Ghostblocks");
        }
        if (Config.TpAnywhere) {
            test.add("AOTV Aura");
        }
        if (Config.AutoJoinSkyblock) {
            test.add("Auto Join SkyBlock");
        }
        if (Config.AutoRogue) {
            test.add("Rogue Sword Aura");
        }
        if (Config.WitherCloakAura) {
            test.add("Wither Cloak Aura");
        }
        if (Config.witherDoorSmasher) {
            test.add("Wither Door Smasher");
        }
        if (Config.DontSlowMyGuy) {
            test.add("No Slow");
        }
        if (Config.AutoSell) {
            test.add("Auto Sell");
        }
        if (Config.HarpStealer) {
            test.add("Harp Stealer");
        }
        if (Config.scanner2) {
            test.add("CH Scanner");
        }
        if (Config.AutoSecrets) {
            test.add("Auto Secrets");
        }
        if (Config.AutoSprint) {
            test.add("Auto Sprint");
        }
        if (Config.scanner) {
            test.add("Gemstone ESP");
        }
        if (Config.NoDowntime) {
            test.add("No Downtime");
        }
        if (Config.MelodyThrottle) {
            test.add("Melody Throttle");
        }
        if (Config.AutoCrystals) {
            test.add("Auto Crystals");
        }
        if (Config.vclip) {
            test.add("Vertical Clip");
        }
        if (Config.AutoTank) {
            test.add("Auto Tank");
        }
        test.sort((String str1, String str2) -> (int) (kanitFontRenderer.getStringWidth(str2) - kanitFontRenderer.getStringWidth((String) str1)));
        return test;
    }

}
