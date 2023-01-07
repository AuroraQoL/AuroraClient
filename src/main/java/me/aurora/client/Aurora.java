package me.aurora.client;

import me.aurora.client.config.Config;
import me.aurora.client.config.ConfigCommand;
import me.aurora.client.events.TickEndEvent;
import me.aurora.client.features.HUD;
import gg.essential.api.EssentialAPI;
import me.aurora.client.features.dungeons.*;
import me.aurora.client.features.mining.GemstoneScanner;
import me.aurora.client.features.mining.StructureScanner;
import me.aurora.client.features.misc.AutoJoinSkyblock;
import me.aurora.client.features.misc.HarpStealer;
import me.aurora.client.features.misc.MelodyThrottle;
import me.aurora.client.features.movement.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.io.IOException;

@Mod(modid = Aurora.MODID, name = Aurora.MODNAME, version = Aurora.VERSION, clientSideOnly = true)
public class Aurora
{
    public static final String MODID = "bossbar_customizer";
    public static final String MODNAME = "BossbarCustomizer";
    public static final String VERSION = "1.2.1";
    public static final String CURRENTVERSIONBUILD = "253";
    public static GuiScreen guiToOpen = null;
    public static Config config;
    public static Minecraft mc = Minecraft.getMinecraft();

    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException, FontFormatException {
        EssentialAPI.getCommandRegistry().registerCommand(new ConfigCommand());
        MinecraftForge.EVENT_BUS.register(this);
        config = new Config();
        config.preload();
        MinecraftForge.EVENT_BUS.register(new HUD());
        MinecraftForge.EVENT_BUS.register(new AutoSell());
        MinecraftForge.EVENT_BUS.register(new Ghostblock());
        MinecraftForge.EVENT_BUS.register(new WitherDoorRemover());
        MinecraftForge.EVENT_BUS.register(new TpAnywhere());
        MinecraftForge.EVENT_BUS.register(new HarpStealer());
        MinecraftForge.EVENT_BUS.register(new NoSlowButWorse());
        MinecraftForge.EVENT_BUS.register(new GemstoneScanner());
        MinecraftForge.EVENT_BUS.register(new TickEndEvent());
        MinecraftForge.EVENT_BUS.register(new AutoJoinSkyblock());
        MinecraftForge.EVENT_BUS.register(new AutoRogue());
        MinecraftForge.EVENT_BUS.register(new AutoSecrets());
        MinecraftForge.EVENT_BUS.register(new MelodyThrottle());
        MinecraftForge.EVENT_BUS.register(new StructureScanner());
        MinecraftForge.EVENT_BUS.register(new NoDowntime());
        MinecraftForge.EVENT_BUS.register(new AutoSprint());
        MinecraftForge.EVENT_BUS.register(new AutoCrystals());
        MinecraftForge.EVENT_BUS.register(new WitherCloakAura());
        MinecraftForge.EVENT_BUS.register(new DungeonMap());
        MinecraftForge.EVENT_BUS.register(new AutoTank());
        MinecraftForge.EVENT_BUS.register(new NoBedrock());
        MinecraftForge.EVENT_BUS.register(new VClip());
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (guiToOpen!=null){
            mc.displayGuiScreen(guiToOpen);
            guiToOpen=null;
        }
    }
}
