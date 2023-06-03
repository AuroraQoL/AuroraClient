package me.aurora.client.features.visual;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ThemeUtils;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;

import java.awt.*;

import static me.aurora.client.Aurora.mc;

public class Keystrokes extends Element {

    private static final FontRender fontRenderer = FontDefiner.getFontRenderer();
    final int standby = new Color(0, 0, 0, 50).getRGB();
    final int pressed = new Color(255, 255, 255, 200).getRGB();

    public Keystrokes() {
        width = 200;
        height = 200;
    }

    @Override
    public int getX() {
        return Config.KEYSTROKES_X;
    }

    @Override
    public void setX(int val) {
        Config.KEYSTROKES_X = val;
    }

    @Override
    public int getY() {
        return Config.KEYSTROKES_Y;
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
        renderKeystrokes();
    }

    @Override
    public void editorDraw() {
        renderKeystrokes();
    }

    private void renderKeystrokes() {
        int rbw = ThemeUtils.currentColorGet(0);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        Gui.drawRect(100 + getX(), 100 + getY(), 125 + getX(), 125 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindForward) ? pressed : standby)));
        fontRenderer.drawStringWithShadow("W", 114 + getX(), 106 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindForward) ? rbw : pressed)));
        Gui.drawRect(73 + getX(), 127 + getY(), 98 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) ? pressed : standby)));
        fontRenderer.drawStringWithShadow("A", 87 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) ? rbw : pressed)));
        Gui.drawRect(100 + getX(), 127 + getY(), 125 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindBack) ? pressed : standby)));
        fontRenderer.drawStringWithShadow("S", 114 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindBack) ? rbw : pressed)));
        Gui.drawRect(127 + getX(), 127 + getY(), 152 + getX(), 152 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindRight) ? pressed : standby)));
        fontRenderer.drawStringWithShadow("D", 141 + getX(), 133 + getY(), ((GameSettings.isKeyDown(mc.gameSettings.keyBindRight) ? rbw : pressed)));
        GlStateManager.popMatrix();
    }
}
