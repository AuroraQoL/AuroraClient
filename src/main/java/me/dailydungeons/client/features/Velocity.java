package me.dailydungeons.client.features;


import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.utils.SkyBlockID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;


public class Velocity {

    static Minecraft mc = Minecraft.getMinecraft();


    public static void onPacket(S27PacketExplosion packet) {
            mc.thePlayer.motionX -= packet.func_149149_c();
            mc.thePlayer.motionY -= packet.func_149144_d();
            mc.thePlayer.motionZ -= packet.func_149147_e();
    }
    private boolean autoDisable() {
        if (Config.Velocity && mc.thePlayer.isInLava()) return false;

        if (mc.thePlayer.getHeldItem() != null) {
            String itemID = SkyBlockID.getSkyBlockID(mc.thePlayer.getHeldItem());
            return itemID.equals("JERRY STAFF") || itemID.equals("BONZO_STAFF") || itemID.equals("SILK_EDGE_SWORD") || itemID.equals("LEAPING_SWORD");
        }
        return true;
    }

}

