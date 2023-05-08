package me.aurora.client.features.misc;

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

}
