package me.aurora.client.utils.string;

/**
 * @author Gabagooooooooooool
 * @version 1.0
 * @brief String Utilities
 */
public class StringUtils {
    public static String removeFormatting(String input) {
        return input.replaceAll("[§|&][0-9,a-f,k-o,r]", "");
    }
}
