package com.mycompany.app;

import org.bukkit.Sound;

public final class ConfigUtils {

    private ConfigUtils() {
    }

    public static boolean isFastHealth() {
        return true;
    }

    public static double getFastHealthAmount() {
        return 7;
    }

    public static boolean isFastHunger() {
        return true;
    }

    public static int getFastHungerAmount() {
        return 6;
    }

    public static Sound getFastHungerSound() {
        return Sound.BURP;
    }
}