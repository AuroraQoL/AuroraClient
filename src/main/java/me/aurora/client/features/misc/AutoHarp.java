package me.aurora.client.features.misc;

import com.electronwill.nightconfig.core.AbstractCommentedConfig;
import com.electronwill.nightconfig.core.AbstractConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import me.aurora.client.config.Config;
import me.aurora.client.events.TickEndEvent;
import me.aurora.client.features.Module;
import me.aurora.client.utils.InventoryUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.Timer;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

import static me.aurora.client.Aurora.mc;

public class AutoHarp implements Module {
    public String name() {
        return "AutoHarp";
    }

    public boolean toggled() {
        return Config.autoHarp;
    }
    private boolean harp = false;
    private int delay = 0; //fuck you hypixel
    Timer timer = new Timer();

    @SubscribeEvent
    public void onBackgroundRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        String chestName = InventoryUtils.getGuiName(event.gui);
        harp = chestName.contains("Harp -");
    }

    //TODO: fix getting limboed sometimes (hard to fix while still being really good since hypixel is shit)
    @SubscribeEvent
    public void LivingUpdateEvent(LivingEvent.LivingUpdateEvent e) {
        if (toggled() && harp) {
            for (Slot slot : mc.thePlayer.openContainer.inventorySlots) {
                if (slot.getStack() != null && slot.getStack().getItem() instanceof ItemBlock && ((ItemBlock) slot.getStack().getItem()).getBlock() == Blocks.quartz_block) {
                    mc.playerController.windowClick(mc.thePlayer.openContainer.windowId, slot.slotNumber, 0, 0, mc.thePlayer);
                }
            }
        }
    }
}
