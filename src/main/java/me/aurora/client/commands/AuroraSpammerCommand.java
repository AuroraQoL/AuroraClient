package me.aurora.client.commands;

import gg.essential.api.EssentialAPI;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.ThreadUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import org.jetbrains.annotations.NotNull;

public class AuroraSpammerCommand extends Command {

    public AuroraSpammerCommand() {
        super("auroraontop", true, false);
    }
    @DefaultHandler
    public void handle() {
        Aurora.mc.thePlayer.sendChatMessage("░█▀█░█░█░█▀▄░█▀█░█▀▄░█▀█░");
        ThreadUtils.sleep(100);
        Aurora.mc.thePlayer.sendChatMessage("░█▀█░█░█░█▀▄░█░█░█▀▄░█▀█░");
        ThreadUtils.sleep(100);
        Aurora.mc.thePlayer.sendChatMessage("░▀░▀░▀▀▀░▀░▀░▀▀▀░▀░▀░▀░▀░");
        ThreadUtils.sleep(100);
        Aurora.mc.thePlayer.sendChatMessage("░░░░░░░░░█▀█░█▀█░░░░░░░░░");
        ThreadUtils.sleep(100);
        Aurora.mc.thePlayer.sendChatMessage("░░░░░░░░░█░█░█░█░░░░░░░░░");
        ThreadUtils.sleep(100);
        Aurora.mc.thePlayer.sendChatMessage("░░░░░░░░░▀▀▀░▀░▀░░░░░░░░░");
        ThreadUtils.sleep(100);
        Aurora.mc.thePlayer.sendChatMessage("░░░░░░░▀█▀░█▀█░█▀█░░░░░░░");
        ThreadUtils.sleep(100);
        Aurora.mc.thePlayer.sendChatMessage("░░░░░░░░█░░█░█░█▀▀░░░░░░░");
        ThreadUtils.sleep(100);
        Aurora.mc.thePlayer.sendChatMessage("░░░░░░░░▀░░▀▀▀░▀░░░░░░░░░");
    }

}
