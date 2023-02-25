package me.aurora.client.utils.hud;

import me.aurora.client.Aurora;
import me.aurora.client.features.visual.Element;
import net.minecraft.client.Minecraft;

public class ModuleEditorH {

    /**
     * IMPLEMENTED/MODIFIED FROM
     * https://github.com/Papa-Stalin/ClientAPI
     * UNDER MIT LICENSE
     */

    private ModuleEditor moduleEditor;



    public final void display()
    {

        if (moduleEditor == null) moduleEditor = new ModuleEditor();
      //  Minecraft.getMinecraft().displayGuiScreen(moduleEditor);
        moduleEditor.initGui();
        Aurora.setGuiToOpen(moduleEditor);

    }

    public final void hide()
    {
        if (Minecraft.getMinecraft().currentScreen instanceof ModuleEditor) Minecraft.getMinecraft().displayGuiScreen(null);
    }

    public void onOpen() {

    }
    public void onClose() { }
    public <Feature extends Element> void render(int x, int y, int w, int h, int mouseX, int mouseY, Feature component) {
    }
    public <Feature extends Element> void clickComponent(int mouseX, int mouseY, int mouseButton, Feature component) { }
    public void mouseReleased(int mouseX, int mouseY, int state) { }
    public boolean doesPauseGame() { return false; }

    public final <Feature extends Element> void renderModule(Feature component)
    {
        component.editorDraw();
    }

    public final ModuleEditor getModuleEditor()
    {
        return moduleEditor;
    }

    public void RenderGUI(Element m){
        if (!(Minecraft.getMinecraft().currentScreen instanceof ModuleEditor)){
            if (m.enabled()) m.guiDraw();
        }
    }
}
