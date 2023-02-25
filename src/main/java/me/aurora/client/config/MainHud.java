package me.aurora.client.config;

import gg.essential.api.EssentialAPI;
import me.aurora.client.Aurora;
import me.aurora.client.utils.CurrentColor;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainHud extends GuiScreen {
    private GuiButton mButtonClose;
    private GuiButton buttonConfig;
    private GuiButton buttonEdit;

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(mButtonClose = new GuiButton(1, this.width / 2 - 64, this.height/2 + 25, 128, 20, "Close"));
        this.buttonList.add(buttonEdit = new GuiButton(1, this.width / 2 - 64, this.height/2 , 128, 20, "Edit Hud"));
        this.buttonList.add(buttonConfig = new GuiButton(2, this.width / 2 - 64, this.height/2 - 25, 128, 20, "Config"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == mButtonClose) mc.thePlayer.closeScreen();
        if (button == buttonConfig) EssentialAPI.getGuiUtil().openScreen(Config.INSTANCE.gui());
        if (button == buttonEdit) Aurora.getHudEdit().display();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(new ResourceLocation("dailydungeons:res/biglogo.png"));
        GlStateManager.color(CurrentColor.getFloatValue(0, 0), CurrentColor.getFloatValue(0, 1), CurrentColor.getFloatValue(0, 2));
        Gui.drawModalRectWithCustomSizedTexture(scaledResolution.getScaledWidth() / 2 - 64, scaledResolution.getScaledHeight() / 2 - 64, 0, 0, 128, 32, 128, 32);
        GlStateManager.popMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
}