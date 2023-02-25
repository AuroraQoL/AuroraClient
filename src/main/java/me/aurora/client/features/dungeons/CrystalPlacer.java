package me.aurora.client.features.dungeons;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class CrystalPlacer implements Module {
    public String name() {
        return "CrystalPlacer";
    }

    public boolean toggled() {
        return Config.crystalPlacer;
    }
    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (Config.crystalPlacer && Keyboard.isKeyDown(Keyboard.KEY_G)) {
            BlockPos pos = new BlockPos(-2, 70, -85);
            Aurora.mc.theWorld.setBlockState(pos, Blocks.iron_block.getDefaultState());
        }
    }
}
