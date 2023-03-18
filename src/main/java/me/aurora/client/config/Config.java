package me.aurora.client.config;

import gg.essential.universal.UMinecraft;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;
import me.aurora.client.Aurora;
import me.aurora.client.features.misc.RatEsp;

import java.io.File;

public class Config extends Vigilant {
    @Property(
            type = PropertyType.BUTTON,
            name = "Move Elements",
            category = "HUD",
            description = "Move elements",
            placeholder = "Edit HUD"
    )
    void button() {
        Aurora.getHudEdit().display();
    }

    ////////////
    ////////////
    ////////////


    @Property(
            type = PropertyType.NUMBER, name = "WATERMARK_X", category = "pos", hidden = true)
    public static int WATERMARK_X = 5;
    @Property(
            type = PropertyType.NUMBER, name = "WATERMARK_Y", category = "pos", hidden = true)
    public static int WATERMARK_Y = 5;
    @Property(
            type = PropertyType.NUMBER, name = "KEY_X", category = "pos", hidden = true)
    public static int KEYSTROKES_X = 150;
    @Property(
            type = PropertyType.NUMBER, name = "KEY_Y", category = "pos", hidden = true)
    public static int KEYSTROKES_Y = 150;
    @Property(
            type = PropertyType.NUMBER, name = "FPS_X", category = "pos", hidden = true)
    public static int FPS_X = 300;
    @Property(
            type = PropertyType.NUMBER, name = "FPS_Y", category = "pos", hidden = true)
    public static int FPS_Y = 300;
    @Property(
            type = PropertyType.NUMBER, name = "P_X", category = "pos", hidden = true)
    public static int PACKET_X = 200;
    @Property(
            type = PropertyType.NUMBER, name = "P_Y", category = "pos", hidden = true)
    public static int PACKET_Y = 100;

