package me.aurora.client;

import club.sk1er.vigilance.Vigilance;
import club.sk1er.vigilance.gui.SettingsGui;
import me.aurora.client.config.Config;
import me.aurora.client.config.ConfigCommand;
import me.aurora.client.events.TickEndEvent;
import me.aurora.client.features.*;
import me.aurora.client.features.dungeons.*;
import me.aurora.client.features.incomplete.LeapingFlyVelocity;
import me.aurora.client.features.incomplete.TerminalAura;
import me.aurora.client.features.incomplete.Velocity;
import me.aurora.client.features.mining.GemstoneScanner;
import me.aurora.client.features.mining.StructureScanner;
import me.aurora.client.features.misc.*;
import me.aurora.client.features.movement.*;
import me.aurora.client.utils.VersionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Mod(modid = Aurora.MODID, name = Aurora.MODNAME, version = Aurora.VERSION, clientSideOnly = true)
public class Aurora {
    public static final String MODID = "bossbar_customizer";
    public static final String MODNAME = "bossbar_customizer";
    public static final String VERSION = "1.2.1";
    public static final int CURRENTVERSIONBUILD = 199;
    public static Config config;
    public static GuiScreen guiToOpen = null;
    public static Aurora INSTANCE = null;
    public static Minecraft mc = Minecraft.getMinecraft();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ConfigCommand());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException, FontFormatException, NoSuchAlgorithmException {
        Vigilance.initialize();
        INSTANCE = this;
        MinecraftForge.EVENT_BUS.register(INSTANCE);
        if (!(VersionUtil.isForced(CURRENTVERSIONBUILD))) {
            MinecraftForge.EVENT_BUS.register(new HUD());
            MinecraftForge.EVENT_BUS.register(new AutoSell());
            MinecraftForge.EVENT_BUS.register(new Ghostblock());
            MinecraftForge.EVENT_BUS.register(new WitherDoorRemover());
            MinecraftForge.EVENT_BUS.register(new Velocity());
            MinecraftForge.EVENT_BUS.register(new TpAnywhere());
            MinecraftForge.EVENT_BUS.register(new AutoHarp());
            MinecraftForge.EVENT_BUS.register(new HarpStealer());
            MinecraftForge.EVENT_BUS.register(new NoSlowButWorse());
            MinecraftForge.EVENT_BUS.register(new GemstoneScanner());
            MinecraftForge.EVENT_BUS.register(new TickEndEvent());
            MinecraftForge.EVENT_BUS.register(new AutoJoinSkyblock());
            MinecraftForge.EVENT_BUS.register(new AutoRogue());
            MinecraftForge.EVENT_BUS.register(new AutoSecrets());
            MinecraftForge.EVENT_BUS.register(new AutoSneak());
            MinecraftForge.EVENT_BUS.register(new TerminalAura());
            MinecraftForge.EVENT_BUS.register(new InventoryWalk());
            MinecraftForge.EVENT_BUS.register(new MelodyThrottle());
            MinecraftForge.EVENT_BUS.register(new StructureScanner());
            MinecraftForge.EVENT_BUS.register(new LeapingFlyVelocity());
            MinecraftForge.EVENT_BUS.register(new NoDowntime());
            MinecraftForge.EVENT_BUS.register(new AutoSprint());
            MinecraftForge.EVENT_BUS.register(new AutoCrystals());
            MinecraftForge.EVENT_BUS.register(new AutoVoodoo());
            MinecraftForge.EVENT_BUS.register(new WitherCloakAura());
            MinecraftForge.EVENT_BUS.register(new DungeonMap());
            MinecraftForge.EVENT_BUS.register(new AutoTank());
            MinecraftForge.EVENT_BUS.register(new VClip());
            if ((VersionUtil.isOutdated(CURRENTVERSIONBUILD))) {
                JOptionPane.showMessageDialog(null, "Current Aurora version is out of support. Usage of this version may result in a ban or bugs. Update client to continue using it in the future.");
            }
        }
        if (VersionUtil.isForced(CURRENTVERSIONBUILD)) {
            JOptionPane.showMessageDialog(null, "Current Aurora version has been discontinued. As usage of this version may result in a ban or several bugs, all features has been disabled. Update client to continue using it.");
        }
        config = new Config();
        config.preload();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            if (guiToOpen != null) {
                SettingsGui.displayScreen(guiToOpen);
                guiToOpen = null;
            }
        }
    }
}
