package me.aurora.client.features.macros;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static me.aurora.client.Aurora.mc;

public class F11 implements Module {

    public String name() {
        return "Macro";
    }

    public boolean toggled() {
        return Config.f11Macro;
    }

    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        if (toggled()) {
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
            }
        }
    }
}
