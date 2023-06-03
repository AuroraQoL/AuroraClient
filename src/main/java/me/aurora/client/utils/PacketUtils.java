package me.aurora.client.utils;

import me.aurora.client.Aurora;
import net.minecraft.network.Packet;

/**
 * IMPLEMENTED FROM SHADYADDONS
 *
 * @author jxee
 */

public class PacketUtils {
    public static void sendPacket(Packet<?> packet) {
        Aurora.mc.getNetHandler().addToSendQueue(packet);
    }
}
