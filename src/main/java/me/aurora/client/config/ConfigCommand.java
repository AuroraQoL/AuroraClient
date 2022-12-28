package me.aurora.client.config;

import me.aurora.client.Aurora;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

/** IMPLEMENTED FROM HYADDONS */

/**
 * Heavily inspired by code from Danker's Skyblock Mod, used under GPL 3.0 license
 * https://github.com/bowser0000/SkyblockMod/blob/master/LICENSE
 * @author bowser0000
 */

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
