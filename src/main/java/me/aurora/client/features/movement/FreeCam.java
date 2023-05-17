package me.aurora.client.features.movement;


import com.mojang.authlib.GameProfile;
import lombok.SneakyThrows;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.BindUtils;
import me.aurora.client.utils.ItemUtils;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.ReflectionUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.apache.commons.lang3.SerializationUtils;

import java.util.UUID;

import static me.aurora.client.Aurora.mc;


/**
 * @author Gabagooooooooooool
 * @version 1.0
 * @brief Freecam
 */
public class FreeCam {
    
}
