package me.aurora.client.features;

import me.aurora.client.config.Config;
import me.aurora.client.utils.CurrentColor;
import me.aurora.client.utils.ToggledModulesUtility;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.io.IOException;

import static me.aurora.client.Aurora.fontLocation;
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

    MinecraftFontRenderer kanitFontRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(fontLocation).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
    public ModuleList() throws IOException, FontFormatException {
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }
        int rbw = CurrentColor.currentColorGet(0);
        if (Config.hudArraylist) {
            int yPos = 0;
            float y3 = 0.0f;
            for (String module : ToggledModulesUtility.toggled()) {
                rbw = CurrentColor.currentColorGet(y3);
                double aa = kanitFontRenderer.getStringWidth(module) + 2;
                final float xPos = scaledResolution.getScaledWidth() - ((float) aa);
                Gui.drawRect((int) (xPos - 3f), yPos, scaledResolution.getScaledWidth(), yPos + 11,
                        new Color(0, 0, 0, 100).getRGB());
                Gui.drawRect((int) (scaledResolution.getScaledWidth() - 1f), yPos, (int) (scaledResolution.getScaledWidth() + 0.5f),
                        yPos + 11, rbw);
                kanitFontRenderer.drawStringWithShadow(module, xPos - 1f, yPos + 1f, rbw);
                yPos += 11;
                y3 += 0.07f;
            }
        }
    }

}

