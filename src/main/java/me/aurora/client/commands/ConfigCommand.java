package me.aurora.client.commands;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import me.aurora.client.config.Config;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super("aurora", true, false);
    }

    @DefaultHandler
/*    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(Config.INSTANCE.gui());
    }*/
    public void handle() {
        EssentialAPI.getGuiUtil().openScreen(new me.aurora.client.config.MainHud());
    }
}