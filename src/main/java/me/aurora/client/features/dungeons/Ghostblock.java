package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.Timer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Arrays;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool OctoSplash01
 * @version 2.0
 * @brief Ghostblocks + Range + Blacklisted Blocks + Legit Mode
 */
public class Ghostblock implements Module {
    private volatile static double lastUsed;
    private final ArrayList<Block> dontGhost = new ArrayList<>(Arrays.asList(
            Blocks.chest, //secret chests
            Blocks.lever, //secret levers
            Blocks.command_block, //terminals
            Blocks.skull, //wither essence
            Blocks.bed, //if someone plays bedwars with aurora for whatever reason XD
            Blocks.trapped_chest)); //mimic (mimic is a mob that is hidden in a trapped chest)
    Timer timer = new Timer();

    public String name() {
        return "Ghostblock";
    }

    public boolean toggled() {
        return Config.ghostblocks;
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (BindUtils.isBindDown("GhostBlocks") && toggled() && ((System.currentTimeMillis() - lastUsed) > (Config.ghostblocks_delay * 1000))) {
            MovingObjectPosition blockPos = mc.thePlayer.rayTrace(Config.ghostblocks_range, 1);
            Block currentBlock = mc.theWorld.getBlockState(blockPos.getBlockPos()).getBlock();
            switch (Config.ghostblocksMode) {
                case 0:
                    if (!dontGhost.contains(currentBlock)) {
                        lastUsed = System.currentTimeMillis();
                        mc.theWorld.setBlockToAir(blockPos.getBlockPos());
                    }
                    break;
                case 1:
                    if (!dontGhost.contains(currentBlock)) {
                        lastUsed = System.currentTimeMillis();
                        mc.theWorld.setBlockToAir(blockPos.getBlockPos());
                        if (timer.timeBetween(50, true)) {
                            mc.thePlayer.swingItem();
                        }
                    }
                    break;
            }
        }
    }
}
