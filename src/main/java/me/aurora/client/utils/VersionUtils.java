package me.aurora.client.utils;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Gabagooooooooooool
 * @version 1.2
 * Basic utilty for checking if current version is the newest one.
 */

public class VersionUtils {
    @SneakyThrows
    public static boolean isOutdated(int num) {
        return new Scanner(new URL("https://raw.githubusercontent.com/Gabagooooooooooool/sup/main/ver.txt").openConnection().getInputStream()).nextInt() > num;
    }
}
