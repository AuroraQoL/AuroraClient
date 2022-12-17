package me.dailydungeons.client.events;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class MotionUpdateEvent
        extends Event {
    public float yaw;
    public float pitch;
    public double x;
    public double y;
    public double z;
    public boolean onGround;
    public boolean sprinting;
    public boolean sneaking;

    protected MotionUpdateEvent(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean sprinting, boolean sneaking) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.sneaking = sneaking;
        this.sprinting = sprinting;
    }

    @Cancelable
    public static class Post
            extends MotionUpdateEvent {
        public Post(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean sprinting, boolean sneaking) {
            super(x, y, z, yaw, pitch, onGround, sprinting, sneaking);
        }

        public Post(MotionUpdateEvent event) {
            super(event.x, event.y, event.z, event.yaw, event.pitch, event.onGround, event.sprinting, event.sneaking);
        }
    }

    @Cancelable
    public static class Pre
            extends MotionUpdateEvent {
        public Pre(double x, double y, double z, float yaw, float pitch, boolean onGround, boolean sprinting, boolean sneaking) {
            super(x, y, z, yaw, pitch, onGround, sprinting, sneaking);
        }
    }
}
