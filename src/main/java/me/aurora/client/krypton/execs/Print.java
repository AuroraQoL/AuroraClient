package me.aurora.client.krypton.execs;

import me.aurora.client.krypton.management.StringVariableParser;
import me.aurora.client.utils.ClientMessages;

public class Print implements IExec {

    @Override
    public Integer exec(String parameter) {
        ClientMessages.sendClientMessage(StringVariableParser.parseVar(parameter));
        return 1;
    }
}
