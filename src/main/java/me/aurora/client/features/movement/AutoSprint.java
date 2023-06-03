package me.aurora.client.features.movement;

import lombok.AllArgsConstructor;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.aurora.client.Aurora.mc;

/**
 * @author OctoSplash01 Gabagooooooooooool
 * @version 1.1
 * @brief Auto Sprint
 */
public class AutoSprint implements Module {
    public String name() {
        return "AutoSprint";
    }

    public boolean toggled() {
        return Config.autoSprint;
    }

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (toggled() && event.entityLiving instanceof EntityPlayer && !(mc.currentScreen instanceof GuiChat) && !mc.thePlayer.isSneaking() && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isCollidedHorizontally) {
            switch (Config.autoSprintSettings) {
                case 0: // Legit
                    if (mc.thePlayer.onGround && Key.FORWARD.keyDown())
                        mc.thePlayer.setSprinting(true);
                    break;
                case 1: // Omnidirectional
                    if (Key.FORWARD.keyDown() || Key.LEFT.keyDown() || Key.RIGHT.keyDown() || Key.BACK.keyDown())
                        mc.thePlayer.setSprinting(true);
                    break;
            }
        }
    }

    @AllArgsConstructor
    private enum Key {
        FORWARD(mc.gameSettings.keyBindForward),
        LEFT(mc.gameSettings.keyBindLeft),
        RIGHT(mc.gameSettings.keyBindRight),
        BACK(mc.gameSettings.keyBindBack);
        private final KeyBinding keyBinding;

        boolean keyDown() {
            return GameSettings.isKeyDown(keyBinding);
        }
    }
}