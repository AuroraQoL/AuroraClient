package me.aurora.client.features.visual;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.events.packets.PacketHandler;
import me.aurora.client.utils.CurrentColor;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

import static me.aurora.client.Aurora.mc;

public class Watermark extends Element {

    ResourceLocation kanitFontResourceLoc = new ResourceLocation("dailydungeons:font/kanit.ttf");
    MinecraftFontRenderer kanitFontRenderer;

    {
        try {
            kanitFontRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(kanitFontResourceLoc).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
        } catch (FontFormatException | IOException ignored) { }
    }

    public Watermark() {
        width = 125;
        height = 20;
    }

    @Override
    public int getX(){
        return Config.WATERMARK_X;
    }
    @Override
    public int getY(){
        return Config.WATERMARK_Y;
    }
    @Override
    public void setX(int val){
        Config.WATERMARK_X = val;
    }

    @Override
    public void setY(int val){
        Config.WATERMARK_Y = val;
    }
    @Override
    public boolean enabled(){
        return Config.hudWatermark;
    }
    @Override
    public void guiDraw(){
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int rbw = CurrentColor.currentColorGet(0);
       // if ((Config.hudWatermark)) {
            kanitFontRenderer.drawStringWithShadow("\247lAurora QoL \247r[" + "version 3.3" + "]", getX(), getY(), 0xA9A9A9);
            kanitFontRenderer.drawStringWithShadow("\247lAurora QoL \247r", getX(), getY(), 0xFFFFFF);
            kanitFontRenderer.drawStringWithShadow("\247lAurora", getX(), getY(), rbw);
            kanitFontRenderer.drawStringWithShadow("Build " + Aurora.CURRENTVERSIONBUILD, getX(), getY()+10, 0x444444);
      //  }
    }
    @Override
    public void editorDraw(){
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int rbw = CurrentColor.currentColorGet(0);
     //   if ((Config.hudWatermark)) {
            kanitFontRenderer.drawStringWithShadow("\247lAurora QoL \247r[" + "version xyz" + "]", getX(), getY(), 0xA9A9A9);
            kanitFontRenderer.drawStringWithShadow("\247lAurora QoL \247r", getX(), getY(), 0xFFFFFF);
            kanitFontRenderer.drawStringWithShadow("\247lAurora", getX(), getY(), rbw);
            kanitFontRenderer.drawStringWithShadow("Build xyz", getX(), getY()+10, 0x444444);
      //  }
    }
}
