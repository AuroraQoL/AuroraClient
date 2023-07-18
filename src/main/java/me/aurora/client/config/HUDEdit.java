package me.aurora.client.config;

import me.aurora.client.features.visual.Element;
import me.aurora.client.utils.hud.ModuleEditorHandler;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class HUDEdit extends ModuleEditorHandler {
    /**
     * IMPLEMENTED/MODIFIED FROM
     * https://github.com/Papa-Stalin/ClientAPI
     * UNDER MIT LICENSE
     */
    private Element drag;
    private int dragX;
    private int dragY;

    @Override
    public void render(int x, int y, int w, int h, int mouseX, int mouseY, Element component) {
        if (component.equals(drag)) {
            component.setX(dragX + mouseX);
            component.setY(dragY + mouseY);
        }

        Gui.drawRect(x - 2, y - 2, x + w + 2, y + h + 2, new Color(0x92FFFFFF, true).getRGB());
        renderModule(component);
    }

    @Override
    public void clickComponent(int mouseX, int mouseY, int mouseButton, Element component) {
        if (mouseButton == 0) {
            drag = component;
            dragX = component.getX() - mouseX;
            dragY = component.getY() - mouseY;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        drag = null;
    }

    @Override
    public void onClose() {
        //      ClientAPI.getModuleManager().getModule("HUDEditor").disable();
    }
}