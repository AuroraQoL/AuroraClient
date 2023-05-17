package me.aurora.client.utils.capes;

import lombok.Getter;
import me.aurora.client.utils.RemoteUtils;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static me.aurora.client.Aurora.mc;

public class CapeDatabase {
    @Getter
    private static final CapeDatabase instance = new CapeDatabase();
    private final Map<String, ResourceLocation> cachedCapeTextures = new HashMap<>();

    public boolean userHasCape(String username) {
        return cachedCapeTextures.containsKey(username.toLowerCase());
    }

    public ResourceLocation getUserCape(String username) {
        return cachedCapeTextures.get(username.toLowerCase());
    }

    public void init() {
        RemoteUtils.getCapeUsers().forEach(
                (username, capeUrl) -> {
                    try {
                        cachedCapeTextures.put(
                                username.toLowerCase(),
                                mc.getTextureManager().getDynamicTextureLocation("auroraskull" + username, new DynamicTexture(ImageIO.read(new URL(capeUrl))))
                        );
                    } catch (IOException ignored) {}
                }
        );
    }
}
