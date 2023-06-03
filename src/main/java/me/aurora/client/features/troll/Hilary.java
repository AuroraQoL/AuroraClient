package me.aurora.client.features.troll;

import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Hilary {
    public static String[] messages = {
            "Just checked your skills, ain't looking too good..",
            "Send me furry porn GumTune#9663",
            "How many creative minds do you have again?" +
                    "It's ridiculous to think your opinion holds " +
                    "any ground against mine of which the admins " +
                    "have tested and put in the game constantly",
            "Hop in Hypixel discord vc, we need to talk.",
            "You know i can see chat messages, right?",
            "If i sold ALL my armor sets, ALL my pet collection " +
                    "(which is worth alot even with candied pets)" +
                    "I would EASILY be able to afford a tier boosted " +
                    "ender dragon and a giant's sword, which would " +
                    "put me farther along in progression than most players.",
            "Having fun? : )",
            "Any last words?"
    };

    public static String[] senders = {
            "§r§c[§r§fYOUTUBE§r§c] §r§cRefraction",
            "§r§d[PIG§r§b+++§r§d] Technoblade",
            "§r§c[ADMIN] Plancke",
            "§r§c[ADMIN] Minikloon",
            "§r§c[ADMIN] Nitroholic",
            "§r§c[ADMIN] Jayavarmen",
            "§r§d§lsincerity",
            "§r§c[§r§fYOUTUBE§r§c] §r§cThirtyVirus",
            "§r§s§ldefrost",
            "§r§c[§r§fYOUTUBE§r§c] §r§cPalikka",
            "§r§c[OWNER] hypixel",
            "§r§c[OWNER] GumTune",
            "§r§6[MVP§r§0++§r§6] kDarko"
    };

    public static List<String> messagesList = Arrays.asList(messages);
    public static List<String> sendersList = Arrays.asList(senders);
    @SubscribeEvent
    public void sendHilaryChat(TickEvent.PlayerTickEvent event) {
        Random random = new Random();
        int decide = random.nextInt(100000);

        Random randomSender = new Random();
        int randomSenderNumber = randomSender.nextInt(senders.length);

        Random randomMessage = new Random();
        int randomMessageNumber = randomMessage.nextInt(messages.length);

        if (event.player != null) {
            if (decide == 1 && Config.hilary) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§dFrom " + sendersList.get(randomSenderNumber) + "§7: " + "§7" + messagesList.get(randomMessageNumber)));
            }
        }
    }
}
