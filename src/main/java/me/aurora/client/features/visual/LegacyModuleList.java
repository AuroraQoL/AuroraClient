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
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import java.awt.Color;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 4.0
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
        int currentModule = 0;
        Deque<Vector2f> edgesQueue = new LinkedList<>();
        List<String> modules = Aurora.getModules().stream().filter(Module::toggled).map(Module::name).sorted(fontRenderer).collect(Collectors.toList());
        for (String moduleName : modules) {
            int currentPhase = currentModule;
            currentModule++;
            int color = ThemeUtils.currentColorGet(currentPhase * 0.07f);
            int xPos = (int) (scaledResolution.getScaledWidth() - fontRenderer.getStringWidth(moduleName));
            int yPos = currentPhase * 11;
            int leftX = xPos - 4;
            int bottomY = yPos + 11;
            Gui.drawRect(leftX, yPos, scaledResolution.getScaledWidth(), bottomY, new Color(0, 0, 0, 30).getRGB());
            edgesQueue.addLast(new Vector2f(leftX, yPos));
            edgesQueue.addLast(new Vector2f(leftX, bottomY));
            fontRenderer.drawStringWithShadow(moduleName, xPos - 1.5f, yPos + 1f, color);
        }
        if (!edgesQueue.isEmpty()) {
            Vector2f last = edgesQueue.getLast();
            edgesQueue.add(new Vector2f(scaledResolution.getScaledWidth(), last.getY()));
            GL11.glLineWidth(2f);
            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
            float current = 0;
            while (edgesQueue.size() > 1) {
                float currentOffset = current * 0.07f;
                current += 0.5f;
                Vector2f currVector = edgesQueue.pollFirst();
                Vector2f nextVector = edgesQueue.peekFirst();
                if (Objects.nonNull(currVector) && Objects.nonNull(nextVector)) {
                    GL11.glBegin(GL11.GL_LINES);
                    GL11.glColor4f(ThemeUtils.getFloatValue(currentOffset, 0), ThemeUtils.getFloatValue(currentOffset, 1), ThemeUtils.getFloatValue(currentOffset, 2), 0.5f);
                    GL11.glVertex2f(currVector.getX(), currVector.getY());
                    GL11.glVertex2f(nextVector.getX(), nextVector.getY());
                    GL11.glEnd();
                }
            }
            GL11.glColor4f(1f,1f,1f,1f);
            GlStateManager.popMatrix();
            GlStateManager.enableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.disableAlpha();
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }
    }
}

