package me.aurora.client.features.visual;

import me.aurora.client.config.Config;
import me.aurora.client.events.packets.PacketHandler;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

import static me.aurora.client.Aurora.fontLocation;

public class PacketDebug extends Element {
    DecimalFormat df = new DecimalFormat("#.#");

    MinecraftFontRenderer kanitFontRenderer;

    {
        try {
            kanitFontRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(fontLocation).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
        } catch (FontFormatException | IOException ignored) { }
    }

    ResourceLocation dyslexiaFontLoc = new ResourceLocation("dailydungeons:res/dys.ttf");
    MinecraftFontRenderer dyslexiaRenderer;

    {
        try {
            dyslexiaRenderer = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(dyslexiaFontLoc).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);
        } catch (FontFormatException | IOException ignored) { }
    }
    public PacketDebug() {
        width = 200;
        height = 10;
    }

    @Override
    public int getX(){
        return Config.PACKET_X;
    }
    @Override
    public int getY(){
        return Config.PACKET_Y;
    }
    @Override
    public void setX(int val){
        Config.PACKET_X = val;
    }

    @Override
    public void setY(int val){
        Config.PACKET_Y = val;
    }
    @Override
    public boolean enabled(){
        return Config.hudPacket;
    }
    @Override
    public void guiDraw(){
        kanitFontRenderer.drawStringWithShadow("Time since last packet: " + df.format((double) (System.currentTimeMillis() - PacketHandler.lastPacket) / 1000d) + "s", getX(), getY(), 0xFF0000);
    }
    @Override
    public void editorDraw(){
        kanitFontRenderer.drawStringWithShadow("Time since last packet: 6.9s", getX(), getY(), 0xFF0000);
    }
}
