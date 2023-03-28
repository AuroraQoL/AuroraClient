package me.aurora.client.features.movement;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static me.aurora.client.Aurora.mc;

public class InvWalk implements Module {

    public String name() {
        return "InvWalk";
    }

    public boolean toggled() {
        return Config.invWalk;
    }

    private final Set<KeyBinding> bindings = new HashSet<>(Arrays.asList(
            mc.gameSettings.keyBindForward,
            mc.gameSettings.keyBindBack,
            mc.gameSettings.keyBindLeft,
            mc.gameSettings.keyBindRight,
            mc.gameSettings.keyBindSprint
    ));

    private final Set<KeyBinding> bindingDisabler = new HashSet<>(Arrays.asList(
            mc.gameSettings.keyBindJump,
            mc.gameSettings.keyBindSneak
    ));

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (toggled() && event.entityLiving instanceof EntityPlayer) {
            if (!(mc.currentScreen instanceof GuiChat) && mc.currentScreen != null) {
                bindings.stream().filter(GameSettings::isKeyDown).forEach(bind -> KeyBinding.setKeyBindState(bind.getKeyCode(), true));
            //    if (Config.disabler) {
            //        bindingDisabler.stream().filter(GameSettings::isKeyDown).forEach(bind -> KeyBinding.setKeyBindState(bind.getKeyCode(), true));
            //    } else {
                Stream.concat(bindings.stream(), bindingDisabler.stream()).filter(GameSettings::isKeyDown).forEach(bind -> KeyBinding.setKeyBindState(bind.getKeyCode(), false));
                // }
            }
        }
    }
}