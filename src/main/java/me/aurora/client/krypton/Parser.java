package me.aurora.client.krypton;

import me.aurora.client.krypton.execs.IExec;
import me.aurora.client.krypton.execs.Print;
import me.aurora.client.krypton.execs.SwapHotbar;
import me.aurora.client.krypton.execs.Wait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final Map<String, IExec> twoLineExecs = new HashMap<String, IExec>() {{
        put("PRINT:", new Print());
        put("WAIT:", new Wait());
        put("SWAPHOTBAR:", new SwapHotbar());
    }};

    public static void execute(ArrayList<String> script) {
        for (int i = 0; i < script.size(); i++) {
            String currLine = script.get(i);
            int temp = exMov(new ArrayList<String>(script.subList(i, script.size())));
            i += temp;
        }
    }

    public static Integer exMov(ArrayList<String> subArray) {
        int toMove = 0;
        if (twoLineExecs.containsKey(subArray.get(0))) {
            toMove += twoLineExecs.get(subArray.get(0)).exec(subArray.get(1));
        }
        return toMove;
    }
}
