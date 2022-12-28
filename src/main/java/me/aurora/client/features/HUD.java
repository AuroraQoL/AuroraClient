package me.aurora.client.features;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.ColorUtils;
import me.aurora.client.utils.ToggledModulesUtility;
import me.aurora.client.utils.font.FontUtil;
import me.aurora.client.utils.font.MinecraftFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;

/**
 *
 * IMPORTANT!!
 *
 * THIS CODE WILL BE REWRITTEN IN THE NEXT RELEASE.
 *
 * SORRY FOR THIS PIECE OF SHIT BELLOW.
 *
 */

public class HUD {

    public static FontUtil mainFont;
    public static FontUtil jelloarray2;


    Font d;

    ResourceLocation c = new ResourceLocation("dailydungeons:font/kanit.ttf");
    MinecraftFontRenderer neufr = new MinecraftFontRenderer(Font.createFont(Font.TRUETYPE_FONT, Minecraft.getMinecraft().getResourceManager().getResource(c).getInputStream()).deriveFont(Font.PLAIN, 19f), true, false);

    public static transient double healthBarTarget = 0, healthBar = 0;

    public HUD() throws IOException, FontFormatException {
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent event) throws IOException, FontFormatException {
        if (event.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }

        //




        //

        Minecraft mc = Minecraft.getMinecraft();

        ScaledResolution scaledResolution = new ScaledResolution(mc);
        ScaledResolution sr = new ScaledResolution(mc);

        //    int rbw = ColorUtils.getRainbow2(-4000, 0).getRGB();
        int rbw;

        if(Config.colors==0) {
            rbw = ColorUtils.getRainbow2(-4000, 0).getRGB();

        } else if (Config.colors==1) {
            rbw = this.getGradientOffset(new Color(223, 94, 255), new Color(0, 170, 255).brighter(), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();
        } else if (Config.colors==2) {
            rbw = this.getGradientOffset(new Color(255, 0, 255), new Color(255, 255, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();

        } else if (Config.colors==3) {
            rbw = this.getGradientOffset(new Color(0, 132, 99), new Color(255, 255, 99), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();

        } else if (Config.colors==4) {
            rbw = this.getGradientOffset(new Color(255, 0, 0), new Color(255, 255, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();

        } else if (Config.colors==5) {
            rbw = this.getGradientOffset(new Color(0, 0, 255), new Color(0, 255, 132), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();

        } else if (Config.colors==6) {
            rbw = this.getGradientOffset(new Color(255, 255, 255), new Color(0, 0, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();
        } else if (Config.colors==7) {
            rbw = this.getGradientOffset(new Color(231, 239, 239), new Color(16, 16, 24), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();
        } else if (Config.colors==8) {
            rbw = this.getGradientOffset(new Color(255, 198, 123), new Color(24, 8, 8), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();
        } else {
            rbw = 255;

        }
       // Client.

//        KFontUtil.tneufr.drawStringWithShadow("test", 100, 100, rbw);

     //   test.drawStringWithShadow("test", 100, 100, rbw);

        //     final int rb2 = ColorUtils.getRainbow2(-4000, 0).getRGB();
   //     final int rbw = this.getGradientOffset(new Color(223, 94, 255), new Color(0, 170, 255).brighter(), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + 0).getRGB();
  //      FontRenderer neufr = Minecraft.getMinecraft().fontRendererObj;
        if ((Config.watermark)) {
         //   if (Config.watermarkstyle  null) {
                neufr.drawStringWithShadow("\247lAurora QoL \247r[" + "public 1.999" + "]", 5, 5, 0xA9A9A9);
                neufr.drawStringWithShadow("\247lAurora QoL \247r", 5, 5, 0xFFFFFF);
                neufr.drawStringWithShadow("\247lAurora", 5, 5, rbw);
                neufr.drawStringWithShadow("Build " + Aurora.CURRENTVERSIONBUILD, 5, 15, 0x444444);
                if (Config.watermarkstyle == 33) {
         //       mc.getTextureManager().bindTexture(new ResourceLocation("dailydungeons:font/logo.png"));
                //    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                //        GL11.glColor4f(255,255,255,255);
          //      GL11.glPushMatrix();
            //    GL11.glEnable(GL11.GL_BLEND);
             //   OpenGlHelper.glBlendFunc(770, 771, 1, 0);
              //  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
               // GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
          //      Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 491 / 3, 116 / 3, 491 / 3, 116 / 3);
               // GL11.glPopMatrix();

            } else if (Config.watermarkstyle == 34) {
              //  mc.getTextureManager().bindTexture(new ResourceLocation("dailydungeons:font/daily2.png"));
                //    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                //        GL11.glColor4f(255,255,255,255);
            /*    GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
             */  // Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 491 / 3, 116 / 3, 491 / 3, 116 / 3);
                    //
                    /*
                GL11.glPopMatrix();*/

            } else {
           //     mc.getTextureManager().bindTexture(new ResourceLocation("dailydungeons:font/old.png"));
                    //    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    //        GL11.glColor4f(255,255,255,255);
          /*          GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
          */
                    //Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 491 / 3, 116 / 3, 491 / 3, 116 / 3);
                 /*   GL11.glPopMatrix();*/
            }





        } else {


        }

        if (Config.keystrokes) {
            /////////KEYSTROKES
            final int noracismW = new Color(0, 1, 0, 100).getRGB();
            final int noracismA = new Color(0, 0, 1, 100).getRGB();
            final int noracismS = new Color(1, 0, 0, 100).getRGB();
            final int noracismD = new Color(1, 0, 1, 100).getRGB();
            final int noracism2 = new Color(255, 255, 255, 206).getRGB();
            final int noracism21 = new Color(255, 255, 255, 205).getRGB();
            final int noracism22 = new Color(255, 255, 255, 204).getRGB();
            final int noracism23 = new Color(255, 255, 255, 203).getRGB();
            final int noracism24 = new Color(255, 255, 255, 202).getRGB();
            final int noracism25 = new Color(255, 255, 255, 201).getRGB();
            final int xddddddddd3 = new Color(255, 255, 255).getRGB();
            //     final int xddddddddd4 = new Color(10, 129, 145).getRGB();
            final int xddddddddd4 = rbw;

            if (mc.gameSettings.isKeyDown(mc.gameSettings.keyBindForward)) {


                Gui.drawRect(100 +(-73+Config.keystrokesX), 100+(-100+Config.keystrokesY), 125+(-73+Config.keystrokesX), 125+(-100+Config.keystrokesY), noracism21);
                neufr.drawStringWithShadow("W", 114+(-77+Config.keystrokesX), 106+(-97+Config.keystrokesY), xddddddddd4);

            } else {

                Gui.drawRect(100+(-73+Config.keystrokesX), 100+(-100+Config.keystrokesY), 125+(-73+Config.keystrokesX), 125+(-100+Config.keystrokesY), noracismW);
                neufr.drawStringWithShadow("W", 114+(-77+Config.keystrokesX), 106+(-97+Config.keystrokesY), xddddddddd3);

            }

            if (mc.gameSettings.isKeyDown(mc.gameSettings.keyBindLeft)) {

                Gui.drawRect(73+(-73+Config.keystrokesX), 127+(-100+Config.keystrokesY), 98+(-73+Config.keystrokesX), 152+(-100+Config.keystrokesY), noracism22);
                neufr.drawStringWithShadow("A", 87+(-77+Config.keystrokesX), 133+(-97+Config.keystrokesY), xddddddddd4);

            } else {

                Gui.drawRect(73+(-73+Config.keystrokesX), 127+(-100+Config.keystrokesY), 98+(-73+Config.keystrokesX), 152+(-100+Config.keystrokesY), noracismA);
                neufr.drawStringWithShadow("A", 87+(-77+Config.keystrokesX), 133+(-97+Config.keystrokesY), xddddddddd3);

            }
            if (mc.gameSettings.isKeyDown(mc.gameSettings.keyBindBack)) {

                Gui.drawRect(100+(-73+Config.keystrokesX), 127+(-100+Config.keystrokesY), 125+(-73+Config.keystrokesX), 152+(-100+Config.keystrokesY), noracism23);
                neufr.drawStringWithShadow("S", 114+(-77+Config.keystrokesX), 133+(-97+Config.keystrokesY), xddddddddd4);

            } else {

                Gui.drawRect(100+(-73+Config.keystrokesX), 127+(-100+Config.keystrokesY), 125+(-73+Config.keystrokesX), 152+(-100+Config.keystrokesY), noracismS);
                neufr.drawStringWithShadow("S", 114+(-77+Config.keystrokesX), 133+(-97+Config.keystrokesY), xddddddddd3);

            }
            if (mc.gameSettings.isKeyDown(mc.gameSettings.keyBindRight)) {

                Gui.drawRect(127+(-73+Config.keystrokesX), 127+(-100+Config.keystrokesY), 152+(-73+Config.keystrokesX), 152+(-100+Config.keystrokesY), noracism24);
                neufr.drawStringWithShadow("D", 141+(-77+Config.keystrokesX), 133+(-97+Config.keystrokesY), xddddddddd4);

            } else {

                Gui.drawRect(127+(-73+Config.keystrokesX), 127+(-100+Config.keystrokesY), 152+(-73+Config.keystrokesX), 152+(-100+Config.keystrokesY), noracismD);
                neufr.drawStringWithShadow("D", 141+(-77+Config.keystrokesX), 133+(-97+Config.keystrokesY), xddddddddd3);

            }
        }


        if (Config.Healthbar && (mc.thePlayer.getLastAttacker() != null)) {


            healthBarTarget = scaledResolution.getScaledWidth() / 2 + 15 + (((125) / (mc.thePlayer.getLastAttacker().getMaxHealth())) * (mc.thePlayer.getLastAttacker().getHealth()));

            double HealthBarSpeed = 3;

            if (healthBar > healthBarTarget) {
                healthBar = ((healthBar) - ((healthBar - healthBarTarget) / HealthBarSpeed));
            } else if (healthBar < healthBarTarget) {
                //healthBar = healthBarTarget;
                healthBar = ((healthBar) + ((healthBarTarget - healthBar) / HealthBarSpeed));
            }


            Gui.drawRect(scaledResolution.getScaledWidth() / 2 + 15, scaledResolution.getScaledHeight() / 2 + 15, scaledResolution.getScaledWidth() / 2 + 140, scaledResolution.getScaledHeight() / 2 + 60, new Color(1, 1, 1, 100).getRGB());
            Gui.drawRect(scaledResolution.getScaledWidth() / 2 + 15, scaledResolution.getScaledHeight() / 2 + 55, scaledResolution.getScaledWidth() / 2 + 140, scaledResolution.getScaledHeight() / 2 + 60, new Color(1, 1, 1, 100).getRGB());
            Gui.drawRect(scaledResolution.getScaledWidth() / 2 + 15, scaledResolution.getScaledHeight() / 2 + 55, (int) healthBar, scaledResolution.getScaledHeight() / 2 + 60, rbw);
            //	Gui.drawRect(sr.getScaledWidth() / 2 - 41, sr.getScaledHeight() / 2 + 100 + 54, healthBarTarget, sr.getScaledHeight() / 2 + 96 + 45, color);

            GlStateManager.color(1, 1, 1);
            neufr.drawStringWithShadow(mc.thePlayer.getLastAttacker().getName(), sr.getScaledWidth() / 2 + 22, sr.getScaledHeight() / 2 + 22, rbw);
         //   GlStateManager.scale(2, 2, 1);
            GL11.glPushMatrix();
            GL11.glScalef(2,2,1);
            neufr.drawStringWithShadow("\u2764" + mc.thePlayer.getLastAttacker().getHealth(), scaledResolution.getScaledWidth() / 4 + 11, scaledResolution.getScaledHeight() / 4 + 17, rbw);
            GL11.glScalef(1,1,1);
            GL11.glPopMatrix();}
        if (Config.arrayList) {
            int yPos = 0;
        float y3 = 0.0f;

        for (String module : ToggledModulesUtility.toggled()) {
//COLORS
            if (Config.colors == 0) {
                rbw = ColorUtils.getRainbow2(-4000, (int) y3).getRGB();

            } else if (Config.colors == 1) {
                rbw = this.getGradientOffset(new Color(223, 94, 255), new Color(0, 170, 255).brighter(), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + y3).getRGB();
            } else if (Config.colors == 2) {
                rbw = this.getGradientOffset(new Color(255, 0, 255), new Color(255, 255, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + y3).getRGB();

            } else if (Config.colors == 3) {
                rbw = this.getGradientOffset(new Color(0, 132, 99), new Color(255, 255, 99), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + y3).getRGB();

            } else if (Config.colors == 4) {
                rbw = this.getGradientOffset(new Color(255, 0, 0), new Color(255, 255, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + y3).getRGB();

            } else if (Config.colors == 5) {
                rbw = this.getGradientOffset(new Color(0, 0, 255), new Color(0, 255, 132), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + y3).getRGB();

            } else if (Config.colors == 6) {
                rbw = this.getGradientOffset(new Color(255, 255, 255), new Color(0, 0, 0), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + y3).getRGB();
            } else if (Config.colors == 7) {
                rbw = this.getGradientOffset(new Color(231, 239, 239), new Color(16, 16, 24), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + y3).getRGB();
            } else if (Config.colors == 8) {
                rbw = this.getGradientOffset(new Color(255, 198, 123), new Color(24, 8, 8), Math.abs(System.currentTimeMillis() / 16L) / 100.0 + y3).getRGB();
            } else {
                rbw = 255;

            }
            /// COLORS
            double aa = neufr.getStringWidth(module)+ 2;
            final float xPos = scaledResolution.getScaledWidth() -  ((float) aa) ;
            //Gui.drawRect((int) ((int) xPos - 4f), yPos, (int) (xPos - 2.5f), yPos + 11, rbw);
            Gui.drawRect((int) (xPos - 3f), yPos, scaledResolution.getScaledWidth(), yPos + 11,
                    new Color(0, 0, 0, 100).getRGB());
            Gui.drawRect((int) (scaledResolution.getScaledWidth() - 1f), yPos, (int) (scaledResolution.getScaledWidth() + 0.5f),
                    yPos + 11, rbw);
            neufr.drawStringWithShadow(module, xPos - 1f, yPos + 1f, rbw);
            yPos += 11;
            y3 += 0.07f;

        }
            }
        }


    public Color getGradientOffset(final Color color1, final Color color2, double offset) {
        if (offset > 1.0) {
            final double left = offset % 1.0;
            final int off = (int)offset;
            offset = ((off % 2 == 0) ? left : (1.0 - left));
        }
        final double inverse_percent = 1.0 - offset;
        final int redPart = (int)(color1.getRed() * inverse_percent + color2.getRed() * offset);
        final int greenPart = (int)(color1.getGreen() * inverse_percent + color2.getGreen() * offset);
        final int bluePart = (int)(color1.getBlue() * inverse_percent + color2.getBlue() * offset);
        return new Color(redPart, greenPart, bluePart);
    }

    }

