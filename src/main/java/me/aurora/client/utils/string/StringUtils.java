package me.aurora.client.utils.string;

public class StringUtils {
    /**
     * Modified code from HyAddons.
     * */
    public static String removeFormatting(String input) {
        return input.replaceAll("[§|&][0-9,a-f,k-o,r]", "");
    }

}
