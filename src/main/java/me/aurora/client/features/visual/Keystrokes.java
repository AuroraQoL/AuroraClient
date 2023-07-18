package me.aurora.client.features.visual;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import me.aurora.client.config.Config;
import me.aurora.client.utils.DrawUtils;
import me.aurora.client.utils.ThemeUtils;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

import java.awt.*;

import static me.aurora.client.Aurora.mc;

public class Keystrokes extends Element {

    private final Color standby = new Color(21, 20, 20, 36);
    private final Color pressed = new Color(238, 231, 231, 37);

    private final KeystrokesElement[] keystrokesElements = {
            new KeystrokesElement(25, 0, mc.gameSettings.keyBindForward, "W"),
            new KeystrokesElement(0, 25, mc.gameSettings.keyBindLeft, "A"),
            new KeystrokesElement(25, 25, mc.gameSettings.keyBindBack, "S"),
            new KeystrokesElement(50, 25, mc.gameSettings.keyBindRight, "D"),

    };

    private final FontRender fontRenderer = FontDefiner.getFontRenderer(25f);

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
        renderKeystrokes();
    }

    @Override
    public void editorDraw() {
        renderKeystrokes();
    }

    private void renderKeystrokes() {
        GlStateManager.pushMatrix();
        for (KeystrokesElement key : keystrokesElements) {
            int x = key.getX() + getX();
            int y = key.getY() + getY();
            DrawUtils.drawOutlinedRect(x, y, 23, 23, key.isActive() ? pressed : standby, key.getPosColor());
            fontRenderer.drawStringWithShadow(key.getRenderName(), x + 6, y + 4, key.getPosColor().getRGB());
        }
        GlStateManager.resetColor();
        GlStateManager.popMatrix();
    }

    @AllArgsConstructor
    @Data
    private static class KeystrokesElement {
        private int x;
        private int y;
        private KeyBinding boundKey;
        private String renderName;

        public boolean isActive() {
            return boundKey.isKeyDown();
        }

        private Color getPosColor() {
            return new Color(ThemeUtils.currentColorGet(((x + y) / 25f) * 0.1f));
        }
    }
}
