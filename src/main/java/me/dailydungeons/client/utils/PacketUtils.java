package me.dailydungeons.client.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;

public class PacketUtils {

    static Minecraft mc = Minecraft.getMinecraft();
    public static void sendPacket(Packet<?> packet) {
        mc.getNetHandler().addToSendQueue(packet);
    }
}
