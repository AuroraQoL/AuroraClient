package me.aurora.client.features.visual;

import lombok.SneakyThrows;
import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.ThemeUtils;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 3.0
 * @brief Simple Module List
 */

public class LegacyModuleList implements Module {

    public String name() {
        return "ModuleList";
    }

    public boolean toggled() {
        return Config.hudArraylist;
    }

    private static final FontRender fontRenderer = FontDefiner.getFontRenderer();

    @SubscribeEvent
    @SneakyThrows
    public void onRender(RenderGameOverlayEvent event) {
        if (!(event.type == RenderGameOverlayEvent.ElementType.TEXT && toggled())) return;
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        AtomicInteger counter = new AtomicInteger(0);
        Aurora.getModules().stream().filter(Module::toggled).map(Module::name).sorted(fontRenderer).forEachOrdered(module -> {
            int currentPhase = counter.getAndIncrement();
            int color = ThemeUtils.currentColorGet(currentPhase * 0.07f);
            int xPos = (int) (scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(module) - 2);
            int yPos = currentPhase * 11;
            Gui.drawRect(xPos - 3, yPos, scaledResolution.getScaledWidth(), yPos + 11, new Color(0, 0, 0, 100).getRGB());
            Gui.drawRect(scaledResolution.getScaledWidth() - 1, yPos, scaledResolution.getScaledWidth(), yPos + 11, color);
            fontRenderer.drawStringWithShadow(module, xPos - 1f, yPos + 1f, color);
        });
    }
}

