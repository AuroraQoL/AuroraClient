package me.aurora.client;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import me.aurora.client.commands.AuroraSpammerCommand;
import me.aurora.client.commands.ConfigCommand;
import me.aurora.client.commands.HUDCommand;
import me.aurora.client.config.Config;
import me.aurora.client.config.HUDEdit;
import me.aurora.client.events.TickEndEvent;
import me.aurora.client.events.packets.PacketHandler;
import me.aurora.client.features.ModuleList;
import me.aurora.client.features.Module;
import me.aurora.client.features.dungeons.*;
import me.aurora.client.features.garden.AutoComposter;
import me.aurora.client.features.garden.GrassESP;
import me.aurora.client.features.mining.GemstoneScanner;
import me.aurora.client.features.mining.StructureScanner;
import me.aurora.client.features.misc.*;
import me.aurora.client.features.movement.*;
import me.aurora.client.features.visual.*;
import me.aurora.client.krypton.Main;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.FPSUtils;
import me.aurora.client.utils.VersionUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Mod(modid = "bossbar_customizer", name = "BossbarCustomizer", version = "1.2.1", clientSideOnly = true)
public class Aurora {
    public static final int CURRENT_VERSION_BUILD = 999;
    private static final Set<Element> hudModules = new HashSet<>();
    public static Minecraft mc = Minecraft.getMinecraft();
    public static ResourceLocation fontLocation;
    private static final HUDEdit hudEdit = new HUDEdit();
    private static GuiScreen guiToOpen = null;
    private static File modFile = null;
    private static final ArrayList<Module> modules = new ArrayList<>();
    public static Set<Element> getHudModules() {
        return hudModules;
    }
    public static ResourceLocation getFontLocation() {
        return fontLocation;
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public static void setGuiToOpen(GuiScreen guiToOpen) {
        Aurora.guiToOpen = guiToOpen;
    }

    public static HUDEdit getHudEdit() {
        return hudEdit;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException, FontFormatException {
        Display.setTitle("Minecraft 1.8.9 - Aurora 3.4 pre-release 2");
        MinecraftForge.EVENT_BUS.register(this);
        new Config().preload();
        selectFont();
        BindUtils.registerBinds(
                new BindUtils.Bind(Keyboard.KEY_NONE, "AutoSellBazaar"),
                new BindUtils.Bind(Keyboard.KEY_G, "GhostBlocks")
        );
        registerModules(new ModuleList(), new AutoSell(), new Ghostblock(), new WitherDoorRemover(),
                new TpAnywhere(), new HarpStealer(), new NoSlowButWorse(), new GemstoneScanner(),
                new AutoJoinSkyblock(), new AutoRogue(), new AutoSecrets(), new MelodyThrottle(),
                new StructureScanner(), new NoDowntime(), new AutoSprint(), new AutoCrystals(),
                new WitherCloakAura(), new AutoTank(), new NoBedrock(), new VClip(),
                new CrystalPlacer(), new AntiLimbo(), new AutoSellBz(), new GrassESP(),
                new AutoComposter(), new RatEsp());
        registerHud(new Watermark(), new Keystrokes(), new PacketDebug(), new FPS());
        registerEvents(new TickEndEvent(), new Main(), new PacketHandler(), new FPSUtils());
        registerCommand(new AuroraSpammerCommand(), new HUDCommand(), new ConfigCommand());
        if (VersionUtil.isOutdated(CURRENT_VERSION_BUILD))
            Runtime.getRuntime().addShutdownHook(new Thread(this::update));
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (guiToOpen != null) {
            mc.displayGuiScreen(guiToOpen);
            setGuiToOpen(null);
        }
    }

    private void update() {
        if (!Config.autoUpdate) return;
        try {
            InputStream in = new URL("https://github.com/Gabagooooooooooool/AuroraUpdater/releases/download/1.0/updater.jar").openStream();
            File updater = new File(System.getProperty("java.io.tmpdir") + "aurora_updater_" + new Random().nextInt() + ".jar");
            Files.copy(in, updater.toPath(), StandardCopyOption.REPLACE_EXISTING);
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "\"" + updater.getAbsolutePath() + "\"", "1000", "\"" + modFile.getAbsolutePath() + "\"", "mainrepo");
            Process p = pb.start();
            System.out.println("Updating...");
        } catch (IOException ignored) {}
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        modFile = event.getSourceFile();
    }

    @SubscribeEvent
    public void renderGameOverlayEvent(RenderGameOverlayEvent.Post event) {
        for (Element hudModule : hudModules) {
            getHudEdit().RenderGUI(hudModule);
        }
    }

    private void registerModules(Module... modules_t) {
        for (Module module : modules_t) {
            getModules().add(module);
            MinecraftForge.EVENT_BUS.register(module);
        }
    }

    private void registerEvents(Object... events) {
        for (Object event : events) {
            MinecraftForge.EVENT_BUS.register(event);
        }
    }

    private void registerHud(Element... elements) {
        hudModules.addAll(Arrays.asList(elements));
    }

    private void registerCommand(Command... commands) {
        for (Command command : commands) {
            EssentialAPI.getCommandRegistry().registerCommand(command);
        }
    }

    private void selectFont() {
        String location = "dailydungeons:res/";
        switch (Config.hudFont) {
            case 0:
                location += "kanit.ttf";
                break;
            case 1:
                location += "oldkanit.ttf";
                break;
            case 2:
                location += "dys.ttf";
                break;
        }
        fontLocation = new ResourceLocation(location);
    }
}
