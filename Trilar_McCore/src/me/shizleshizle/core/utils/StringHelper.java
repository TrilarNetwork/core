package me.shizleshizle.core.utils;

public class StringHelper {

    public static String normalCase(String toNormalize) {
        String firstLetter = toNormalize.substring(0, 1);
        String rest = toNormalize.substring(1);
        return firstLetter.toUpperCase() + rest.toLowerCase();
    }

    public static String[] splitBySpace(String toSplit) {
        return toSplit.split("\\s+");
    }
}
