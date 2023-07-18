package me.aurora.client.utils.hud;

import me.aurora.client.Aurora;
import me.aurora.client.features.visual.Element;
import net.minecraft.client.Minecraft;
import scala.xml.Elem;

import static me.aurora.client.Aurora.mc;

/**
 * @author Papa-Stalin Gabagooooooooooool
 * @version 1.1
 * @credit ClientAPI (Papa-Stalin)
 * @brief Module Editor
 */
public class ModuleEditorHandler {
    private ModuleEditor moduleEditor;

    public final void display() {

        if (moduleEditor == null)
            moduleEditor = new ModuleEditor();
        moduleEditor.initGui();
        Aurora.setGuiToOpen(moduleEditor);

    }

    public final void hide() {
        if (mc.currentScreen instanceof ModuleEditor)
            mc.displayGuiScreen(null);
    }

    public void onOpen() {}

    public void onClose() {}

    public void render(int x, int y, int w, int h, int mouseX, int mouseY, Element component) {
    }

    public void clickComponent(int mouseX, int mouseY, int mouseButton, Element component) {
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public boolean doesPauseGame() {
        return false;
    }

    public final void renderModule(Element component) {
        component.editorDraw();
    }

    public final ModuleEditor getModuleEditor() {
        return moduleEditor;
    }

    public void RenderGUI(Element m) {
        if (!(mc.currentScreen instanceof ModuleEditor) && m.enabled()) m.guiDraw();
    }
}
