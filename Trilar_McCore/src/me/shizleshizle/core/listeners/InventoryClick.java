package me.shizleshizle.core.listeners;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.cmdutils.VanishUtils;
import me.shizleshizle.core.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InventoryClick implements Listener {

	@EventHandler
	public void onIC(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) return;
		User p = new User((Player) e.getWhoClicked());
		if (p.isVanished()) {
			if (VanishUtils.containsInvs(p)) {
				if (e.getInventory().equals(VanishUtils.getInvs(p))) {
					e.setCancelled(true);
				}
			}
		}
		ItemStack i = e.getCurrentItem();
		if (i == null) return;
		if (ChatColor.stripColor(p.getOpenInventory().getTitle()).equals("Player List")) {
			e.setCancelled(true);
			if (i.getType() == Material.PLAYER_HEAD) {
				if (i.hasItemMeta()) {
					Player t = Bukkit.getPlayer(ChatColor.stripColor(Objects.requireNonNull(i.getItemMeta()).getDisplayName()));
					if (t == null) {
						p.sendMessage(Main.PREFIX + "This player does not exist!");
						return;
					}
					p.teleport(t.getLocation());
					p.closeInventory();
				}
			}
		} else if (ChatColor.stripColor(p.getOpenInventory().getTitle()).equals("Invsee")) {
			e.setCancelled(true);
		}
	}
}