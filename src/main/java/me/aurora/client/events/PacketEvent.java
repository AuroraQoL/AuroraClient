package me.aurora.client.events;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * IMPLEMENTED FROM SHADYADDONS
 * @author jxee
 */
@Cancelable
public class PacketEvent
        extends Event {
    public Packet<?> packet;
    public ChannelHandlerContext context;

    public PacketEvent(Packet<?> packet, ChannelHandlerContext context) {
        this.packet = packet;
        this.context = context;
    }

    public static class Post
            extends Event {
        public Packet<?> packet;
        public ChannelHandlerContext context;

        public Post(Packet<?> packet, ChannelHandlerContext context) {
            this.packet = packet;
            this.context = context;
        }
    }
}