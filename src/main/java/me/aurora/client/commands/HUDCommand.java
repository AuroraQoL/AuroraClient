package me.aurora.client.commands;

import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import me.aurora.client.Aurora;


public class HUDCommand extends Command {
    public HUDCommand() {
        super("aurorahud", true, false);
    }

    @DefaultHandler
    public void handle() {
        Aurora.getHudEdit().display();
    }
}