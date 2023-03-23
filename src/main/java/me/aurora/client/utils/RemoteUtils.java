package me.aurora.client.utils;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
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
}
