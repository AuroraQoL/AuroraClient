package me.aurora.client.features.dungeons;

import me.aurora.client.Aurora;
import me.aurora.client.config.Config;
import me.aurora.client.utils.conditions.Conditions;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class CrystalPlacer {
    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (Config.crystalPlacer && Keyboard.isKeyDown(Keyboard.KEY_G)) {
            BlockPos pos = new BlockPos(-2, 70, -85);
            Aurora.mc.theWorld.setBlockState(pos, Blocks.iron_block.getDefaultState());
        }
    }
}
