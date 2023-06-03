package me.aurora.client.features.visual;

import me.aurora.client.config.Config;
import me.aurora.client.utils.FPSUtils;
import me.aurora.client.utils.ThemeUtils;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;

public class FPS extends Element {
    private static final FontRender fontRenderer = FontDefiner.getFontRenderer(30f);
    private static int fpsCache = 0;
    private static boolean fpsDoUpdate;

    public FPS() {
        width = 65;
        height = 15;
    }

    @Override
    public int getX() {
        return Config.FPS_X;
    }

    @Override
    public void setX(int val) {
        Config.FPS_X = val;
    }

    @Override
    public int getY() {
        return Config.FPS_Y;
    }

    @Override
    public void setY(int val) {
        Config.FPS_Y = val;
    }

    @Override
    public boolean enabled() {
        return Config.hudFPS;
    }

    @Override
    public void guiDraw() {
        if (fpsDoUpdate) fpsCache = FPSUtils.getFPS();
        fpsDoUpdate = !fpsDoUpdate;
        renderFps(fpsCache);
    }

    @Override
    public void editorDraw() {
        renderFps(1337);
    }

    private void renderFps(int fps) {
        int rbw = ThemeUtils.currentColorGet(0);
        fontRenderer.drawStringWithShadow("\247lFPS\247r:\2477 " + fps, getX(), getY(), 0xFFFFFF);
        for (int i = 0; i < 2; i++) {
            fontRenderer.drawStringWithShadow("\247lFPS", getX(), getY(), rbw);
        }
    }
}
