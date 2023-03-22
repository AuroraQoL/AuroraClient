package me.aurora.client.config;

import gg.essential.api.EssentialAPI;
import me.aurora.client.Aurora;
import me.aurora.client.utils.ThemeUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;

public class MainHud extends GuiScreen {

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.addAll(Arrays.asList(
                buttonConstructor(1, -25, "Config"),
                buttonConstructor(2, 0, "Edit HUD"),
                buttonConstructor(3, 25, "Close")

                ));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1:
                EssentialAPI.getGuiUtil().openScreen(Config.INSTANCE.gui());
                break;
            case 2:
                Aurora.getHudEdit().display();
                break;
            case 3:
                mc.thePlayer.closeScreen();
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(new ResourceLocation("dailydungeons:res/biglogo.png"));
        GlStateManager.color(ThemeUtils.getFloatValue(0, 0), ThemeUtils.getFloatValue(0, 1), ThemeUtils.getFloatValue(0, 2));
        Gui.drawModalRectWithCustomSizedTexture(scaledResolution.getScaledWidth() / 2 - 64, scaledResolution.getScaledHeight() / 2 - 64, 0, 0, 128, 32, 128, 32);
        GlStateManager.popMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    private GuiButton buttonConstructor(int id, int heightOffset, String text) {
        final int buttonHeight = 20;
        final int buttonWidth = 128;
        final int centerPos = this.width / 2 - buttonWidth/2;
        final int baseHeight = this.height/2;
        return new GuiButton(id, centerPos, baseHeight + heightOffset, buttonWidth, buttonHeight, text);
    }
}