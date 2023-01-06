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

        if (Config.hudTargetDisplay) {
            test.add("targetDisplay");
        }
        if (Config.hudWatermark) {
            test.add("watermark");
        }
        if (Config.hudKeystrokes) {
            test.add("keystrokes");
        }
        if (Config.ghostblocks) {
            test.add("ghostblocks");
        }
        if (Config.aotvAura) {
            test.add("aotvAura");
        }
        if (Config.fastJoin) {
            test.add("fastJoin");
        }
        if (Config.ghost_secretsUnblock){
            test.add("secretsUnblock");
        }
        if (Config.rogueSwordAura) {
            test.add("rogueSwordAura");
        }
        if (Config.witherCloakAura) {
            test.add("witherCloakAura");
        }
        if (Config.witherDoorRemover) {
            test.add("witherDoorRemover");
        }
        if (Config.noSlowdown) {
            test.add("noSlowdown");
        }
        if (Config.autoSell) {
            test.add("autoSell");
        }
        if (Config.harpStealer) {
            test.add("harpStealer");
        }
        if (Config.structureScanner) {
            test.add("structureScanning");
        }
        if (Config.autoSecrets) {
            test.add("autoSecrets");
        }
        if (Config.autoSprint) {
            test.add("autoSprint");
        }
        if (Config.gemstoneEsp) {
            test.add("gemstoneEsp");
        }
        if (Config.noDowntime) {
            test.add("noDowntime");
        }
        if (Config.melodyThrottle) {
            test.add("melodyThrottle");
        }
        if (Config.autoCrystals) {
            test.add("autoCrystals");
        }
        if (Config.verticalClip) {
            test.add("verticalClip");
        }
        if (Config.autoTank) {
            test.add("autoTank");
        }
        test.sort((String str1, String str2) -> (int) (kanitFontRenderer.getStringWidth(str2) - kanitFontRenderer.getStringWidth((String) str1)));
        return test;
    }

}
