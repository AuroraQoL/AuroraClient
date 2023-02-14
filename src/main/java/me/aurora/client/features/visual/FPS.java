package me.aurora.client.features.visual;

import me.aurora.client.config.Config;
import me.aurora.client.utils.CurrentColor;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

import static me.aurora.client.Aurora.mc;

public class FPS extends Element {

    ResourceLocation kanitFontResourceLoc = new ResourceLocation("dailydungeons:font/kanit.ttf");
    MinecraftFontRenderer kanitFontRenderer;

    {
        try {
            kanitFontRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(kanitFontResourceLoc).getInputStream()).deriveFont(Font.PLAIN, 30f), true, false);
        } catch (FontFormatException | IOException ignored) { }
    }

    public FPS() {
        width = 65;
        height = 15;
    }

    @Override
    public int getX(){
        return Config.FPS_X;
    }
    @Override
    public int getY(){
        return Config.FPS_Y;
    }
    @Override
    public void setX(int val){
        Config.FPS_X = val;
    }

    @Override
    public void setY(int val){
        Config.FPS_Y = val;
    }
    @Override
    public boolean enabled(){
        return Config.hudFPS;
    }
    @Override
    public void guiDraw(){
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int rbw = CurrentColor.currentColorGet(0);
        kanitFontRenderer.drawStringWithShadow("\247lFPS\247r:\2477 " + Minecraft.getDebugFPS(), getX(), getY(), 0xFFFFFF);
        for (int i = 0; i < 5; i++) {
            kanitFontRenderer.drawStringWithShadow("\247lFPS", getX(), getY(), rbw);
        }
    }
    @Override
    public void editorDraw(){
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int rbw = CurrentColor.currentColorGet(0);
        kanitFontRenderer.drawStringWithShadow("\247lFPS\247r:\2477 144", getX(), getY(), 0xFFFFFF);
        for (int i = 0; i < 5; i++) {
            kanitFontRenderer.drawStringWithShadow("\247lFPS", getX(), getY(), rbw);
        }
    }
}
