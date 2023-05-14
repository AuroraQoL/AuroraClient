package me.aurora.client.utils;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Gabagooooooooooool
 * @version 1.2
 * Basic utilty for checking if current version is the newest one.
 */

public class RemoteUtils {
    @SneakyThrows
    public static boolean isOutdated(int num) {
        return new Scanner(new URL("https://raw.githubusercontent.com/Gabagooooooooooool/sup/main/ver.txt").openConnection().getInputStream()).nextInt() > num;
    }

    @SneakyThrows
    public static Set<String> getBlacklistedModules() {
        return new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/Gabagooooooooooool/AuroraData/main/modules/RemotelyDisabled.aurf").openConnection().getInputStream())).lines().filter(b -> !b.startsWith(";")).collect(Collectors.toSet());
    }

    @SneakyThrows
    public static Map<String, Integer> getCapeUsers() {
        return new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/AuroraQoL/AuroraCapesMD5/main/Capes.aurf").openConnection().getInputStream())).lines().filter(b -> !b.startsWith(";")).map(s -> s.split("\\|")).filter(e -> e.length > 2).collect(Collectors.toMap(k -> k[1], v-> Integer.parseInt(v[2])));
    }
}
