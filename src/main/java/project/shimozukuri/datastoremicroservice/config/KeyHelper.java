package project.shimozukuri.datastoremicroservice.config;

import java.util.Objects;

public class KeyHelper {

    final private static String defaultPrefix = "app";
    private static String prefix = null;

    public static void setPrefix(String keyPrefix) {
        prefix = keyPrefix;
    }

    public static String getKey(String key) {
        return getPrefix() + ":" + key;
    }

    private static String getPrefix() {
        return Objects.requireNonNullElse(prefix, defaultPrefix);
    }
}
