package me.aurora.client.commands;

import me.aurora.client.Aurora;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class ConfigCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "aurora";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
          Aurora.guiToOpen = Aurora.config.gui();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

}
