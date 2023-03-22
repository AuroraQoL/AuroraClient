package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 1.2
 * @brief Rat/Crabby ESP
 */
public class RatEsp implements Module {
    private static final ResourceLocation CRABBY = new ResourceLocation("dailydungeons:res/crab.png");
    private static final ResourceLocation RAT = new ResourceLocation("dailydungeons:res/bigrat.png");

    /**
     * Following method has been circulating in Minecraft Hacking Community for a while, making it impossible to trace original author.
     */
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        if(toggled()) {
            mc.theWorld.getLoadedEntityList().stream().filter(e -> e instanceof EntityOtherPlayerMP).forEach(entity -> {
                double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * event.partialTicks - mc.getRenderManager().viewerPosX;
                double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * event.partialTicks - mc.getRenderManager().viewerPosY;
                double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * event.partialTicks - mc.getRenderManager().viewerPosZ;
                GL11.glPushMatrix();
                GL11.glTranslated(x, y - 0.2, z);
                GL11.glScalef(0.03f, 0.03f, 0.03f);
                GL11.glRotated(-mc.getRenderManager().playerViewY, 0.0, 1.0, 0.0);
                GlStateManager.disableDepth();
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                mc.getTextureManager().bindTexture(Config.ratEsp_crabbyMode ? CRABBY : RAT);
                Gui.drawModalRectWithCustomSizedTexture(50, 90, 0.0f, 0.0f, -100, -100, -100.0f, -100.0f);
                GlStateManager.enableDepth();
                GL11.glPopMatrix();
            });
        }
    }

    @Override
    public boolean toggled() {
        return Config.ratEsp;
    }

    @Override
    public String name() {
        return "RatESP";
    }
}