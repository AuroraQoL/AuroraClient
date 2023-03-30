package me.aurora.client.mixin;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class MixinLoader implements IFMLLoadingPlugin {
    public MixinLoader() {
        System.out.println("Mixins Loaded");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.aurora.json");
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
    }

    public String getAccessTransformerClass() {
        return null;
    }
}