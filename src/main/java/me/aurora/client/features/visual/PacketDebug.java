package me.aurora.client.features.visual;

import me.aurora.client.config.Config;
import me.aurora.client.utils.PacketHandler;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;

import java.text.DecimalFormat;

public class PacketDebug extends Element {
    private static final FontRender fontRenderer = FontDefiner.getFontRenderer();

    public PacketDebug() {
        width = 200;
        height = 10;
    }

    @Override
    public int getX() {
        return Config.PACKET_X;
    }

    @Override
    public void setX(int val) {
        Config.PACKET_X = val;
    }

    @Override
    public int getY() {
        return Config.PACKET_Y;
    }

    @Override
    public void setY(int val) {
        Config.PACKET_Y = val;
    }

    @Override
    public boolean enabled() {
        return Config.hudPacket;
    }

    @Override
    public void guiDraw() {
        fontRenderer.drawStringWithShadow("Time since last packet: " + new DecimalFormat("#.#").format((double) (System.currentTimeMillis() - PacketHandler.lastPacket) / 1000d) + "s", getX(), getY(), 0xFF0000);
    }

    @Override
    public void editorDraw() {
        fontRenderer.drawStringWithShadow("Time since last packet: 6.9s", getX(), getY(), 0xFF0000);
    }
}
