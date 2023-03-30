package me.aurora.client.mixin;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Minecraft.class})
public class MixinMinecraft {
    @Inject(method = {"startGame"}, at = {@At("RETURN")})
    private void startGame(CallbackInfo ci) {
        Display.setTitle("Minecraft 1.8.9 - Aurora 4.0 preview");
    }
}