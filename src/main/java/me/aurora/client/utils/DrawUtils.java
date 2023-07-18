package me.aurora.client.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@UtilityClass
public class DrawUtils {

    public void drawOutlinedRect(int x, int y, int width, int height, Color fill, Color outline) {
        GlStateManager.pushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableTexture2D();

        int xEnd = x+width;
        int yEnd = y+height;

        Gui.drawRect(x, y, xEnd, yEnd, fill.getRGB());

        GlStateManager.popMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableTexture2D();

        GL11.glLineWidth(2f);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);

        float[] outlineColor = translateToFloat(outline);

        GlStateManager.color(outlineColor[0], outlineColor[1], outlineColor[2], 1f);

        GL11.glBegin(GL11.GL_LINE_LOOP);

        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, yEnd);
        GL11.glVertex2d(xEnd, yEnd);
        GL11.glVertex2d(xEnd, y);

        GL11.glEnd();

        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        GlStateManager.enableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
        GlStateManager.popMatrix();
    }

    public float[] translateToFloat(Color color) {
        return new float[] { color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f };
    }
}
