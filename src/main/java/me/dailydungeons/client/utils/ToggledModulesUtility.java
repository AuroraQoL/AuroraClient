package me.dailydungeons.client.utils;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.features.HUD;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToggledModulesUtility {

    List<String> test = new ArrayList<>();

    static ResourceLocation c = new ResourceLocation("dailydungeons:font/kanit.ttf");
    static MinecraftFontRenderer neufr;

    static {
        try {
            neufr = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(c).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ToggledModulesUtility() throws IOException, FontFormatException {
    }

    public static List<String> toggled() {
        List<String> test = new ArrayList<>();

        if(Config.Healthbar){
            test.add("Health Display");
        }
        if(Config.watermark){
            test.add("Watermark");
        }
        if(Config.keystrokes){
            test.add("Keystrokes");
        }
        if(Config.ghostblock){
            test.add("Ghostblocks");
        }
//        if(Config.ESP){
//            test.add("ESP");
//        }

        if(Config.AutoHarp){
            test.add("AutoHarp");
        }
        if(Config.AutoJump){
            test.add("Auto Jump");
        }
        if(Config.TpAnywhere){
            test.add("AOTV Aura");
        }
        if(Config.AutoJoinSkyblock){
            test.add("Auto Join SkyBlock");
        }
        if(Config.AutoTerminals){
            test.add("Auto Terminals");
        }
        if(Config.AutoRogue){
            test.add("Rogue Sword Aura");
        }
        if(Config.WitherCloakAura){
            test.add("Wither Cloak Aura");
        }
        if(Config.witherDoorSmasher){
            test.add("Wither Door Smasher");
        }
        if(Config.DontSlowMyGuy){
            test.add("No Slow");
        }
        if(Config.Velocity){
            test.add("Velocity");
        }
        if(Config.AutoSell){
            test.add("Auto Sell");
        }
        if(Config.TerminalAura){
            test.add("Terminal Aura");
        }
        if(Config.HarpStealer){
            test.add("Harp Stealer");
        }
        if(Config.scanner2){
            test.add("CH Scanner");
        }
        if(Config.AutoSneak){
            test.add("Auto Sneak");
        }
        if(Config.AutoSecrets){
            test.add("Auto Secrets");
        }
        if(Config.AutoSprint){
            test.add("Auto Sprint");
        }
        if(Config.LeapingFly){
            test.add("Leaping Flight");
        }
        if(Config.InventoryWalk){
            test.add("Inventory Walk");
        }
        if(Config.scanner){
            test.add("Gemstone ESP");
        }
        if(Config.NoDowntime){
            test.add("No Downtime");
        }
        if(Config.MelodyThrottle){
            test.add("Melody Throttle");
        }
        if(Config.AutoCrystals){
            test.add("Auto Crystals");
        }
        if(Config.AutoVoodoo){
            test.add("Auto Voodoo");
        }
        if(Config.DungeonMap){
            test.add("Dungeon Map");
        }
        if(Config.vclip){
            test.add("Vertical Clip");
        }
        if(Config.AutoTank){
            test.add("Auto Tank");
        }


        test.sort((String str1, String str2) -> (int) (neufr.getStringWidth(str2) - neufr.getStringWidth((String) str1)));

        return test;
    }



}
