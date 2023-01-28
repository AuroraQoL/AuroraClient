package me.aurora.client.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Gabagooooooooooool
 * @version 1.1
 * Basic utilty for checking if current version is the newest one.
 */

public class VersionUtil {
    public static boolean isOutdated(int num) throws IOException {
        return (new Scanner(new URL("https://raw.githubusercontent.com/Gabagooooooooooool/sup/main/ver.txt").openConnection().getInputStream()).nextInt()) > num;
    }
}
