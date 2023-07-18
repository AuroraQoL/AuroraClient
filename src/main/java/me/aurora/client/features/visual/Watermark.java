package me.aurora.client.features.visual;

import me.aurora.client.config.Config;
import me.aurora.client.utils.ThemeUtils;
import me.aurora.client.utils.font.FontDefiner;
import me.aurora.client.utils.font.FontRender;

public class Watermark extends Element {
    private static final FontRender fontRenderer = FontDefiner.getFontRenderer();
    private static final FontRender logoRenderer = FontDefiner.getFontRenderer(30f);
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
        drawForVersion("4.0-Free");
    }
    @Override
    public void editorDraw(){
        drawForVersion("6.9-Funny");
    }

    private void drawForVersion(String buildID){
        logoRenderer.drawStringWithShadow("\247lAurora", getX(), getY(), ThemeUtils.currentColorGet(0));
        fontRenderer.drawStringWithShadow("Version " + buildID, getX(), getY()+15, 0x444444);
    }
}
