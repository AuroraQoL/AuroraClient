package me.aurora.client.krypton.execs;

import me.aurora.client.krypton.management.MathVariableParser;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

import static me.aurora.client.Aurora.mc;

//implemented from https://github.com/ShadyAddons/ShadyAddons/blob/main/src/main/java/cheaters/get/banned/features/routines/actions/SwapHotbarAction.java
public class SwapHotbar implements IExec {
    @Override
    public Integer exec(String parameter) {
        mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange((int) MathVariableParser.parseMath(parameter)));
        mc.thePlayer.inventory.currentItem = (int) MathVariableParser.parseMath(parameter);
        return 1;
    }
}
