package me.dailydungeons.client;

import club.sk1er.vigilance.Vigilance;
import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.command.PaletteCommand;
import club.sk1er.vigilance.gui.SettingsGui;
import club.sk1er.vigilance.gui.VigilancePalette;
import com.jagrosh.discordipc.entities.DiscordBuild;
import me.dailydungeons.client.cerberus.Cerberus;
import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.config.ConfigCommand;
import me.dailydungeons.client.events.TickEndEvent;
import me.dailydungeons.client.features.*;
import me.dailydungeons.client.utils.FontUtil;

import me.dailydungeons.client.utils.Utils;
import me.dailydungeons.client.utils.VersionUtil;
import me.dailydungeons.client.utils.inDungeon;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
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
import java.util.Scanner;

@Mod(modid = DailyDungeons.MODID, name = DailyDungeons.MODNAME, version = DailyDungeons.VERSION, clientSideOnly = true)
public class DailyDungeons {
    public static final String MODID = "bossbar_customizer";
    public static final String MODNAME = "bossbar_customizer";
    private static DailyDungeons INSTANCE = null;
    public static FontUtil jelloarray2;

    public static final String VERSION = "1.2.1";
    public static final int CURRENTVERSIONBUILD = 153;

    public static Config config;

    public static GuiScreen guiToOpen = null;
    private static final Minecraft mc = Minecraft.getMinecraft();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ConfigCommand());

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException, FontFormatException, NoSuchAlgorithmException {
        Vigilance.initialize();
        INSTANCE = this;
        ///




        System.out.println("Welcome ");

/*
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            System.out.println("user " + user.username + "#" + user.discriminator + "");
            DiscordRichPresence.Builder presence = new DiscordRichPresence.Builder("ni ");
            presence.setDetails("chuj123");
            DiscordRPC.discordUpdatePresence(presence.build());
        }).build();
        DiscordRPC.discordInitialize("1045815224256712725", handlers, true);
        DiscordRPC.discordRegister("1045815224256712725", "");

        DiscordRichPresence rich = new DiscordRichPresence.Builder("2.0 - Supporter").setDetails("In Menus" */
/*+ mc.thePlayer.getDisplayName()*//*
).setBigImage("wither", "Client logo").setSmallImage("supporter", "Supporter Version").build();
        DiscordRPC.discordUpdatePresence(rich);
*/



        ///
        System.out.println("Initializing Cerberus Protection System...");
        if (Cerberus.isAllowedToRun()) {
            System.out.println("Verified ownership for device with ID : " + Cerberus.getHWID());
        } else {
            System.out.println("Cerberus couldn't verify ownership of the mod. Running diagnosis...");
            if (Cerberus.networkConnection()){
                System.out.println("Cannot connect to the server.");
                return;

            } else {
                System.out.println("It seems like your device is not verified. Device ID : " + Cerberus.getHWID());

                return;

            }
        }
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Utils());

        if (!(me.dailydungeons.client.utils.VersionUtil.isForced(CURRENTVERSIONBUILD))) {
            MinecraftForge.EVENT_BUS.register(new HUD());
            MinecraftForge.EVENT_BUS.register(new AutoSell());
            MinecraftForge.EVENT_BUS.register(new Ghostblock());
            MinecraftForge.EVENT_BUS.register(new AutoGhostBlock());
            MinecraftForge.EVENT_BUS.register(new Velocity());
            MinecraftForge.EVENT_BUS.register(new TpAnywhere());
            MinecraftForge.EVENT_BUS.register(new AutoHarp());
            MinecraftForge.EVENT_BUS.register(new HarpStealer());
            MinecraftForge.EVENT_BUS.register(new NoSlowButWorse());
            MinecraftForge.EVENT_BUS.register(new AutoJump());
            MinecraftForge.EVENT_BUS.register(new NahuiBlyat());
            MinecraftForge.EVENT_BUS.register(new TickEndEvent());
            MinecraftForge.EVENT_BUS.register(new AutoJoinSkyblock());
            MinecraftForge.EVENT_BUS.register(new AutoRogue());
            MinecraftForge.EVENT_BUS.register(new AutoSecrets());
            MinecraftForge.EVENT_BUS.register(new AutoSneak());
            MinecraftForge.EVENT_BUS.register(new TerminalAura());
            MinecraftForge.EVENT_BUS.register(new inDungeon());
            //MinecraftForge.EVENT_BUS.register(new module());
            MinecraftForge.EVENT_BUS.register(new ESP());
            MinecraftForge.EVENT_BUS.register(new InventoryWalk());
            MinecraftForge.EVENT_BUS.register(new MelodyThrottle());
            MinecraftForge.EVENT_BUS.register(new StructureScanner());

            //  MinecraftForge.EVENT_BUS.register(new LeapingFly());
            MinecraftForge.EVENT_BUS.register(new LeapingFlyVelocity());
            MinecraftForge.EVENT_BUS.register(new NoDowntime());
            MinecraftForge.EVENT_BUS.register(new AutoSprint());
            MinecraftForge.EVENT_BUS.register(new AutoCrystals());
            MinecraftForge.EVENT_BUS.register(new AutoVoodoo());
            MinecraftForge.EVENT_BUS.register(new WitherCloakAura());
            //MinecraftForge.EVENT_BUS.register(new MiningMacro());
            //MinecraftForge.EVENT_BUS.register(new KillAura());
            MinecraftForge.EVENT_BUS.register(new DungeonMap());

/*        if (Cerberus.isAllowedToRun()) {
            MinecraftForge.EVENT_BUS.register(new StructureScanner());
        }*/
            if ((me.dailydungeons.client.utils.VersionUtil.isOutdated(CURRENTVERSIONBUILD))) {

                JOptionPane.showMessageDialog(null, "Current Aurora version is out of support. Usage of this version may result in a ban or bugs. Update client to continue using it in the future.");


            }

            //    MinecraftForge.EVENT_BUS.register(new ESP2());
        }

        if(VersionUtil.isForced(CURRENTVERSIONBUILD)){
            JOptionPane.showMessageDialog(null, "Current Aurora version has been discontinued. As usage of this version may result in a ban or several bugs, all features has been disabled. Update client to continue using it.");

        }


        config = new Config();
        config.preload();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if(event.phase != TickEvent.Phase.START) {
            if(guiToOpen != null) {
                SettingsGui.displayScreen(guiToOpen);
                guiToOpen = null;
            }
        }
    }

    // Font render

    }
