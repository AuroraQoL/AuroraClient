package me.aurora.client.krypton;

import me.aurora.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class Main {

    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent e) {
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null)
            return;
        try {
            if (Keyboard.isCreated()) {
                if (Keyboard.getEventKeyState()) {
                    int keyCode = Keyboard.getEventKey();
                    if (keyCode <= 0)
                        return;
                    if (Config.script.length() <= 1)
                        return;
                    if (keyCode == KeyBindHandler.getKeyCode(splitLines(Config.script))) {
                        CompletableFuture.runAsync(() -> {
                            Parser.execute(splitLines(Config.script));
                        });
                    }
                }
            }
        } catch (Exception q) {
            q.printStackTrace();
        }
    }

    public ArrayList<String> splitLines(String input) {
        String[] lineArray = input.split("\n");
        return new ArrayList<>(Arrays.asList(lineArray));
    }
}
