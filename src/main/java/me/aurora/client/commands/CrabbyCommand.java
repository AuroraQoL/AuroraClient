package me.aurora.client.commands;

import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import me.aurora.client.utils.MessageUtils;

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
