package me.dailydungeons.client.cerberus;


import jdk.nashorn.api.scripting.URLReader;
import net.minecraft.util.Session;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.stream.Collectors;

//CURRENTLY DISABLED BECAUSE OPEN RELEASE

public class Cerberus {


    public static boolean isAllowedToRun() throws MalformedURLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        return true;
    }

    public static boolean networkConnection() throws MalformedURLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        return true;
    }

    public static String getHWID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return null;
    }
}

