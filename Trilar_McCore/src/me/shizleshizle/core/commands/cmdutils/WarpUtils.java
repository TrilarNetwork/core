package me.shizleshizle.core.commands.cmdutils;

import me.shizleshizle.core.ConfigManager;
import me.shizleshizle.core.Main;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class WarpUtils {
    public static ArrayList<String> warps = new ArrayList<>();
    private static final ConfigManager c = Main.c;

    public static void setWarp(String name, double x, double y, double z, double yaw, double pitch, String worldname) {
        c.getWarps().set("settings.warps." + name + ".x", x);
        c.getWarps().set("settings.warps." + name + ".y", y);
        c.getWarps().set("settings.warps." + name + ".z", z);
        c.getWarps().set("settings.warps." + name + ".yaw", yaw);
        c.getWarps().set("settings.warps." + name + ".pitch", pitch);
        c.getWarps().set("settings.warps." + name + ".world", worldname);
        c.saveWarps();
        warps.add(name);
    }

    public static void removeWarp(String name) {
        if (warps.contains(name)) {
            c.getWarps().set("settings.warps." + name + ".x", null);
            c.getWarps().set("settings.warps." + name + ".y", null);
            c.getWarps().set("settings.warps." + name + ".z", null);
            c.getWarps().set("settings.warps." + name + ".yaw", null);
            c.getWarps().set("settings.warps." + name + ".pitch", null);
            c.getWarps().set("settings.warps." + name + ".world", null);
            c.saveWarps();
            warps.remove(name);
        }
    }

    public static void saveWarps() {
        List<String> list = c.getWarps().getStringList("settings.warps");
        list.addAll(warps);
        c.getWarps().set("settings.warps", list);
        c.saveWarps();
    }

    public static void loadWarps() {
        List<String> list = c.getWarps().getStringList("settings.warps");
        for (String w : list) {
            if (!warps.contains(w)) {
                warps.add(w);
            }
        }
    }

    public static String listWarps() {
        String s = "";
        if (warps.isEmpty()) {
            s = Main.p + "There are no warps!";
            return s;
        } else {
            StringBuilder sb = new StringBuilder();
            for (String w : warps) {
                String uno = ChatColor.GOLD + w;
                String dos = ChatColor.YELLOW + ", ";
                sb.append(uno).append(dos);
            }
            if (sb.toString().isEmpty()) {
                return s;
            }
            String del = ", ";
            s = sb.toString();
            if (s.startsWith(del)) {
                return s;
            } else {
                return s.substring(0, s.length() - 2);
            }
        }
    }
}
