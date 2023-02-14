package me.aurora.client.utils.hud;

import me.aurora.client.Aurora;
import me.aurora.client.features.visual.Element;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class ModuleEditor extends GuiScreen {

    /**
     * IMPLEMENTED/MODIFIED FROM
     * https://github.com/Papa-Stalin/ClientAPI
     * UNDER MIT LICENSE
     */


        @Override
        public void drawScreen(int mouseX, int mouseY, float partialTicks)
        {
            // crabby
            mc.getTextureManager().bindTexture(new ResourceLocation("dailydungeons:font/crab.png"));
            Gui.drawModalRectWithCustomSizedTexture(5, 5, 0, 0, 100, 100, 100, 100);
            //
            this.drawDefaultBackground();
            for (Element m : Aurora.getHudModules())
            {
                Aurora.hudEdit.render(m.getX(), m.getY(), m.width, m.height, mouseX, mouseY, m);
            }
        }

        @Override
        protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
        {
            for (Element m : Aurora.getHudModules())
            {
                if (isHover(m.getX(), m.getY(), m.width, m.height, mouseX, mouseY))
                {
                    Aurora.hudEdit.clickComponent(mouseX, mouseY, mouseButton, m);
                }
            }
        }

        @Override
        protected void mouseReleased(int mouseX, int mouseY, int state)
        {
            Aurora.hudEdit.mouseReleased(mouseX, mouseY, state);
        }

        @Override
        public void initGui()
        {
        }

        @Override
        public void onGuiClosed()
        {
            Aurora.hudEdit.onClose();
        }

        @Override
        public boolean doesGuiPauseGame()
        {
            return Aurora.hudEdit.doesPauseGame();
        }



        private boolean isHover(int X, int Y, int W, int H, int mX, int mY)
        {
            return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
        }

}
