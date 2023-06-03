package me.aurora.client.features.dungeons;

import me.aurora.client.config.Config;
import me.aurora.client.events.TickEndEvent;
import me.aurora.client.features.Module;
import me.aurora.client.utils.InventoryUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static me.aurora.client.Aurora.mc;

/**
 * @author jxee Gabagooooooooooool
 * @version 2.0
 * @credit ShadyAddons (jxee)
 * @brief Auto Sell
 */
public class AutoSell implements Module {

    private final Set<String> dungeonShit = new HashSet<>(Arrays.asList(
            "Training Weight",
            "Health Potion VIII Splash Potion",
            "Healing Potion 8 Slash Potion",
            "Beating Heart",
            "Premium Flesh",
            "Mimic Fragment",
            "Enchanted Rotten Flesh",
            "Enchanted Bone",
            "Defuse Kit",
            "Enchanted Ice",
            "Optic Lens",
            "Tripwire Hook",
            "Button",
            "Carpet",
            "Lever",
            "Rune",
            "Journal Entry",
            "Sign"));
    private boolean inTradeGui = false;
    private int tickCount = 0;

    public String name() {
        return "AutoSell";
    }

    public boolean toggled() {
        return Config.autoSell;
    }

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        tickCount++;
        if (tickCount % 4 == 0 && inTradeGui && toggled() && ConditionUtils.inSkyblock() && mc.currentScreen instanceof GuiChest) {
            ItemStack checkedStack = ((GuiChest) mc.currentScreen).inventorySlots.inventorySlots.get(49).getStack();
            if (checkedStack != null && checkedStack.getItem() != Item.getItemFromBlock(Blocks.barrier)) {
                mc.thePlayer.inventoryContainer.inventorySlots.stream().filter(this::properItem).findFirst().ifPresent(slot -> {
                    InventoryUtils.clickSlot(45 + slot.slotNumber, 2, 3);
                    MessageUtils.sendClientMessage("Selling trash...");
                });
            }
        }
    }

    @SubscribeEvent
    public void onRenderGuiBackground(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (ConditionUtils.inSkyblock() && toggled() && event.gui instanceof GuiChest)
            inTradeGui = ((GuiChest) event.gui).inventorySlots.getSlot(0).inventory.getName().contains("Trades");
    }

    public boolean properItem(Slot checkedSlot) {
        return dungeonShit.contains(checkedSlot.getStack().getDisplayName());
    }
}

