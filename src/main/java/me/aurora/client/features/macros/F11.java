package me.aurora.client.features.macros;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.MessageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;

import static me.aurora.client.Aurora.mc;

public class F11 implements Module {

    public String name() {
        return "Macro";
    }

    public boolean toggled() {
        return Config.f11Macro;
    }


    public static KeyBinding[] bindArray = {
        mc.gameSettings.keyBindAttack,
        mc.gameSettings.keyBindBack,
        mc.gameSettings.keyBindLeft,
        mc.gameSettings.keyBindRight,
        mc.gameSettings.keyBindUseItem,
        mc.gameSettings.keyBindForward
    };


    public static void stopAllMovement() {
        for(KeyBinding bind : bindArray) {
            KeyBinding.setKeyBindState(bind.getKeyCode(), false);
        }
    }

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (BindUtils.isBindPressed("F11Macro") && toggled()) {
            MessageUtils.sendClientMessage("Toggled F11 Macro, press the keys you set to come back.");
            Minecraft.getMinecraft().gameSettings.pauseOnLostFocus = false;
            if (event.entityLiving instanceof EntityPlayer) {
                if (mc.currentScreen == null) {
                    if (Config.f11Forward) {
                        KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
                    }
                    if (Config.f11Back) {
                        KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), true);
                    }
                    if (Config.f11Left) {
                        KeyBinding.setKeyBindState(mc.gameSettings.keyBindLeft.getKeyCode(), true);
                    }
                    if (Config.f11Right) {
                        KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), true);
                    }
                    if (Config.f11LeftClick) {
                        KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), true);
                    }
                    if (Config.f11RightClick) {
                        KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                    }
                }
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindFullscreen.getKeyCode(), true);
            }
        } else {
            Minecraft.getMinecraft().gameSettings.pauseOnLostFocus = true;
        }
    }

}
