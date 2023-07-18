package me.aurora.client.features.dungeons;

import lombok.*;
import me.aurora.client.config.Config;
import me.aurora.client.features.Module;
import me.aurora.client.utils.MessageUtils;
import me.aurora.client.utils.conditions.ConditionUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.aurora.client.Aurora.mc;

/**
 * @author Gabagooooooooooool
 * @version 2.0
 */
public class NoDowntime implements Module {

    private static final Queue<String> messagesQueue = new LinkedList<>();

    public String name() {
        return "NoDowntime";
    }

    public boolean toggled() {
        return Config.noDowntime;
    }

    @SubscribeEvent
    @SneakyThrows
    public void onChat(ClientChatReceivedEvent event) {
        if (Config.noDowntime && ConditionUtils.inSkyblock()) {
            String message = event.message.getFormattedText().replaceAll("§.", "");
            messagesQueue.offer(message);
            if (messagesQueue.size() > 3) messagesQueue.poll();
            if (message.matches(".*Defeated*.(Bonzo|Scarf|Professor|Thorn|Livid|Sadan|Necron)*.in.*") && !messagesQueue.isEmpty()) {
                CatacombType type = messagesQueue.peek().contains("Master") ? CatacombType.MASTER_CATACOMBS : CatacombType.CATACOMBS;
                type.setFloor(getFloor(message));
                MessageUtils.sendClientMessage("Waiting...");
                if (type.getFloor() != 0) {
                    new ScheduledThreadPoolExecutor(1).schedule(() -> {
                        int floor = type.getFloor();
                        mc.thePlayer.sendChatMessage(String.format("/joindungon %s %s", type.getNaming(), floor));
                        MessageUtils.sendClientMessage(String.format("Joining %sCatacombs Floor %s", type == CatacombType.CATACOMBS ? "" : "Master Mode ", floor));
                    }, Config.noDowntime_ParameterDelay, TimeUnit.SECONDS);
                }
            }
        }
    }

    private int getFloor(String message) {
        if (message.contains("Bonzo")) {
            return 1;
        } else if (message.contains("Scarf")) {
            return 2;
        } else if (message.contains("Professor")) {
            return 3;
        } else if (message.contains("Thorn")) {
            return 4;
        } else if (message.contains("Livid")) {
            return 5;
        } else if (message.contains("Sadan")) {
            return 6;
        } else if (message.contains("Necron")) {
            return 7;
        }
        return 0;
    }

    @RequiredArgsConstructor
    protected enum CatacombType {
        CATACOMBS("catacombs"), MASTER_CATACOMBS("master_catacombs");
        @NonNull
        @Getter
        private final String naming;
        @Getter
        @Setter
        private int floor;
    }
}