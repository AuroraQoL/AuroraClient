package me.dailydungeons.client.config;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.*;
import me.dailydungeons.client.features.AutoSecrets;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;

public class Config extends Vigilant {

//

    @Property(
            type = PropertyType.SWITCH,
            name = "Watermark Visibility",
            description = "",
            category = "HUD",
            subcategory = "Watermark"
    )
    public static boolean watermark = true;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Watermark Style",
            description = "",
            options = {"Vanilla"},


            category = "HUD",
            subcategory = "Watermark"
    )
    public static int watermarkstyle = 0;
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
            subcategory = "Colors"
    )
    public static int colors = 1;
    @Property(
            type = PropertyType.SWITCH,
            name = "Keystrokes",
            description = "",
            category = "HUD",
            subcategory = "Keystrokes"
    )
    public static boolean keystrokes = true;

    @Property(
            type = PropertyType.SLIDER,
            name = "Keystrokes Position X",
            description = "",
            category = "HUD",
            subcategory = "Keystrokes",
            min = 0,
            max = 1080
    )
    public static int keystrokesX = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Keystrokes Position Y",
            description = "",
            category = "HUD",
            subcategory = "Keystrokes",
            min = 0,
            max = 1920
    )
    public static int keystrokesY = 0;

    @Property(
            type = PropertyType.SWITCH,
            name = "Target Display",
            description = "Displays health and other info of current target.",
            category = "HUD",
            subcategory = "Target Display"
    )
    public static boolean Healthbar = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "Ghostblocks",
            description = "Make ghostblocks by pressing G",
            category = "Dungeons",
            subcategory = "Ghostblocks"
    )
    public static boolean ghostblock = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Doors",
            description = "Destroy Wither Doors by pressing H",
            category = "Dungeons",
            subcategory = "Ghostblocks"
    )
    public static boolean witherDoorSmasher = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "No Slowdown",
            description = "Disables slowdown",
            category = "Movement",
            subcategory = "No Slowdown"
    )
    public static boolean DontSlowMyGuy = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[NOT WORKING] \247rAutoTerminal",
            description = "Auto Completes Terminals",
            category = "Dungeons",
            subcategory = "Terminals"
    )


  //  public static boolean terminalHalfTrip = false;
    public static boolean AutoTerminals = false;
  //  public static long terminalClickDelay = 130;
  @Property(
          type = PropertyType.SWITCH,
          name = "Harp Stealer",
          description = "Allows you to take harp, preventing other people from using it",
          category = "QOL",
          subcategory = "Harp"
  )
  public static boolean HarpStealer = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[PATCHED] \247rVelocity",
            description = "Allows you to not take any knockback",
            category = "Movement",
            subcategory = "Velocity"
    )
    public static boolean Velocity = false;


    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[PATCHED] \247rAuto Jump",
            description = "Automatically jumps for higher speed (still bans on hypixel)",
            category = "Movement",
            subcategory = "Auto Jump"
    )
    public static boolean AutoJump = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[NOT TESTED] \247rAutoHarp",
            description = "Automatically does harp",
            category = "QOL",
            subcategory = "Harp"
    )
    public static boolean AutoHarp = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "AOTV Aura",
            description = "Aloows you to teleport no matter what you're holding",
            category = "QOL"
    )
    public static boolean TpAnywhere = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[NOT TESTED] \247rAuto-Sell Dungeon Junk",
            description = "Automatically sells useless dungeon items",
            category = "Dungeons",
            subcategory = "QOL"
    )
    public static boolean AutoSell = false;

