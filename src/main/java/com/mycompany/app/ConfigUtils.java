package com.mycompany.app;

import org.bukkit.Sound;

public final class ConfigUtils {

    private ConfigUtils() {
    }

    // Soup
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

    // Kangaroo
    public static int kangarooFallDamage() {
        return 7;
    }

    public static double kangarooJump() {
        return 0.9;
    }

    public static double kangarooLounge() {
        return 1.3;
    }

}