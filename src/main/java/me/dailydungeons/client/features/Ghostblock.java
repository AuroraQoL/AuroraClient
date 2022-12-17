package me.dailydungeons.client.features;

import me.dailydungeons.client.config.Config;
import me.dailydungeons.client.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;


public class Ghostblock {


    Minecraft mc = Minecraft.getMinecraft();



  /*  @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (mc.gameSettings.isKeyDown(key))


    } */

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if(Keyboard.isKeyDown(Keyboard.KEY_G) && Config.ghostblock) {
            MovingObjectPosition POSITIONOFBLOCK = mc.thePlayer.rayTrace(mc.playerController.getBlockReachDistance(), 1);
                mc.theWorld.setBlockToAir(POSITIONOFBLOCK.getBlockPos());
        }
    }

}

