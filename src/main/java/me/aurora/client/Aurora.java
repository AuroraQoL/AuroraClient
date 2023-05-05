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
import me.aurora.client.features.Module;
import me.aurora.client.features.dungeons.*;
import me.aurora.client.features.garden.AutoComposter;
import me.aurora.client.features.garden.GrassESP;
import me.aurora.client.features.mining.GemstoneScanner;
import me.aurora.client.features.mining.StructureScanner;
import me.aurora.client.features.misc.*;
import me.aurora.client.features.movement.*;
import me.aurora.client.features.test.AutoCrystals;
import me.aurora.client.features.test.AutoSecrets;
import me.aurora.client.features.test.CrystalPlacer;
import me.aurora.client.features.visual.*;
import me.aurora.client.krypton.Main;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.FPSUtils;
import me.aurora.client.utils.PacketHandler;
import me.aurora.client.utils.RemoteUtils;
import me.cephetir.communistscanner.CommunistScanners;
import me.cephetir.communistscanner.StructureCallBack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Mod(modid = "bossbar_customizer", name = "BossbarCustomizer", version = "1.2.1", clientSideOnly = true)
public class Aurora {
    public static final int CURRENT_VERSION_BUILD = 3499;
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
    @Getter
    private static final boolean aprilFools = java.time.LocalDate.now().getDayOfMonth() == 1 && java.time.LocalDate.now().getMonth() == java.time.Month.APRIL;
    @Getter
    private static boolean isSupporter = false;
    @Getter
    private static String guiCmd = "aurorahud";

    @EventHandler
    @SneakyThrows
    public void init(FMLInitializationEvent event) {
        Display.setTitle("Aurora 3.4.1");
        MinecraftForge.EVENT_BUS.register(this);
        new Config().preload();
        isSupporter = supporterClassExist();
        CommunistScanners.INSTANCE.init(new StructureCallBack() {
            @Override
            public void newStructure(@NotNull String server, @NotNull String name, @NotNull BlockPos blockPos) {
                StructureScanner.addStructure(server, blockPos, name, true);
            }
        });
        BindUtils.registerBinds(
                new BindUtils.Bind(Keyboard.KEY_NONE, "AutoSellBazaar"),
                new BindUtils.Bind(Keyboard.KEY_G, "GhostBlocks"),
                new BindUtils.Bind(Keyboard.KEY_NONE, "VClip"),
                new BindUtils.Bind(Keyboard.KEY_K, "FastJoin")
        );
        registerModules(new LegacyModuleList(), new AutoSell(), new Ghostblock(), new WitherDoorRemover(),
                new AotvAura(), new HarpStealer(), new NoSlow(), new GemstoneScanner(),
                new AutoJoinSkyblock(), new AutoRogue(), new AutoHarp(), new AutoSecrets(), new MelodyThrottle(),
                new StructureScanner(), new NoDowntime(), new AutoSprint(), new AutoCrystals(),
                new WitherCloakAura(), new AutoTank(), new NoBedrock(), new VClip(),
                new CrystalPlacer(), new AntiLimbo(), new AutoSellBz(), new GrassESP(),
                new AutoComposter(), new RatEsp(), new TerminalAnnouncer());
        if (!isSupporter) {
            registerHud(new Watermark(), new Keystrokes(), new PacketDebug(), new FPS());
        }
        registerEvents(new TickEndEvent(), new Main(), new PacketHandler(), new FPSUtils());
        registerCommand(new CrabbyCommand(), new HUDCommand(), new ConfigCommand());
        if (RemoteUtils.isOutdated(CURRENT_VERSION_BUILD))
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
            File updater = new File(System.getProperty("java.io.tmpdir") + "aurora_updater_" + new Random().nextInt() + ".jar");
            Files.copy(new URL("https://github.com/Gabagooooooooooool/AuroraUpdater/releases/download/1.0/updater.jar").openStream(), updater.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Updating...");
            Process p = new ProcessBuilder("java", "-jar", "\"" + updater.getAbsolutePath() + "\"", "1000", "\"" + modFile.getAbsolutePath() + "\"", "mainrepo").start();
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
        Set<String> blacklisted = RemoteUtils.getBlacklistedModules();
        System.out.println("Blacklisted Aurora modules:");
        blacklisted.forEach(System.out::println);
        for (Module module : modules_t) {
            if (blacklisted.contains(module.name())) continue;
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

    private boolean supporterClassExist() {
        try {
            Class.forName("re.aurora.Sep");
        }
        catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
