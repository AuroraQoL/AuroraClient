package me.aurora.client.features.misc;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static me.aurora.client.Aurora.mc;

public class RatEsp implements Module {

    private static final Set<Entity> entities = ConcurrentHashMap.newKeySet();

    /**
     * MODIFIED FROM SHADYADDONS
     * @author jxee
     */
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        if (e.entity instanceof EntityOtherPlayerMP) entities.add(e.entity);
    }

    /**
     * Following method has been circulating in Minecraft Hacking Community for a while, making it impossible to trace original author.
     */
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent e) {
        if(Config.ratEsp) {
            entities.removeIf(x -> x.getDisplayName() == null);
            for (Entity entity : entities) {
                final double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * e.partialTicks - mc.getRenderManager().viewerPosX;
                final double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * e.partialTicks - mc.getRenderManager().viewerPosY;
                final double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * e.partialTicks - mc.getRenderManager().viewerPosZ;
                GL11.glPushMatrix();
                GL11.glTranslated(x, y - 0.2, z);
                GL11.glScalef(0.03f, 0.03f, 0.03f);
                GL11.glRotated(-mc.getRenderManager().playerViewY, 0.0, 1.0, 0.0);
                GlStateManager.disableDepth();
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                mc.getTextureManager().bindTexture(new ResourceLocation("dailydungeons:res/bigrat.png"));
                Gui.drawModalRectWithCustomSizedTexture(50, 90, 0.0f, 0.0f, -100, -100, -100.0f, -100.0f);
                GlStateManager.enableDepth();
                GL11.glPopMatrix();
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        entities.clear();
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