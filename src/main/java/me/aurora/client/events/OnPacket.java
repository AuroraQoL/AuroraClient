package me.aurora.client.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;


/**
 * IMPLEMENTED FROM SHADYADDONS
 * @author jxee
 */
@Cancelable
public class OnPacket extends Event {

    public Direction direction;
    public Packet<?> packet;

    public OnPacket(Packet<?> packet) {
        this.packet = packet;
    }

    public static class ReceiveEvent extends OnPacket {
        public ReceiveEvent(Packet<?> packet) {
            super(packet);
            this.direction = Direction.INBOUND;
        }
    }

    public static class SendEvent extends OnPacket {
        public SendEvent(Packet<?> packet) {
            super(packet);
            this.direction = Direction.OUTBOUND;
        }
    }

    enum Direction {
        INBOUND, OUTBOUND
    }

}
