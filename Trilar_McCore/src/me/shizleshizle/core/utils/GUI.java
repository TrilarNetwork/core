package me.shizleshizle.core.utils;

import me.shizleshizle.core.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.HashMap;

import static org.bukkit.ChatColor.*;

public class GUI {

    public static void openPlayerList(User p) {
        int size = 0;
        if (p.getServer().getOnlinePlayers().size() <= 9) {
            size = 9;
        } else if (p.getServer().getOnlinePlayers().size() <= 18) {
            size = 18;
        } else if (p.getServer().getOnlinePlayers().size() <= 27) {
            size = 27;
        } else if (p.getServer().getOnlinePlayers().size() <= 36) {
            size = 36;
        } else if (p.getServer().getOnlinePlayers().size() <= 45) {
            size = 45;
        } else if (p.getServer().getOnlinePlayers().size() <= 54) {
            size = 54;
        }
        Inventory inv = Bukkit.createInventory(null, size, "Player List");
        for (Player ap : p.getServer().getOnlinePlayers()) {
            ItemStack i = CI.createItem(Material.PLAYER_HEAD, 1, -1, WHITE + ap.getName());
            SkullMeta meta = (SkullMeta) i.getItemMeta();
            assert meta != null;
            meta.setOwningPlayer(ap.getPlayer());
            i.setItemMeta(meta);
            inv.addItem(i);
        }
        p.openInventory(inv);
    }

    public static void openStaffGUI(User p) {
        Inventory inv = Bukkit.createInventory(null, 36, GOLD + "Staff GUI");
        HashMap<Integer, ItemStack> items = new HashMap<>();
        items.put(1, CI.createItem(Material.CRIMSON_DOOR, 1, -1, AQUA + "Kick"));
        items.put(4, CI.createItem(Material.RED_WOOL, 1, -1, DARK_RED + "Warn"));
        items.put(7, CI.createItem(Material.NETHERITE_AXE, 1, -1, DARK_BLUE + "Ban"));
        items.put(11, CI.createItem(Material.BLUE_ICE, 1, -1, DARK_AQUA + "Freeze"));
        items.put(15, CI.createItem(Material.COBWEB, 1, -1, DARK_GRAY + "Mute"));
        items.put(22, CI.createItem(Material.COMPASS, 1, -1, GOLD + "Teleport"));
        items.put(31, CI.createItem(Material.RED_BANNER, 1, -1, WHITE + "Exit"));
        for (int slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        p.openInventory(inv);
    }

    public static void openKickGUI(User p) {
        Inventory inv = Bukkit.createInventory(null, 27, AQUA + "Kick GUI");
        HashMap<Integer, ItemStack> items = new HashMap<>();
        items.put(4, CI.createItem(Material.CRIMSON_DOOR, 1, -1, AQUA + "Kick", YELLOW + "Choose a reason for kicking a player."));
        items.put(12, CI.createItem(Material.OAK_PLANKS, 1, -1, BLUE + "You have been kicked!"));
        items.put(14, CI.createItem(Material.DARK_OAK_PLANKS, 1, -1, DARK_PURPLE + "Other", LIGHT_PURPLE + "Type a reason yourself"));
        items.put(22, CI.createItem(Material.RED_BANNER, 1, -1, WHITE + "Back"));
        for (int slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        p.openInventory(inv);
    }

    public static void openWarnGUI(User p) {
        Inventory inv = Bukkit.createInventory(null, 36, AQUA + "Warn GUI");
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // TODO: set items
        for (int slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        p.openInventory(inv);
    }

    public static void openBanGUI(User p) {
        Inventory inv = Bukkit.createInventory(null, 36, AQUA + "Ban GUI");
        HashMap<Integer, ItemStack> items = new HashMap<>();
        items.put(4, CI.createItem(Material.NETHERITE_AXE, 1, -1, DARK_BLUE + "Ban"));
        items.put(11, CI.createItem(Material.OAK_PLANKS, 1, -1, DARK_PURPLE + "Swearing"));
        items.put(15, CI.createItem(Material.BIRCH_PLANKS, 1, -1, DARK_PURPLE + "Hacking"));
        items.put(21, CI.createItem(Material.SPRUCE_PLANKS, 1, -1, DARK_PURPLE + "Advertising"));
        items.put(23, CI.createItem(Material.JUNGLE_PLANKS, 1, -1, DARK_PURPLE + "Discriminating/Racism"));
        items.put(31, CI.createItem(Material.DARK_OAK_PLANKS, 1, -1, DARK_PURPLE + "Other", LIGHT_PURPLE + "Type a reason yourself"));
        items.put(27, CI.createItem(Material.RED_BANNER, 1, -1, WHITE + "Back"));
        for (int slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        p.openInventory(inv);
    }

    public static void openMuteGUI(User p) {
        Inventory inv = Bukkit.createInventory(null, 27, AQUA + "Mute GUI");
        HashMap<Integer, ItemStack> items = new HashMap<>();
        items.put(4, CI.createItem(Material.COBWEB, 1, -1, DARK_GRAY + "Mute"));
        items.put(12, CI.createItem(Material.BEDROCK, 1, -1, DARK_AQUA + "No time defined (permanent, until unmuted)"));
        items.put(14, CI.createItem(Material.GRASS_BLOCK, 1, -1, DARK_AQUA + "Set a time"));
        items.put(22, CI.createItem(Material.RED_BANNER, 1, -1, WHITE + "Back"));
        for (int slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        p.openInventory(inv);
    }

    public static void openMuteTimeGUI(User p) {
        Inventory inv = Bukkit.createInventory(null, 45, AQUA + "Mute Time GUI");
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // adding time items
        items.put(0, CI.createItem(Material.WARPED_PLANKS, 1, -1, DARK_GREEN + "Add 1 day"));
        items.put(2, CI.createItem(Material.WARPED_PLANKS, 1, -1, DARK_GREEN + "Add 1 hour"));
        items.put(4, CI.createItem(Material.WARPED_PLANKS, 1, -1, DARK_GREEN + "Add 1 minute"));
        items.put(6, CI.createItem(Material.WARPED_PLANKS,   1, -1, DARK_GREEN + "Add 1 second"));

        // objects that tell the player the time to add
        items.put(18, CI.createItem(Material.DIAMOND_BLOCK, 1, -1, WHITE + "0 day(s)"));
        items.put(20, CI.createItem(Material.GOLD_BLOCK, 1, -1, WHITE + "0 hour(s)"));
        items.put(22, CI.createItem(Material.IRON_BLOCK, 1, -1, WHITE + "0 minute(s)"));
        items.put(24, CI.createItem(Material.COAL_BLOCK, 1, -1, WHITE + "0 second(s)"));

        // remove time
        items.put(36, CI.createItem(Material.CRIMSON_PLANKS, 1, -1, DARK_GREEN + "Remove 1 day"));
        items.put(38, CI.createItem(Material.CRIMSON_PLANKS, 1, -1, DARK_GREEN + "Remove 1 hour"));
        items.put(40, CI.createItem(Material.CRIMSON_PLANKS, 1, -1, DARK_GREEN + "Remove 1 minute"));
        items.put(42, CI.createItem(Material.CRIMSON_PLANKS,   1, -1, DARK_GREEN + "Remove 1 second"));

        // misc
        items.put(17, CI.createItem(Material.LIME_WOOL, 1, -1, GREEN + "Mute"));
        items.put(35, CI.createItem(Material.RED_BANNER, 1, -1, WHITE + "Back"));
        for (int slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        p.openInventory(inv);
    }
}
