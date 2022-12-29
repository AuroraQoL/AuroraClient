package me.aurora.client.features;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.ColorUtils;
import me.aurora.client.utils.CurrentColor;
import me.aurora.client.utils.ToggledModulesUtility;
import me.aurora.client.utils.font.FontUtil;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

import static me.aurora.client.Aurora.mc;
import static me.aurora.client.config.Config.keystrokesX;
import static me.aurora.client.config.Config.keystrokesY;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 * Head-Up Display.
 */

public class HUD {
    public static double healthBarTarget = 0, healthBar = 0;
    ResourceLocation kanitFontResourceLoc = new ResourceLocation("dailydungeons:font/kanit.ttf");
    MinecraftFontRenderer kanitFontRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(kanitFontResourceLoc).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
    final int standby = new Color(0, 0, 0, 100).getRGB();
    final int pressed = new Color(255, 255, 255, 205).getRGB();

    public HUD() throws IOException, FontFormatException {
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }
        int rbw = CurrentColor.currentColorGet(0);
        if ((Config.watermark)) {
            kanitFontRenderer.drawStringWithShadow("\247lAurora QoL \247r[" + "public 1.999" + "]", 5, 5, 0xA9A9A9);
            kanitFontRenderer.drawStringWithShadow("\247lAurora QoL \247r", 5, 5, 0xFFFFFF);
            kanitFontRenderer.drawStringWithShadow("\247lAurora", 5, 5, rbw);
            kanitFontRenderer.drawStringWithShadow("Build " + Aurora.CURRENTVERSIONBUILD, 5, 15, 0x444444);
        }
        if (Config.keystrokes) {
            Gui.drawRect(100 + keystrokesX, 100 + keystrokesY, 125 + keystrokesX, 125 + keystrokesY, ((GameSettings.isKeyDown(mc.gameSettings.keyBindForward) ? pressed : standby)));
            kanitFontRenderer.drawStringWithShadow("W", 114 + keystrokesX, 106 + keystrokesY, ((GameSettings.isKeyDown(mc.gameSettings.keyBindForward) ? rbw : pressed)));
            Gui.drawRect(73 + keystrokesX, 127 + keystrokesY, 98 + keystrokesX, 152 + keystrokesY, ((GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) ? pressed : standby)));
            kanitFontRenderer.drawStringWithShadow("A", 87 + keystrokesX, 133 + keystrokesY, ((GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) ? rbw : pressed)));
            Gui.drawRect(100 + keystrokesX, 127 + keystrokesY, 125 + keystrokesX, 152 + keystrokesY, ((GameSettings.isKeyDown(mc.gameSettings.keyBindBack) ? pressed : standby)));
            kanitFontRenderer.drawStringWithShadow("S", 114 + keystrokesX, 133 + keystrokesY, ((GameSettings.isKeyDown(mc.gameSettings.keyBindBack) ? rbw : pressed)));
            Gui.drawRect(127 + keystrokesX, 127 + keystrokesY, 152 + keystrokesX, 152 + keystrokesY, ((GameSettings.isKeyDown(mc.gameSettings.keyBindRight) ? pressed : standby)));
            kanitFontRenderer.drawStringWithShadow("D", 141 + keystrokesX, 133 + keystrokesY, ((GameSettings.isKeyDown(mc.gameSettings.keyBindRight) ? rbw : pressed)));
        }
        /** MODIFIED FROM PULSE */
        if (Config.Healthbar && (mc.thePlayer.getLastAttacker() != null)) {
            healthBarTarget = scaledResolution.getScaledWidth() / 2 + 15 + (((125) / (mc.thePlayer.getLastAttacker().getMaxHealth())) * (mc.thePlayer.getLastAttacker().getHealth()));
            double HealthBarSpeed = 2;
            if (healthBar > healthBarTarget) {
                healthBar = ((healthBar) - ((healthBar - healthBarTarget) / HealthBarSpeed));
            } else if (healthBar < healthBarTarget) {
                healthBar = ((healthBar) + ((healthBarTarget - healthBar) / HealthBarSpeed));
            }
            Gui.drawRect(scaledResolution.getScaledWidth() / 2 + 15, scaledResolution.getScaledHeight() / 2 + 15, scaledResolution.getScaledWidth() / 2 + 140, scaledResolution.getScaledHeight() / 2 + 60, new Color(1, 1, 1, 100).getRGB());
            Gui.drawRect(scaledResolution.getScaledWidth() / 2 + 15, scaledResolution.getScaledHeight() / 2 + 55, scaledResolution.getScaledWidth() / 2 + 140, scaledResolution.getScaledHeight() / 2 + 60, new Color(1, 1, 1, 100).getRGB());
            Gui.drawRect(scaledResolution.getScaledWidth() / 2 + 15, scaledResolution.getScaledHeight() / 2 + 55, (int) healthBar, scaledResolution.getScaledHeight() / 2 + 60, rbw);
            GlStateManager.color(1, 1, 1);
            kanitFontRenderer.drawStringWithShadow(mc.thePlayer.getLastAttacker().getName(), scaledResolution.getScaledWidth() / 2 + 22, scaledResolution.getScaledHeight() / 2 + 22, rbw);
            GL11.glPushMatrix();
            GL11.glScalef(2, 2, 1);
            kanitFontRenderer.drawStringWithShadow("\u2764" + mc.thePlayer.getLastAttacker().getHealth(), scaledResolution.getScaledWidth() / 4 + 11, scaledResolution.getScaledHeight() / 4 + 17, rbw);
            GL11.glScalef(1, 1, 1);
            GL11.glPopMatrix();
        }
        if (Config.arrayList) {
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

