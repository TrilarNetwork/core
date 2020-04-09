package me.shizleshizle.core.utils;

import me.shizleshizle.core.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

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
			ItemStack i = CI.createItem(Material.PLAYER_HEAD, 1, -1, ChatColor.WHITE + ap.getName());
			SkullMeta meta = (SkullMeta) i.getItemMeta();
			assert meta != null;
			meta.setOwningPlayer(ap.getPlayer());
			i.setItemMeta(meta);
			inv.addItem(i);
		}
		p.openInventory(inv);
	}
}
