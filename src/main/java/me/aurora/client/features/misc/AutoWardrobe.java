package me.aurora.client.features.misc;


import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.InventoryUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.Timer;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.item.ItemDye;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

public class AutoWardrobe implements Module {


    @Override
    public boolean toggled() {
        return Config.AutoWD;
    }

    @Override
    public String name() {
        return "Auto Wardrobe";
    }

    private boolean inWardrobe = false;
    private boolean readyToSwitch = false;
    private int ticks;

    Timer timer = new Timer();

    @SubscribeEvent
    public void onBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        String chestName = InventoryUtils.getGuiName(event.gui);
        inWardrobe = chestName.contains("Wardrobe");
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
            if (mc.currentScreen instanceof GuiChest && toggled()) {
                if (inWardrobe && readyToSwitch) {
                    if(timer.timeBetween(200, true)) {
                        InventoryUtils.clickSlot(35 + Config.wd_slot, 0, 0);
                    }
                    if(timer.timeBetween(200, true)) {
                        mc.thePlayer.closeScreen();
                    }
                }
            }
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {
        if (BindUtils.isBindPressed("AutoWardrobe")) {
            readyToSwitch = true;
            mc.thePlayer.sendChatMessage("/wd");
            MessageUtils.sendClientMessage("Opening Wardrobe..");
        }
    }

}
