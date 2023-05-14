package me.aurora.client.utils.capes;

import lombok.Getter;
import lombok.SneakyThrows;
import me.aurora.client.utils.RemoteUtils;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class CapeDatabase {
    @Getter
    private final static CapeDatabase instance = new CapeDatabase();
    private Map<String, Integer> cachedCapes = new HashMap<>();
    private final Map<String, Integer> cachedUserNameMapping = new HashMap<>();

    public boolean userHasCape(String username) {
        final String tUsername = username.toLowerCase();
        for (Map.Entry<String, Integer> userEntry : cachedCapes.entrySet())
            if (userEntry.getKey().equals(getMD5(tUsername))) {
                cachedUserNameMapping.put(tUsername, userEntry.getValue());
                return true;
            }
        return false;
    }

    public int getUserCape(String username) {
        final String tUsername = username.toLowerCase();
        for (Map.Entry<String, Integer> userEntry : cachedUserNameMapping.entrySet())
            if (userEntry.getKey().equals(tUsername))
                return userEntry.getValue();
        return 0;
    }

    public boolean cachedUserHasCape(String username) {
        final String tUsername = username.toLowerCase();
        return cachedUserNameMapping.containsKey(tUsername);
    }

    public void init() {
        cachedCapes = RemoteUtils.getCapeUsers();
    }

    @SneakyThrows
    private String getMD5(String input) {
        byte[] messageDigest = MessageDigest.getInstance("MD5").digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
