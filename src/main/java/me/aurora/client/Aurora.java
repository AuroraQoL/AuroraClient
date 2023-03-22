package me.aurora.client;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.aurora.client.commands.ConfigCommand;
import me.aurora.client.commands.CrabbyCommand;
import me.aurora.client.commands.HUDCommand;
import me.aurora.client.config.Config;
import me.aurora.client.config.HUDEdit;
import me.aurora.client.events.TickEndEvent;
import me.aurora.client.features.test.CrystalPlacer;
import me.aurora.client.utils.FPSUtils;
import me.aurora.client.utils.PacketHandler;
import me.aurora.client.features.Module;
import me.aurora.client.features.visual.LegacyModuleList;
import me.aurora.client.features.test.AutoCrystals;
import me.aurora.client.features.dungeons.*;
import me.aurora.client.features.garden.AutoComposter;
import me.aurora.client.features.garden.GrassESP;
import me.aurora.client.features.mining.GemstoneScanner;
import me.aurora.client.features.mining.StructureScanner;
import me.aurora.client.features.misc.*;
import me.aurora.client.features.movement.*;
import me.aurora.client.features.test.AutoSecrets;
import me.aurora.client.features.dungeons.AutoTank;
import me.aurora.client.features.visual.*;
import me.aurora.client.krypton.Main;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.VersionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
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

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Mod(modid = "bossbar_customizer", name = "BossbarCustomizer", version = "1.2.1", clientSideOnly = true)
public class Aurora {
    public static final int CURRENT_VERSION_BUILD = 999;
    @Getter
    private static final Set<Element> hudModules = new HashSet<>();
    public static Minecraft mc = Minecraft.getMinecraft();
    @Getter
    private static final HUDEdit hudEdit = new HUDEdit();
    @Setter
    private static GuiScreen guiToOpen = null;
    private static File modFile = null;
    @Getter
    private static final ArrayList<Module> modules = new ArrayList<>();

    @EventHandler
    @SneakyThrows
    public void init(FMLInitializationEvent event) {
        Display.setTitle("Minecraft 1.8.9 - Aurora 3.4 preview");
        MinecraftForge.EVENT_BUS.register(this);
        new Config().preload();
        BindUtils.registerBinds(
                new BindUtils.Bind(Keyboard.KEY_NONE, "AutoSellBazaar"),
                new BindUtils.Bind(Keyboard.KEY_G, "GhostBlocks"),
                new BindUtils.Bind(Keyboard.KEY_NONE, "VClip"),
                new BindUtils.Bind(Keyboard.KEY_K, "FastJoin")
        );
        registerModules(new LegacyModuleList(), new AutoSell(), new Ghostblock(), new WitherDoorRemover(),
                new AotvAura(), new HarpStealer(), new NoSlow(), new GemstoneScanner(),
                new AutoJoinSkyblock(), new AutoRogue(), new AutoSecrets(), new MelodyThrottle(),
                new StructureScanner(), new NoDowntime(), new AutoSprint(), new AutoCrystals(),
                new WitherCloakAura(), new AutoTank(), new NoBedrock(), new VClip(),
                new CrystalPlacer(), new AntiLimbo(), new AutoSellBz(), new GrassESP(),
                new AutoComposter(), new RatEsp());
        registerHud(new Watermark(), new Keystrokes(), new PacketDebug(), new FPS());
        registerEvents(new TickEndEvent(), new Main(), new PacketHandler(), new FPSUtils());
        registerCommand(new CrabbyCommand(), new HUDCommand(), new ConfigCommand());
        if (VersionUtils.isOutdated(CURRENT_VERSION_BUILD))
            Runtime.getRuntime().addShutdownHook(new Thread(this::update));
    }

    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (guiToOpen != null) {
            mc.displayGuiScreen(guiToOpen);
            setGuiToOpen(null);
        }
    }

    @SneakyThrows
    private void update() {
        if (Config.autoUpdate) {
            InputStream in = new URL("https://github.com/Gabagooooooooooool/AuroraUpdater/releases/download/1.0/updater.jar").openStream();
            File updater = new File(System.getProperty("java.io.tmpdir") + "aurora_updater_" + new Random().nextInt() + ".jar");
            Files.copy(in, updater.toPath(), StandardCopyOption.REPLACE_EXISTING);
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "\"" + updater.getAbsolutePath() + "\"", "1000", "\"" + modFile.getAbsolutePath() + "\"", "mainrepo");
            Process p = pb.start();
            System.out.println("Updating...");
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        modFile = event.getSourceFile();
    }

    @SubscribeEvent
    public void renderGameOverlayEvent(RenderGameOverlayEvent.Post event) {
        hudModules.forEach(getHudEdit()::RenderGUI);
    }

    private void registerModules(Module... modules_t) {
        for (Module module : modules_t) {
            getModules().add(module);
            MinecraftForge.EVENT_BUS.register(module);
        }
    }

    private void registerEvents(Object... events) {
        for (Object event : events)
            MinecraftForge.EVENT_BUS.register(event);
    }

    private void registerHud(Element... elements) {
        hudModules.addAll(Arrays.asList(elements));
    }

    private void registerCommand(Command... commands) {
        for (Command command : commands)
            EssentialAPI.getCommandRegistry().registerCommand(command);
    }
}
