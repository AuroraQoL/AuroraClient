package me.aurora.client.features.visual;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.DrawUtils;
import me.aurora.client.utils.ThemeUtils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Speedometer extends Element {
    public Speedometer() {
        width = 100;
        height = 50;
    }

    private long tickCounter = 0;
    private int inactiveSince = 0;
    private final Queue<Double> speed = new LinkedList<>();
    private final List<Double> lastEntries = new ArrayList<>();

    @Override
    public int getX() {
        return Config.SPEED_X;
    }

    @Override
    public int getY() {
        return Config.SPEED_Y;
    }

    @Override
    public void editorDraw() {
        guiDraw();
    }

    @Override
    public void setX(int x) {
        Config.SPEED_X = x;
    }

    @Override
    public void setY(int y) {
        Config.SPEED_Y = y;
    }

    private double getTotalMovement() {
        return Math.abs(Aurora.mc.thePlayer.motionX) + Math.abs(Aurora.mc.thePlayer.motionY) + Math.abs(Aurora.mc.thePlayer.motionZ);
    }

    private double getMin() {
        return speed.stream().min(Double::compareTo).orElse(Double.MIN_VALUE);
    }

    private double getMax() {
        return speed.stream().max(Double::compareTo).orElse(Double.MAX_VALUE);
    }

    private double getRange() {
        return getMax() - getMin();
    }

    /**
     * {@link #isInactive()} could be used, but this isn't good for performance as calculation is performed twice.
     * therefore, an ugly solution will be used
     */
    private double getOffsetY(double value) {
        double range = getRange();
        if (range == 0) return getY() + height;
        else return getY() + (getMax() - value) / range * height;
    }

    private boolean isInactive() {
        return getRange() == 0;
    }

    private double getOffsetX(double pos) {
        int posCurr = width + getX() - speed.size();
        return posCurr + pos;
    }

    public void guiDraw() {
        tickCounter++;
        lastEntries.add(getTotalMovement());
        if (tickCounter % 5 == 0) {
            speed.offer(lastEntries.stream().mapToDouble(Double::doubleValue).average().orElse(0d));
            lastEntries.clear();
            if (tickCounter % 60 == 0) {
                if (isInactive()) {
                    if (inactiveSince < 19) {
                        inactiveSince++;
                    }
                }
                else {
                    int i = 0;
                    while (inactiveSince > 0 && i < 3) {
                        i++;
                        inactiveSince--;
                    }
                }
            }
        }

        if (speed.size() > width)
            speed.remove();

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.disableTexture2D();

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH, GL11.GL_NICEST);

        GL11.glLineWidth(3f);

        float[] color = DrawUtils.translateToFloat(ThemeUtils.getThemeColor(0f));
        GlStateManager.color(color[0], color[1], color[2], 1.0f - (0.05f * inactiveSince));

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();

        worldRenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);

        int counter = 0;
        for (double d : speed) {
            worldRenderer.pos(getOffsetX(counter), getOffsetY(d), 0).endVertex();
            counter++;
        }

        tessellator.draw();

        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        GlStateManager.resetColor();
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.enableTexture2D();
    }

    @Override
    public boolean enabled() {
        return Config.hudSpeedgraph;
    }
}
