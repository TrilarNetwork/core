package me.shizleshizle.core.utils;

import org.bukkit.Material;

public class StringToMaterial {

    public static Material getMaterialFromString(String mat) {
        return Material.getMaterial(mat.toUpperCase());
    }

    public static boolean isMaterial(String mat) {
        return Material.getMaterial(mat.toUpperCase()) != null;
    }
}
