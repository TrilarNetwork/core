package me.shizleshizle.core.commands.cmdutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.shizleshizle.core.ConfigManager;
import me.shizleshizle.core.objects.User;

public class VanishUtils {
	private static ConfigManager c = ConfigManager.getInstance();
    private static List<String> cansee = c.getConfig().getStringList("settings.canSeeVanished");
    public static HashMap<String, ItemStack[]> pInv = new HashMap<>();
    private static Map<String, Inventory> invs = new HashMap<>();

    public static void addInvs(User p, Inventory i) {
        invs.put(p.getName(), i);
    }

    public static Inventory getInvs(User p) {
        if (containsInvs(p)) {
            return invs.get(p.getName());
        } else {
            return null;
        }
    }

    public static boolean containsInvs(User p) {
        return invs.containsKey(p.getName());
    }

    public static void addSee(User p) {
        if (!cansee.contains(p.getName())) {
            cansee.add(p.getName());
            c.getConfig().set("settings.canSeeVanished", null);
            c.getConfig().set("settings.canSeeVanished", cansee);
            c.saveConfig();
        }
    }

    public static void removeSee(User p) {
        if (cansee.contains(p.getName())) {
            cansee.remove(p.getName()); //settings.canSeeVanished
            c.getConfig().set("settings.canSeeVanished", null);
            c.getConfig().set("settings.canSeeVanished", cansee);
            c.saveConfig();
        }
    }

    public static boolean canSee(User p) {
        return cansee.contains(p.getName());
    }
}
