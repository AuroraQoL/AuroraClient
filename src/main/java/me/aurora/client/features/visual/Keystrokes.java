package me.aurora.client.features.visual;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.CurrentColor;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

import static me.aurora.client.Aurora.mc;

public class Keystrokes extends Element {

    final int standby = new Color(0, 0, 0, 99).getRGB();
    final int pressed = new Color(255, 255, 255, 200).getRGB();

    ResourceLocation kanitFontResourceLoc = new ResourceLocation("dailydungeons:font/kanit.ttf");
    MinecraftFontRenderer kanitFontRenderer;

    {
        try {
            kanitFontRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(kanitFontResourceLoc).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
        } catch (FontFormatException | IOException ignored) {
        }
    }

    public Keystrokes() {
        width = 200;
        height = 200;
    }

    @Override
    public int getX() {
        return Config.KEYSTROKES_X;
    }

    @Override
    public int getY() {
        return Config.KEYSTROKES_Y;
    }

    @Override
    public void setX(int val) {
        Config.KEYSTROKES_X = val;
    }

    @Override
    public void setY(int val) {
        Config.KEYSTROKES_Y = val;
    }

    @Override
    public boolean enabled() {
        return Config.hudKeystrokes;
    }

    @Override
    public void guiDraw() {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int rbw = CurrentColor.currentColorGet(0);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.pushMatrix();
        Gui.drawRect(100 + getX(), 100 + getY(), 125 + getX(), 125 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindForward) ? pressed : standby)));
        kanitFontRenderer.drawStringWithShadow("W", 114 + getX(), 106 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindForward) ? rbw : pressed)));
        Gui.drawRect(73 + getX(), 127 + getY(), 98 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) ? pressed : standby)));
        kanitFontRenderer.drawStringWithShadow("A", 87 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) ? rbw : pressed)));
        Gui.drawRect(100 + getX(), 127 + getY(), 125 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindBack) ? pressed : standby)));
        kanitFontRenderer.drawStringWithShadow("S", 114 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindBack) ? rbw : pressed)));
        Gui.drawRect(127 + getX(), 127 + getY(), 152 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindRight) ? pressed : standby)));
        kanitFontRenderer.drawStringWithShadow("D", 141 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindRight) ? rbw : pressed)));
        GlStateManager.popMatrix();

    }

    @Override
    public void editorDraw() {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int rbw = CurrentColor.currentColorGet(0);
        Gui.drawRect(100 + getX(), 100 + getY(), 125 + getX(), 125 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindForward) ? pressed : standby)));
        kanitFontRenderer.drawStringWithShadow("W", 114 + getX(), 106 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindForward) ? rbw : pressed)));
        Gui.drawRect(73 + getX(), 127 + getY(), 98 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) ? pressed : standby)));
        kanitFontRenderer.drawStringWithShadow("A", 87 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) ? rbw : pressed)));
        Gui.drawRect(100 + getX(), 127 + getY(), 125 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindBack) ? pressed : standby)));
        kanitFontRenderer.drawStringWithShadow("S", 114 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindBack) ? rbw : pressed)));
        Gui.drawRect(127 + getX(), 127 + getY(), 152 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindRight) ? pressed : standby)));
        kanitFontRenderer.drawStringWithShadow("D", 141 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindRight) ? rbw : pressed)));

    }
}
