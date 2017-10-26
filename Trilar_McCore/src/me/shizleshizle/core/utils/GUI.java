package me.shizleshizle.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.shizleshizle.core.objects.User;

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
			ItemStack i = CI.createItem(Material.SKULL_ITEM, 1, -1, ChatColor.WHITE + ap.getName());
			SkullMeta m = (SkullMeta) i.getItemMeta();
			m.setOwner(ap.getName());
			i.setDurability((short) 3);
			i.setItemMeta(m);
			inv.addItem(i);
		}
		p.openInventory(inv);
	}
}
