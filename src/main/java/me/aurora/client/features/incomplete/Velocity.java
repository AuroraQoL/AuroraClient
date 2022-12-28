package me.aurora.client.features.incomplete;


import me.aurora.client.config.Config;
import me.aurora.client.utils.SkyBlockID;
import net.minecraft.client.Minecraft;
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

