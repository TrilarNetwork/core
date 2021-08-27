package me.shizleshizle.core.commands.cmdutils;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.listeners.PlayerQuit;
import me.shizleshizle.core.objects.User;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.AttributeInstance;

import static org.bukkit.ChatColor.*;

public class WhoIsUtils {

    public static String getOfflineID(OfflinePlayer op) {
        String s;
        s = GOLD + op.getUniqueId().toString();
        return s;
    }

    public static String getOfflineHealth(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.health.containsKey(op.getName())) {
            double h = PlayerQuit.health.get(op.getName());
            int health = (int) h;
            s = GOLD.toString() + health;
        }
        return s;
    }

    public static String getOfflineHunger(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.hunger.containsKey(op.getName())) {
            int h = PlayerQuit.hunger.get(op.getName());
            s = GOLD.toString() + h;
        }
        return s;
    }

    public static String getOfflineXP(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.xp.containsKey(op.getName()) && PlayerQuit.xplevel.containsKey(op.getName())) {
            int xp = PlayerQuit.xp.get(op.getName());
            int level = PlayerQuit.xplevel.get(op.getName());
            s = GOLD.toString() + xp + YELLOW + " (" + GOLD + "Level " + level + YELLOW + ")";
        }
        return s;
    }

    public static String getOfflineGameMode(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.gm.containsKey(op.getName())) {
            GameMode gm = PlayerQuit.gm.get(op.getName());
            if (gm.equals(GameMode.SURVIVAL)) {
                s = GOLD + "Survival";
            } else if (gm.equals(GameMode.SPECTATOR)) {
                s = GOLD + "Spectator";
            } else if (gm.equals(GameMode.CREATIVE)) {
                s = GOLD + "Creative";
            } else if (gm.equals(GameMode.ADVENTURE)) {
                s = GOLD + "Adventure";
            }
        }
        return s;
    }

    public static String getOfflineGod(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.god.containsKey(op.getName())) {
            boolean b = PlayerQuit.god.get(op.getName());
            if (b) {
                s = GREEN + "true";
            } else {
                s = RED + "false";
            }
        }
        return s;
    }

    public static String getOfflineFrozen(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.frozen.containsKey(op.getName())) {
            boolean b = PlayerQuit.frozen.get(op.getName());
            if (b) {
                s = GREEN + "true";
            } else {
                s = RED + "false";
            }
        }
        return s;
    }

    public static String getOfflineTpToggle(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.t.containsKey(op.getName())) {
            boolean b = PlayerQuit.t.get(op.getName());
            if (b) {
                s = GREEN + "true";
            } else {
                s = RED + "false";
            }
        }
        return s;
    }

    public static String getOfflineVanished(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.vanished.containsKey(op.getName())) {
            boolean b = PlayerQuit.vanished.get(op.getName());
            if (b) {
                s = GREEN + "true";
            } else {
                s = RED + "false";
            }
        }
        return s;
    }

    public static String getOfflineMoney(OfflinePlayer op) {
        String s;
        double b = Main.econ.getBalance(op);
        String val = Main.econ.currencyNamePlural();
        s = GOLD.toString() + b + YELLOW + " " + val;
        return s;
    }

    public static String getAfk(User p) {
        return (p.isAfk() ? GREEN + "true" : RED + "false");
    }

    public static String getMuted(User p) {
        return (p.isMuted() ? GREEN + "true" : RED + "false");
    }

    public static String getID(User p) {
        return GOLD + p.getUUID().toString();
    }

    public static String getXP(User p) {
        return GOLD.toString() + p.getTotalExperience() + YELLOW + " (Level " + GOLD + p.getLevel() + YELLOW + ")";
    }

    public static String getFood(User p) {
        return GOLD.toString() + p.getFoodLevel() + YELLOW + "/" + GOLD + "20";
    }

    public static String getAddress(String name) {
        String s;
        if (Main.sql.getIP(name) != null) {
            s = GOLD + Main.sql.getIP(name);
        } else {
            s = GOLD + "No IP is known of this person.";
        }
        return s;
    }

    public static String getGameMode(User p) {
        String s = null;
        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
            s = GOLD + "Survival";
        } else if (p.getGameMode().equals(GameMode.SPECTATOR)) {
            s = GOLD + "Spectator";
        } else if (p.getGameMode().equals(GameMode.CREATIVE)) {
            s = GOLD + "Creative";
        } else if (p.getGameMode().equals(GameMode.ADVENTURE)) {
            s = GOLD + "Adventure";
        }
        return s;
    }

    public static String getLoc(User p) {
        String s;
        String n = p.getWorld().getName();
        int x = p.getLocation().getBlockX();
        int y = p.getLocation().getBlockY();
        int z = p.getLocation().getBlockZ();
        s = GOLD + n + YELLOW + ", X:" + GOLD + x + YELLOW + ", Y:" + GOLD + y + YELLOW + ", Z:" + GOLD + z;
        return s;
    }

    public static String getHealth(User p) {
        String s;
        int health = (int) p.getHealth();
        AttributeInstance mh = p.getMaxHealth();
        s = GOLD.toString() + health + YELLOW + "/" + GOLD + mh.getValue();
        return s;
    }

    public static String getOp(User p) {
        String s;
        if (p.isOp()) {
            s = GREEN + "true";
        } else {
            s = RED + "false";
        }
        return s;
    }

    public static String getGod(User p) {
        String s;
        if (p.isGod()) {
            s = GREEN + "true";
        } else {
            s = RED + "false";
        }
        return s;
    }

    public static String getFly(User p) {
        String s;
        if (p.getAllowFlight()) {
            if (p.isFlying()) {
                s = GREEN + "true " + YELLOW + "(" + GOLD + "flying" + YELLOW + ")";
            } else {
                s = GREEN + "true " + YELLOW + "(" + GOLD + "not flying" + YELLOW + ")";
            }
        } else {
            s = RED + "false";
        }
        return s;
    }

    public static String getFrozen(User p) {
        String s;
        if (p.isFrozen()) {
            s = GREEN + "true";
        } else {
            s = RED + "false";
        }
        return s;
    }

    public static String getSpeed(User p) {
        String s;
        if (p.isFlying()) {
            s = GOLD.toString() + p.getFlySpeed() + YELLOW + " (" + GOLD + "flying speed" + YELLOW + ")";
        } else {
            s = GOLD.toString() + p.getWalkSpeed() + YELLOW + " (" + GOLD + "walking speed" + YELLOW + ")";
        }
        return s;
    }

    public static String getTpToggle(User p) {
        String s;
        if (p.hasTpDisabled()) {
            s = GREEN + "true";
        } else {
            s = RED + "false";
        }
        return s;
    }

    public static String getVanished(User p) {
        String s;
        if (p.isVanished()) {
            s = GREEN + "true";
        } else {
            s = RED + "false";
        }
        return s;
    }

    public static String getMoney(User p) {
        OfflinePlayer op = p.getUser();
        String s;
        double b = Main.econ.getBalance(op);
        String val = Main.econ.currencyNamePlural();
        s = GOLD.toString() + b + YELLOW + " " + val;
        return s;
    }
}
