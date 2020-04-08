package me.shizleshizle.core.commands.cmdutils;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.listeners.PlayerQuit;
import me.shizleshizle.core.objects.User;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.AttributeInstance;

public class WhoIsUtils {

    public static String getOfflineID(OfflinePlayer op) {
        String s;
        s = ChatColor.GOLD + op.getUniqueId().toString();
        return s;
    }

    public static String getOfflineHealth(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.health.containsKey(op.getName())) {
            double h = PlayerQuit.health.get(op.getName());
            int health = (int) h;
            s = ChatColor.GOLD.toString() + health;
        }
        return s;
    }

    public static String getOfflineHunger(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.hunger.containsKey(op.getName())) {
            int h = PlayerQuit.hunger.get(op.getName());
            s = ChatColor.GOLD.toString() + h;
        }
        return s;
    }

    public static String getOfflineXP(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.xp.containsKey(op.getName()) && PlayerQuit.xplevel.containsKey(op.getName())) {
            int xp = PlayerQuit.xp.get(op.getName());
            int level = PlayerQuit.xplevel.get(op.getName());
            s = ChatColor.GOLD.toString() + xp + ChatColor.YELLOW + " (" + ChatColor.GOLD + "Level " + level + ChatColor.YELLOW + ")";
        }
        return s;
    }

    public static String getOfflineGameMode(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.gm.containsKey(op.getName())) {
            GameMode gm = PlayerQuit.gm.get(op.getName());
            if (gm.equals(GameMode.SURVIVAL)) {
                s = ChatColor.GOLD + "Survival";
            } else if (gm.equals(GameMode.SPECTATOR)) {
                s = ChatColor.GOLD + "Spectator";
            } else if (gm.equals(GameMode.CREATIVE)) {
                s = ChatColor.GOLD + "Creative";
            } else if (gm.equals(GameMode.ADVENTURE)) {
                s = ChatColor.GOLD + "Adventure";
            }
        }
        return s;
    }

    public static String getOfflineGod(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.god.containsKey(op.getName())) {
            boolean b = PlayerQuit.god.get(op.getName());
            if (b) {
                s = ChatColor.GREEN + "true";
            } else {
                s = ChatColor.RED + "false";
            }
        }
        return s;
    }

    public static String getOfflineFrozen(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.frozen.containsKey(op.getName())) {
            boolean b = PlayerQuit.frozen.get(op.getName());
            if (b) {
                s = ChatColor.GREEN + "true";
            } else {
                s = ChatColor.RED + "false";
            }
        }
        return s;
    }

    public static String getOfflineTpToggle(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.t.containsKey(op.getName())) {
            boolean b = PlayerQuit.t.get(op.getName());
            if (b) {
                s = ChatColor.GREEN + "true";
            } else {
                s = ChatColor.RED + "false";
            }
        }
        return s;
    }

    public static String getOfflineVanished(OfflinePlayer op) {
        String s = null;
        if (PlayerQuit.vanished.containsKey(op.getName())) {
            boolean b = PlayerQuit.vanished.get(op.getName());
            if (b) {
                s = ChatColor.GREEN + "true";
            } else {
                s = ChatColor.RED + "false";
            }
        }
        return s;
    }

    public static String getOfflineMoney(OfflinePlayer op) {
        String s;
        double b = Main.econ.getBalance(op);
        String val = Main.econ.currencyNamePlural();
        s = ChatColor.GOLD.toString() + b + ChatColor.YELLOW + " " + val;
        return s;
    }

    public static String getID(User p) {
        return ChatColor.GOLD + p.getUUID().toString();
    }

    public static String getXP(User p) {
        return ChatColor.GOLD.toString() + p.getTotalExperience() + ChatColor.YELLOW + " (Level " + ChatColor.GOLD + p.getLevel() + ChatColor.YELLOW + ")";
    }

    public static String getFood(User p) {
        return ChatColor.GOLD.toString() + p.getFoodLevel() + ChatColor.YELLOW + "/" + ChatColor.GOLD + "20";
    }

    public static String getAddress(String name) {
        String s;
        if (Main.sql.getIP(name) != null) {
        	s = ChatColor.GOLD + Main.sql.getIP(name);
        } else {
        	s = ChatColor.GOLD + "No IP is known of this person.";
        }
        return s;
    }

    public static String getGameMode(User p) {
        String s = null;
        if (p.getGameMode().equals(GameMode.SURVIVAL)) {
            s = ChatColor.GOLD + "Survival";
        } else if (p.getGameMode().equals(GameMode.SPECTATOR)) {
            s = ChatColor.GOLD + "Spectator";
        } else if (p.getGameMode().equals(GameMode.CREATIVE)) {
            s = ChatColor.GOLD + "Creative";
        } else if (p.getGameMode().equals(GameMode.ADVENTURE)) {
            s = ChatColor.GOLD + "Adventure";
        }
        return s;
    }

    public static String getLoc(User p) {
        String s;
        String n = p.getWorld().getName();
        int x = p.getLocation().getBlockX();
        int y = p.getLocation().getBlockY();
        int z = p.getLocation().getBlockZ();
        s = ChatColor.GOLD + n + ChatColor.YELLOW + ", " + ChatColor.GOLD + x + ChatColor.YELLOW + ", " + ChatColor.GOLD + y + ChatColor.YELLOW + ", " + ChatColor.GOLD + z;
        return s;
    }

    public static String getHealth(User p) {
        String s;
        int health = (int) p.getHealth();
        AttributeInstance mh = p.getMaxHealth();
        s = ChatColor.GOLD.toString() + health + ChatColor.YELLOW + "/" + ChatColor.GOLD + mh.getValue();
        return s;
    }

    public static String getOp(User p) {
        String s;
        if (p.isOp()) {
            s = ChatColor.GREEN + "true";
        } else {
            s = ChatColor.RED + "false";
        }
        return s;
    }

    public static String getGod(User p) {
        String s;
        if (p.isGod()) {
            s = ChatColor.GREEN + "true";
        } else {
            s = ChatColor.RED + "false";
        }
        return s;
    }

    public static String getFly(User p) {
        String s;
        if (p.getAllowFlight()) {
            if (p.isFlying()) {
                s = ChatColor.GREEN + "true " + ChatColor.YELLOW + "(" + ChatColor.GOLD + "flying" + ChatColor.YELLOW + ")";
            } else {
                s = ChatColor.GREEN + "true " + ChatColor.YELLOW + "(" + ChatColor.GOLD + "not flying" + ChatColor.YELLOW + ")";
            }
        } else {
            s = ChatColor.RED + "false";
        }
        return s;
    }

    public static String getFrozen(User p) {
        String s;
        if (p.isFrozen()) {
            s = ChatColor.GREEN + "true";
        } else {
            s = ChatColor.RED + "false";
        }
        return s;
    }

    public static String getSpeed(User p) {
        String s;
        if (p.isFlying()) {
            s = ChatColor.GOLD.toString() + p.getFlySpeed() + ChatColor.YELLOW + " (" + ChatColor.GOLD + "flying speed" + ChatColor.YELLOW + ")";
        } else {
            s = ChatColor.GOLD.toString() + p.getWalkSpeed() + ChatColor.YELLOW + " (" + ChatColor.GOLD + "walking speed" + ChatColor.YELLOW + ")";
        }
        return s;
    }

    public static String getTpToggle(User p) {
        String s;
        if (p.hasTpDisabled()) {
            s = ChatColor.GREEN + "true";
        } else {
            s = ChatColor.RED + "false";
        }
        return s;
    }

    public static String getVanished(User p) {
        String s;
        if (p.isVanished()) {
            s = ChatColor.GREEN + "true";
        } else {
            s = ChatColor.RED + "false";
        }
        return s;
    }

    public static String getMoney(User p) {
        OfflinePlayer op = p.getUser();
        String s;
        double b = Main.econ.getBalance(op);
        String val = Main.econ.currencyNamePlural();
        s = ChatColor.GOLD.toString() + b + ChatColor.YELLOW + " " + val;
        return s;
    }
}
