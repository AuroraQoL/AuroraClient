package me.aurora.client.features.garden;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.InventoryUtils;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Items;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 1.1
 * @brief Automatic Composter
 * @todo Rewrite States Using Enums
 */
public class AutoComposter  implements Module {
    public String name() {
        return "AutoComposter";
    }

    public boolean toggled() {
        return Config.autoComposter;
    }
    private int delay = 0;
    private boolean inComposter = false;
    private boolean prevComposter = false;
    private String prevName = "";
    private boolean fuelAdded = false;
    private boolean cropsAdded = false;
    private boolean compostCollected = false;

    @SubscribeEvent
    public void onGui(GuiScreenEvent.InitGuiEvent.Post event) {
            fuelAdded = false;
            cropsAdded = false;
            compostCollected = false;
    }

    @SubscribeEvent
    public void onBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        String chestName = InventoryUtils.getGuiName(event.gui);
        if (chestName.contains("Confirm") && prevName.contains("Composter")) prevComposter = true;
        prevName = chestName;
        inComposter = chestName.contains("Composter");
        if (!chestName.contains("Confirm") && !chestName.contains("Composter")){
            prevComposter = false;
            fuelAdded = false;
            cropsAdded = false;
            compostCollected = false;
        }
    }


    @SubscribeEvent
    public void onTick(TickEvent event) {
        if (delay % Config.composter_delay == 0 && Config.autoComposter) {
            if (mc.currentScreen instanceof GuiChest && inComposter) {
                boolean actionDone = false;
                if(!cropsAdded){
                    switch(Config.composter_crop){
                        case 0:
                            mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 48, 1, 0, mc.thePlayer);
                            actionDone = true;
                            cropsAdded = true;
                            break;
                        case 2:
                            cropsAdded = true;
                            break;
                        case 1:
                            mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 48, 0, 0, mc.thePlayer);
                            actionDone = true;
                            cropsAdded = true;
                            break;
                    }
                }
                if(!fuelAdded && !actionDone){
                    if (Config.composter_fuel){
                        mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 50, 0, 0, mc.thePlayer);
                        actionDone = true;
                    }
                    fuelAdded = true;
                }
                if(!compostCollected && !actionDone){
                    if (mc.thePlayer.openContainer.inventorySlots.get(22).getStack().getItem() == Items.skull){
                        if(Config.composter_compost) {
                            mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 22, 0, 0, mc.thePlayer);
                        }
                        compostCollected = true;
                    }
                }
            } else if (mc.currentScreen instanceof GuiChest && prevComposter) {
                mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, 11, 0, 0, mc.thePlayer);
            }
        }
        delay++;
        if (delay>10000) delay=0;
    }

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        inComposter = false;
        fuelAdded = false;
        cropsAdded = false;
        compostCollected = false;
    }
}