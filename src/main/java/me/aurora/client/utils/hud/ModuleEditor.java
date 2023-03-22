package me.aurora.client.utils.hud;

import me.aurora.client.Aurora;
import me.aurora.client.features.visual.Element;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;


/**
 * @credit ClientAPI (Papa-Stalin)
 * @author Papa-Stalin Gabagooooooooooool
 * @version 1.1
 * @brief Module Editor
 */
public class ModuleEditor extends GuiScreen {

        @Override
        public void drawScreen(int mouseX, int mouseY, float partialTicks)
        {
            // crabby
            mc.getTextureManager().bindTexture(new ResourceLocation("dailydungeons:res/crab.png"));
            Gui.drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 100, 100, 100, 100);
            //
            this.drawDefaultBackground();
            for (Element m : Aurora.getHudModules())
            {
                Aurora.getHudEdit().render(m.getX(), m.getY(), m.width, m.height, mouseX, mouseY, m);
            }
        }

        @Override
        protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
        {
            for (Element m : Aurora.getHudModules())
            {
                if (isHover(m.getX(), m.getY(), m.width, m.height, mouseX, mouseY))
                {
                    Aurora.getHudEdit().clickComponent(mouseX, mouseY, mouseButton, m);
                }
            }
        }

        @Override
        protected void mouseReleased(int mouseX, int mouseY, int state)
        {
            Aurora.getHudEdit().mouseReleased(mouseX, mouseY, state);
        }

        @Override
        public void initGui()
        {
        }

        @Override
        public void onGuiClosed()
        {
            Aurora.getHudEdit().onClose();
        }

        @Override
        public boolean doesGuiPauseGame()
        {
            return Aurora.getHudEdit().doesPauseGame();
        }



        private boolean isHover(int X, int Y, int W, int H, int mX, int mY)
        {
            return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
        }

}
