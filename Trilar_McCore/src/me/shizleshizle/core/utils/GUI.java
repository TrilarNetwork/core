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
        // TODO: set items
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
        // TODO: set items
        for (int slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        p.openInventory(inv);
    }

    public static void openMuteGUI(User p) {
        Inventory inv = Bukkit.createInventory(null, 36, AQUA + "Mute GUI");
        HashMap<Integer, ItemStack> items = new HashMap<>();
        // TODO: set items
        for (int slot : items.keySet()) {
            inv.setItem(slot, items.get(slot));
        }
        p.openInventory(inv);
    }
}
