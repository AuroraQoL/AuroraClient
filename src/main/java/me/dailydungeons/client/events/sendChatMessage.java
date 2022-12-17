package me.dailydungeons.client.events;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.awt.*;

@Cancelable
public class sendChatMessage extends Event {
    public String message;
    public boolean addToChat;

    public sendChatMessage(String message, boolean addToChat) {
        this.message = message;
        this.addToChat = addToChat;
    }
}