    ////////////
    ////////////
    ////////////

    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Script (Demo)",
            description = "Enter your script here :)",
            category = "Krypton (Demo)",
            subcategory = "Script"
    )
    public static String script = "";
    @Property(
            type = PropertyType.SWITCH,
            name = "Automatic Updates",
            description = "Automatically updates client after you close the game.",
            category = "Other",
            subcategory = "Updates"
    )
    public static boolean autoUpdate = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Watermark Visibility",
            description = "",
            category = "HUD",
            subcategory = "Watermark"
    )
    public static boolean hudWatermark = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Sell Items on Bazaar",
            description = "Automatically sells your items on bazaar if your inventory is full",
            category = "QOL",
            subcategory = "Bazaar"
    )
    public static boolean autoSellBz = false;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Auto Sell Items on Bazaar type",
            description = "",
            options = {"Keybind", "Full Inventory"},
            category = "QOL",
            subcategory = "Bazaar"
    )
    public static int autoSellBzType = 0;
    @Property(
            type = PropertyType.SWITCH,
            name = "Rat ESP",
            description = "Turns other players into big rat monster",
            category = "Memes",
            subcategory = "Rat ESP"
    )
    public static boolean ratEsp = false;
    @Property(
            type = PropertyType.CHECKBOX,
            name = "Crabby Mode",
            description = "Replaces rat with Crabby",
            category = "Memes",
            subcategory = "Rat ESP"
    )
    public static boolean ratEsp_crabbyMode = false;
    @Property(
            type = PropertyType.SELECTOR,
            name = "HUD Wave Theme",
            description = "Change color of RGB wave",
            options = {"\247cR\2476a\247ei\247an\2479b\247bo\2475w", "\247bB\247du\247bb\247db\247bl\247de\247bg\247du\247bm",
                    "\247dS\2476u\247en\2475r\247di\2476s\247de", "\2472S\247ap\247er\2476i\247en\247ag",
                    "\2474A\2474u\247ct\247cu\2476m\2476n", "\2479A\2473q\247bu\2473a",
                    "\247fM\2477o\2478n\2477o \2477F\2478a\2477d\247fe",
                    "\2470B\2471o\2479n\2477e", "\2478C\2477o\2476p\247ep\2476e\2477r",
                    "No Wave"},
            category = "HUD",
            subcategory = "Looks"
    )
    public static int hudThemeColor = 1;
    @Property(
            type = PropertyType.SELECTOR,
            name = "HUD Font",
            description = "Change font of hud. \247c\247lApplies after game restart!",
            options = {"\247lPrompt (New Default)", "Kanit (Old Default)", "OpenDyslexic"},
            category = "HUD",
            subcategory = "Looks"
    )
    public static int hudFont = 0;
    @Property(
            type = PropertyType.SWITCH,
            name = "Keystrokes",
            description = "",
            category = "HUD",
            subcategory = "Modules"
    )
    public static boolean hudKeystrokes = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "FPS",
            description = "Displays current FPS",
            category = "HUD",
            subcategory = "Modules"
    )
    public static boolean hudFPS = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Packet Debug",
            description = "Displays time since last recieved packet (in seconds)",
            category = "HUD",
            subcategory = "Modules"
    )
    public static boolean hudPacket = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Ghostblocks",
            description = "Make ghostblocks",
            category = "Dungeons",
            subcategory = "Ghostblocks"
    )
    public static boolean ghostblocks = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Ghostblocks Range",
            description = "",
            category = "Dungeons",
            subcategory = "Ghostblocks",
            min = 1,
            max = 32
    )
    public static int ghostblocks_range = 6;
    @Property(
            type = PropertyType.DECIMAL_SLIDER,
            name = "Ghostblocks Delay (Seconds)",
            description = "",
            category = "Dungeons",
            subcategory = "Ghostblocks",
            min = 0,
            max = 1
    )
    public static float ghostblocks_delay = 0.1f;
    @Property(
            type = PropertyType.SWITCH,
            name = "\2478[GHOST] \247rSecrets Unblock",
            description = "Automatically removes bedrock to let you use more efficient secret paths. This module is designed for usage in ghost-type scenarios, for standard use please check Ghostblocks.",
            category = "Dungeons",
            subcategory = "Ghost"
    )
    public static boolean ghost_secretsUnblock = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Wither Door Remover",
            description = "Destroy Wither Doors by pressing H",
            category = "Dungeons",
            subcategory = "Ghostblocks"
    )
    public static boolean witherDoorRemover = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "No Slowdown",
            description = "Disables slowdown",
            category = "Movement",
            subcategory = "No Slowdown"
    )
    public static boolean noSlowdown = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Harp Stealer",
            description = "Allows you to take harp, preventing other people from using it",
            category = "QOL",
            subcategory = "Harp"
    )
    public static boolean harpStealer = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP]\247r AOTV Aura",
            description = "Allows you to teleport no matter what you're holding",
            category = "QOL"
    )
    public static boolean aotvAura = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[NOT WORKING / UNDER REWRITE] \247rAuto-Sell Dungeon Junk",
            description = "Automatically sells useless dungeon items",
            category = "Dungeons",
            subcategory = "QOL",
            hidden = true
    )
    public static boolean autoSell = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP]\247r Rogue Sword Aura",
            description = "Allows you to use rogue sword no matter what you're currently holding",
            category = "Movement",
            subcategory = "Speed"
    )
    public static boolean rogueSwordAura = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Arraylist",
            description = "Allows you to see what modules you're currently using",
            category = "HUD",
            subcategory = "Arraylist"
    )
    public static boolean hudArraylist = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Fast Join",
            description = "Press L to join SkyBlock",
            category = "QOL",
            subcategory = "Fast Join"
    )
    public static boolean fastJoin = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Multi-Threading",
            description = "\247cREQUIRES DECENT OCTA-CORE CPU FOR SMOOTH USAGE",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean structureScanner_ParameterThread = false;
    /**
     * HIDDEN. IN DEVELOPMENT
     */
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP]\247r Auto Secrets",
            description = "Auto Etherwarps in order to get Secrets (Currenty for dev purposes)",
            category = "Dungeons",
            subcategory = "Secrets",
            hidden = true
    )
    public static boolean autoSecrets = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Sprint",
            description = "Sets your sprint to always be true",
            category = "Movement",
            subcategory = "Sprint"
    )
    public static boolean autoSprint = false;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Auto Sprint Mode",
            description = "",
            options = {"Legit", "Omni"},
            category = "Movement",
            subcategory = "Sprint"
    )
    public static int autoSprintSettings = 0;
    @Property(
            type = PropertyType.SWITCH,
            name = "Melody Throttle Spammer",
            description = "Spams party chat when you are being throttled",
            category = "QOL",
            subcategory = "Melody"
    )
    public static boolean melodyThrottle = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "No Downtime",
            description = "Instantly Starts The Dungeon On End. Does not require to set the floor, it automaticly detects.",
            category = "Dungeons",
            subcategory = "No Downtime"
    )
    public static boolean noDowntime = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "No Downtime Delay",
            description = "No Downtime delay",
            category = "Dungeons",
            subcategory = "No Downtime",
            min = 1,
            max = 128
    )
    public static int noDowntime_ParameterDelay = 128;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP]\247r Auto Crystals",
            description = "Automatically does Floor 7 Crystals (currently only works for right crystal)",
            category = "Dungeons",
            subcategory = "Crystals",
            hidden = true
    )
    public static boolean autoCrystals = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP]\247r Crystal Placer",
            description = "Jump into iron blocks while holding a crystal in order to place it",
            category = "Dungeons",
            subcategory = "Crystals",
            hidden = true
    )
    public static boolean crystalPlacer = false;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Auto Crystals Sides",
            description = "Change What Crystal You Want To Do",
            options = {"Right", "Left"},


            category = "Dungeons",
            subcategory = "Crystals",
            hidden = true
    )
    public static int crystalSide = 1;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247c\247l[\u03B2]\247r Gemstone ESP",
            description = "Scans Crystall Hollows to reveal gemstone veins and overlays them with colored box.",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean gemstoneEsp = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "kaotic is a furry",
            description = "furry",
            category = "kaotic",
            subcategory = "furry",
            hidden = true
    )
    public static boolean antiLimbo = false;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Block ESP Type",
            description = "Change color of ESP",
            options = {"Outlined", "Filled", "Optimized"},
            category = "Mining",
            subcategory = "Scanner"
    )
    public static int gemstoneEsp_ParameterVisualType = 0;
    @Property(
            type = PropertyType.SLIDER,
            name = "Outlined ESP thickness",
            description = "How thick lines are",
            category = "Mining",
            subcategory = "Scanner",
            min = 1,
            max = 8
    )
    public static int gemstoneEsp_thicc = 2;
    @Property(
            type = PropertyType.SLIDER,
            name = "Scan Range",
            description = "Range for scanning (blocks)",
            category = "Mining",
            subcategory = "Scanner",
            min = 16,
            max = 128
    )
    public static int gemstoneEsp_ParameterRange = 32;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247c\247l[\u03B2] \247r\247e[EXCLUSIVE]\247r Structure Scanning (Standalone)",
            description = "Reveals Structures (Spiral, throne, etc.) and best locations for mining. ",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean structureScanner = true;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Scan Display Mode",
            description = "",
            options = {"None", "Message", "Waypoint", "Message + Waypoint"},
            category = "Mining",
            subcategory = "Scanner"
    )
    public static int scanType = 3;
    @Property(
            type = PropertyType.CHECKBOX,
            name = "Freecam Structure Scanning",
            description = "Enables freecam module support",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean structureScanner_freecam = true;
    @Property(
            type = PropertyType.CHECKBOX,
            name = "Only scan for Dillo spots",
            description = "Only scans for Dillo spots, improving performance",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean structureScanner_dillo = true;


    @Property(
            type = PropertyType.SLIDER,
            name = "Extended Scan Range",
            description = "Range for structure scanning (blocks)",
            category = "Mining",
            subcategory = "Scanner",
            min = 16,
            max = 256
    )
    public static int structureScanner_ParameterRange = 32;
    @Property(
            type = PropertyType.SWITCH,
            name = "Aggressive Gemstone Scanner",
            description = "Does not wait between scans. Can cause performance issues, but allows 100% real time scanning and fix visual bugs when paired with \"Keep Blocks\"",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean gemstoneEsp_ParameterAggressiveScan = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Aggressive Structure Scanner",
            description = "Does not wait between scans. Can cause performance issues, but allows 100% real time scanning.",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean structureScanner_ParameterAggressiveScan = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Keep Blocks",
            description = "Does not remove blocks when moving. Can cause stability issues and stackoverflow.",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean gemstoneEsp_ParameterKeep = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP] \247rWither Cloak Aura",
            description = "Automatically uses Wither Cloak Sword if you're in lava",
            category = "Dungeons",
            subcategory = "Wither Cloak"
    )
    public static boolean witherCloakAura = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP]\247r Auto Tank",
            description = "Auto TP to maxor platform",
            category = "Dungeons",
            subcategory = "Tank"
    )
    public static boolean autoTank = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP]\247r Vertical Clip",
            description = "Press K to VClip",
            category = "Dungeons",
            subcategory = "Clip"
    )
    public static boolean verticalClip = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Vertical Clip Slider",
            description = "",
            category = "Dungeons",
            subcategory = "Clip",
            min = 1,
            max = 27
    )
    public static int verticalClip_ParameterDistance = 9;

    @Property(
            type = PropertyType.SWITCH,
            name = "Grass ESP",
            description = "Overlays grass in Garden for the cleaning-up task.",
            category = "Garden",
            subcategory = "ESP"
    )
    public static boolean grassEsp = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Auto-Composter",
            description = "Configurable Auto-composter.",
            category = "Garden",
            subcategory = "Auto-Composter"
    )
    public static boolean autoComposter = true;
    @Property(
            type = PropertyType.SLIDER,
            name = "Auto-Composter delay",
            description = "Delay between actions (in ticks).",
            category = "Garden",
            subcategory = "Auto-Composter",
            min=3,
            max=20
    )
    public static int composter_delay = 8;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Crops insertion type",
            description = "",
            options = {"From sacks", "From inventory", "Don't insert"},
            category = "Garden",
            subcategory = "Auto-Composter"
    )
    public static int composter_crop = 1;
    @Property(
            type = PropertyType.CHECKBOX,
            name = "Fuel insertion",
            description = "",
            category = "Garden",
            subcategory = "Auto-Composter"
    )
    public static boolean composter_fuel = true;
    @Property(
            type = PropertyType.CHECKBOX,
            name = "Collect compost",
            description = "",
            category = "Garden",
            subcategory = "Auto-Composter"
    )
    public static boolean composter_compost = true;

    public static Config INSTANCE = new Config();

    public Config() {
        super(
                new File(UMinecraft.getMinecraft().mcDataDir, "config/aurora.toml"),
                "Aurora"
        );
        initialize();
    }



}
