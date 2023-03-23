package me.aurora.client.commands;

import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import me.aurora.client.utils.MessageUtils;

import java.util.concurrent.CompletableFuture;

import static me.aurora.client.Aurora.mc;

public class CrabbyCommand extends Command {

    public CrabbyCommand() {
        super("crabby", true, false);
    }

    @DefaultHandler
    public void handle() {
        MessageUtils.sendDelayedMultilinePlayerMessage(
                100,
                "゜░゜░゜゜゜゜░゜░゜",
                "░゜░゜░゜゜░゜░゜░゜",
                "░゜゜゜░゜゜░゜゜゜░゜",
                "゜░゜░゜゜゜゜░゜░゜",
                "゜░゜░゜゜゜゜░゜░゜",
                "゜░゜゜░░░░゜゜░゜",
                "░゜゜゜゜゜゜゜゜゜゜░",
                "░゜▀゜█▄▄█゜▀゜░",
                "░゜゜゜゜゜゜゜゜゜゜░",
                "゜░░░░░░░░░░゜"
        );
    }
}
