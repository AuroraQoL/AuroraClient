package me.dailydungeons.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class VersionUtil {

    public static boolean isOutdated(int num) throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/Gabagooooooooooool/bruh/main/current.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        URLConnection connection = url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        int value = scanner.nextInt();
        System.out.println(value);
        System.out.println(value > num);

        return value > num;
    }

    public static boolean isForced(int num) throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/Gabagooooooooooool/bruh/main/forced.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        URLConnection connection = url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        int value = scanner.nextInt();
        System.out.println(value);
        System.out.println(value > num);

        return value > num;
    }
}
