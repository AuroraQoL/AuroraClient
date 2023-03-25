package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.InventoryUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.Timer;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

import static me.aurora.client.Aurora.mc;

public class TerminalAnnouncer implements Module {

    public String name() {
        return "TerminalAnnouncer";
    }

    public boolean toggled() {
        return Config.terminalAnnouncer;
    }

    private String currentTerminal;
    private final Timer timer = new Timer();
    private final HashMap<String, String> terminalDefinition = new HashMap<String, String>() {{
        put("Change all to same color!", "Color Terminal");
        put("Select all the", "Select Color Terminal");
        put("What starts with:", "Letter Terminal");
        put("Click in order!", "Numbers Terminal");
        put("Click the button on time!", "Melody Termianl");
        put("Correct all the panes!", "Panes Terminal");
    }};

    @SubscribeEvent
    public void onBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        currentTerminal = terminalDefinition.entrySet().stream().filter(val -> InventoryUtils.getGuiName(event.gui).contains(val.getKey())).findFirst().map(Map.Entry::getValue).orElse(null);
    }

    @SubscribeEvent
    public void onGuiDraw(GuiScreenEvent.BackgroundDrawnEvent event) {
        if (toggled() && mc.currentScreen instanceof GuiChest && currentTerminal != null && timer.timeBetween(5000, true))
            MessageUtils.sendClientMessage("/pc I'm currently in: " + currentTerminal);
    }
}
