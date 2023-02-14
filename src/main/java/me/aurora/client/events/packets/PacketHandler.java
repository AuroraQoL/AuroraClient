package me.aurora.client.events.packets;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

/**
 * @author Dragonain (hypixel.net forums)
 * @author TheAlphaEpsilon
 */

@ChannelHandler.Sharable
public class PacketHandler extends SimpleChannelInboundHandler {

    public static boolean firstConnection = true;
    public static long lastPacket = 0;

    public PacketHandler(){
        super(false);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        lastPacket = System.currentTimeMillis();
        ctx.fireChannelRead(msg);
    }

    @SubscribeEvent
    public void join(FMLNetworkEvent.ClientConnectedToServerEvent e){

        if(firstConnection) {
            firstConnection = false;
            ChannelPipeline pipeline = e.manager.channel().pipeline();
            pipeline.addBefore("packet_handler", this.getClass().getName(), this);
        }
    }

    @SubscribeEvent
    public void leave(FMLNetworkEvent.ClientDisconnectionFromServerEvent e){
        firstConnection = true;
    }
}