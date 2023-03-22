package me.aurora.client.features.visual;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.ThemeUtils;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;

public class Watermark extends Element {
    private static final FontRender fontRenderer = FontDefiner.getFontRenderer();
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
        drawForVersion(Aurora.CURRENT_VERSION_BUILD, "3.4 preview");
    }
    @Override
    public void editorDraw(){
        drawForVersion(1337, "6.9 funni update");
    }

    private void drawForVersion(int build, String buildID){
        fontRenderer.drawStringWithShadow(String.format("\247lAurora QoL \247r[ %s ]", buildID), getX(), getY(), 0xA9A9A9);
        fontRenderer.drawStringWithShadow("\247lAurora QoL \247r", getX(), getY(), 0xFFFFFF);
        fontRenderer.drawStringWithShadow("\247lAurora", getX(), getY(), ThemeUtils.currentColorGet(0));
        fontRenderer.drawStringWithShadow("Codename Lambda | Build " + build, getX(), getY()+10, 0x444444);
    }
}
