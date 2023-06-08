package me.aurora.client.features.troll;

import me.aurora.client.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Hilary {

    private int ticks;
    public boolean hasSentInLast15Minutes = false;
    public static String[] messages = {
            "Just checked your skills, ain't looking too good..",
            "Send me furry porn GumTune#9663",
            "Hop in Hypixel discord vc, we need to talk.",
            "You know i can see chat messages, right?",
            "If i sold ALL my armor sets, ALL my pet collection " +
                    "(which is worth alot even with candied pets)" +
                    "I would EASILY be able to afford a tier boosted " +
                    "ender dragon and a giant's sword, which would " +
                    "put me farther along in progression than most players.",
            "Having fun? : )",
            "Any last words?",
            "Suggestion | MoniseurHase#1418 Add auto terminals. Unbannable version: Click anywhere on screen to click on the right spot. NEU does the same with Storage overlay so why not.",
            "Beamed by defrost (your uuid is being uploaded as we speak)",
            "How many creative minds do you have again? Its ridiculous that you think your opinion holds any ground against mine with an idea that the admins took and used all the time. I am an endgame player - if i sold ALL my skyblock cakes, ALL my pet collection (which is worth a lot even with candied pets) I can easily afford a tier boosted ender dragon and a giant's sword, which effectively makes me a lot farther along than most other end game players.",
            "You really thought that wasnt detectable?",
            "https://imgur.com/B6xBaEX This could be us but you playin",
            "Aurora Client is ratted. We have everyone's UUID"
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
            "§r§6[MVP§r§0++§r§6] kDarko",
            "§r§c[ADMIN] Dctr",
            "§r§b[MV§r§5+§r§b] egom"
    };

    public static List<String> messagesList = Arrays.asList(messages);
    public static List<String> sendersList = Arrays.asList(senders);

    @SubscribeEvent
    public void sendHilaryChat(TickEvent.PlayerTickEvent event) {

        if(++ticks < 90000) return;
        ticks = 0;

        Random randomSender = new Random();
        int randomSenderNumber = randomSender.nextInt(senders.length);

        Random randomMessage = new Random();
        int randomMessageNumber = randomMessage.nextInt(messages.length);

        if (event.player != null && Config.hilary) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§dFrom " + sendersList.get(randomSenderNumber) + "§7: " + "§7" + messagesList.get(randomMessageNumber)));
        }
    }
}
