package me.aurora.client.features;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ThemeUtils;
import me.aurora.client.utils.FeaturesUtils;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.io.IOException;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * Head-Up Display.
 */

public class ModuleList implements Module {
    public String name() {
        return "ModuleList";
    }

    public boolean toggled() {
        return Config.hudArraylist;
    }
    private static final FontRender fontRenderer = FontDefiner.getFontRenderer();


    public ModuleList() throws IOException, FontFormatException {
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }
        int rbw = ThemeUtils.currentColorGet(0);
        if (Config.hudArraylist) {
            int yPos = 0;
            float y3 = 0.0f;
            for (String module : FeaturesUtils.toggled()) {
                rbw = ThemeUtils.currentColorGet(y3);
                double aa = fontRenderer.getStringWidth(module) + 2;
                final float xPos = scaledResolution.getScaledWidth() - ((float) aa);
                Gui.drawRect((int) (xPos - 3f), yPos, scaledResolution.getScaledWidth(), yPos + 11,
                        new Color(0, 0, 0, 100).getRGB());
                Gui.drawRect((int) (scaledResolution.getScaledWidth() - 1f), yPos, (int) (scaledResolution.getScaledWidth() + 0.5f),
                        yPos + 11, rbw);
                fontRenderer.drawStringWithShadow(module, xPos - 1f, yPos + 1f, rbw);
                yPos += 11;
                y3 += 0.07f;
            }
        }
    }

}

