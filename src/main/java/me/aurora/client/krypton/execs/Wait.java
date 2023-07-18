package me.aurora.client.krypton.execs;

import me.aurora.client.krypton.management.MathVariableParser;

public class Wait implements IExec {
    @Override
    public Integer exec(String parameter) {
        try {
            Thread.sleep((int) MathVariableParser.parseMath(parameter));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
}
