package me.dailydungeons.client.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;


// implemented from https://github.com/ShadyAddons/ShadyAddons on GPLv3

@Cancelable
public class onPacket extends Event {

    public Direction direction;
    public Packet<?> packet;

    public onPacket(Packet<?> packet) {
        this.packet = packet;
    }

    public static class ReceiveEvent extends onPacket {
        public ReceiveEvent(Packet<?> packet) {
            super(packet);
            this.direction = Direction.INBOUND;
        }
    }

    public static class SendEvent extends onPacket {
        public SendEvent(Packet<?> packet) {
            super(packet);
            this.direction = Direction.OUTBOUND;
        }
    }

    enum Direction {
        INBOUND, OUTBOUND
    }

}
