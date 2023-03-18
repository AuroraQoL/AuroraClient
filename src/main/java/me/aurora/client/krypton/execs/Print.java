package me.aurora.client.krypton.execs;

import me.aurora.client.krypton.management.StringVariableParser;
import me.aurora.client.utils.MessageUtils;

public class Print implements IExec {

    @Override
    public Integer exec(String parameter) {
        MessageUtils.sendClientMessage(StringVariableParser.parseVar(parameter));
        return 1;
    }
}