/*    @Property(
            type = PropertyType.SWITCH,
            name = "\2474[BROKEN] \247rRat ESP",
            description = "ESP but rat",
            category = "QOL"
    )
    public static boolean ESP = false;*/
    @Property(
            type = PropertyType.SWITCH,
            name = "Rogue Sword Aura",
            description = "Allows you to use rogue sword no matter what you're currently holding",
            category = "Movement",
            subcategory = "Speed"
    )
    public static boolean AutoRogue = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Arraylist",
            description = "Allows you to see what modules you're currently using",
            category = "HUD",
            subcategory = "Arraylist"
    )
    public static boolean arrayList = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Fast Join",
            description = "Press L to join SkyBlock",
            category = "QOL",
            subcategory = "Fast Join"
    )
    public static boolean AutoJoinSkyblock = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[NOT DONE] \247rTerminal Aura",
            description = "Teleports between Terminals \2478(credits to LittenIsBad for the idea)",
            category = "Dungeons",
            subcategory = "Terminals"
    )
    public static boolean TerminalAura = true;
    @Property(
            type = PropertyType.SWITCH,
            name = "Inventory Walk",
            description = "Allows you to walk in GUIs",
            category = "Movement",
            subcategory = "GUI"
    )
    public static boolean InventoryWalk = false;

    @Property(
            type = PropertyType.SWITCH,
            name = "\2476[\u2726 SUPPORTER] \247rAuto Secrets",
            description = "Auto Etherwarps in order to get Secrets (needs secret aura)",
            category = "Dungeons",
            subcategory = "Secrets"
    )
    public static boolean AutoSecrets = false;


    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Sneak",
            description = "Auto Sneaks (can fuck up the packets)",
            category = "Movement",
            subcategory = "Auto Sneak"
    )
    public static boolean AutoSneak = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Auto Sprint",
            description = "Auto Sprints for You",
            category = "Movement",
            subcategory = "Sprint"
    )
    public static boolean AutoSprint = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Melody Throttle Spammer",
            description = "Spams party chat when you are being throttled",
            category = "QOL",
            subcategory = "Melody"
    )
    public static boolean MelodyThrottle = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[PATCHED] \247rAuto Voodoo Doll",
            description = "Uses Voodoo Doll on Kuudra when you use Valkyrie",
            category = "Kuudra",
            subcategory = "Voodoo"
    )
    public static boolean AutoVoodoo = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\2476[\u2726 SUPPORTER] \247rLeaping Flight",
            description = "Allows you to literally fucking fly lmfao",
            category = "Movement",
            subcategory = "Leaping Fly"
    )
    public static boolean LeapingFly = false;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Leaping Flight Velocity",
            description = "Change The Velocity Of Leaping Flight",
            options = {"Fast", "Slow", "High", "Low"},


            category = "Movement",
            subcategory = "Leaping Fly"
    )
    public static int LeapingFlyVelocity = 1;
    @Property(
            type = PropertyType.SWITCH,
            name = "No Downtime",
            description = "Instantly Starts The Dungeon On End. Does not require to set the floor, it automaticly detects.",
            category = "Dungeons",
            subcategory = "No Downtime"
    )
    public static boolean NoDowntime = false;

    @Property(
            type = PropertyType.SLIDER,
            name = "No Downtime Delay",
            description = "No Downtime delay",
            category = "Dungeons",
            subcategory = "No Downtime",
            min = 1,
            max = 128
    )
    public static int downtime = 128;
    
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP]\247r Auto Crystals",
            description = "Automatically does Floor 7 Crystals",
            category = "Dungeons",
            subcategory = "Crystals"
    )
    public static boolean AutoCrystals = false;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Auto Crystals Sides",
            description = "Change What Crystal You Want To Do",
            options = {"Right", "Left"},


            category = "Dungeons",
            subcategory = "Crystals"
    )
    public static int crystalSide = 1;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247c\247l[\u03B2] \247r\247a[WORKING]\247r Gemstone ESP",
            description = "Scans Crystall Hollows to reveal gemstone veins and overlays them with colored box.",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean scanner = true;
    @Property(
            type = PropertyType.SELECTOR,
            name = "Block ESP Type",
            description = "Change color of ESP",
            options = {"Outlined", "Filled", "Optimized"},
            category = "Mining",
            subcategory = "Scanner"
    )
    public static int colorsTwo = 0;
    @Property(
            type = PropertyType.SLIDER,
            name = "Outlined ESP thickness",
            description = "How thick lines are",
            category = "Mining",
            subcategory = "Scanner",
            min = 1,
            max = 8
    )
    public static int thicc = 2;
    @Property(
            type = PropertyType.SLIDER,
            name = "Scan Range",
            description = "Range for scanning (blocks)",
            category = "Mining",
            subcategory = "Scanner",
            min = 16,
            max = 128
    )
    public static int scanrange = 32;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247c\247l[\u03B2] \247r\247e[EXCLUSIVE]\247r Extended Scanning (Standalone)",
            description = "Reveals Structures (Spiral, throne, etc.) and best locations for mining. ",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean scanner2 = true;

    @Property(
            type = PropertyType.SLIDER,
            name = "Extended Scan Range",
            description = "Range for extended scanning (blocks)",
            category = "Mining",
            subcategory = "Scanner",
            min = 16,
            max = 128
    )
    public static int scanrange2 = 32;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247c\247l[\u03B2] \247rExperimental Extended Scanning",
            description = "Faster & More efficent scanning, can cause crash.",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean experimentalExtendedScanning = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[NOT WORKING] \2476[\u2726 SUPPORTER] \247rFast Scan",
            description = "Uses optimized scanning algorithm for faster speeds. Can cause visual issues.",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean scanner3 = true;

    @Property(
            type = PropertyType.SWITCH,
            name = "\247c[NOT WORKING] \247b[CLOUD] \247rAI Optimization",
            description = "Optimizes mining by making paths using Artificial Inteligence. Speed depends on our server.",
            category = "Mining",
            subcategory = "Scanner"
    )
    public static boolean bayes = true;
    //@Property(
    //        type = PropertyType.SWITCH,
   //         name = "\247c[REMOVED] \247rMining Macro",
   //         description = "placeholder",
    //        category = "Mining",
   //         subcategory = "Macro"
 //   )
  //  public static boolean MiningMacro = false;
  //  @Property(
     //       type = PropertyType.SWITCH,
   //         name = "\247c[REMOVED] \247rKill Aura",
    //        description = "placeholder",
     //       category = "Combat",
    //        subcategory = "Kill Aura"
 //   )
   // public static boolean KillAura = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "\247b[WIP] \247rWither Cloak Aura",
            description = "Automatically uses Wither Cloak Sword if you're in lava",
            category = "Dungeons",
            subcategory = "Wither Cloak"
    )
    public static boolean WitherCloakAura = false;
    @Property(
            type = PropertyType.SLIDER,
            name = "Dungeon Map Range",
            description = "Range for scanning the map",
            category = "Map",
            subcategory = "Scanner",
            min = 16,
            max = 256
    )
    public static int dungeonmapscan = 32;
    @Property(
            type = PropertyType.SLIDER,
            name = "Dungeon Map Range Boost",
            description = "Scan more map (might not work hehe)",
            category = "Map",
            subcategory = "Scanner",
            min = 16,
            max = 256
    )
    public static int extrascanner = 32;
    @Property(
            type = PropertyType.SWITCH,
            name = "Dungeon Map",
            description = "Scans the Dungeon Map",
            category = "Map",
            subcategory = "Scanner"
    )
    public static boolean DungeonMap = true;
    public static boolean AutoLimbo = false;
    public static boolean SelfBan = false;

    public static class ConfigSorting extends SortingBehavior {
        @NotNull @Override
        public Comparator<Category> getCategoryComparator() {
            return new Comparator<Category>() {
                @Override
                public int compare(Category o1, Category o2) {
                    if(o1.getName().equals("General")) {
                        return -1;
                    } else if(o2.getName().equals("General")) {
                        return 1;
                    } else {
                        return o1.getName().compareTo(o2.getName());
                    }
                }
            };
        }
    }

    public Config() {
        super(new File("./config/ddconfig.toml"), "AURORA", new JVMAnnotationPropertyCollector(), new ConfigSorting());
        initialize();
    }

}
