package me.shizleshizle.core.listeners;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.bansystem.GUIFunction;
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
		String title = ChatColor.stripColor(p.getOpenInventory().getTitle());
		if (i == null) return;
		if (title.equals("Player List")) {
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
		} else if (title.equals("Invsee")) {
			e.setCancelled(true);
		} else if (title.equals("Staff GUI")) {
			e.setCancelled(true);
			handleStaffGUI(p, i);
		}
	}

	private void handleStaffGUI(User p, ItemStack currentItem) {
		Material itemMat = currentItem.getType();
		// TODO: Open functions
		if (itemMat == Material.CRIMSON_DOOR) { // kick function
			Main.staffgui.put(p.getName(), GUIFunction.KICK);
		} else if (itemMat == Material.RED_WOOL) { // warn function
			Main.staffgui.put(p.getName(), GUIFunction.WARN);
		} else if (itemMat == Material.NETHERITE_AXE) { // ban function
			Main.staffgui.put(p.getName(), GUIFunction.BAN);
		} else if (itemMat == Material.BLUE_ICE) { // freeze function
			Main.staffgui.put(p.getName(), GUIFunction.FREEZE);
		} else if (itemMat == Material.COBWEB) { // mute function
			Main.staffgui.put(p.getName(), GUIFunction.MUTE);
		} else if (itemMat == Material.COMPASS) { // teleport function
			Main.staffgui.put(p.getName(), GUIFunction.TELEPORT);
		} else if (itemMat == Material.RED_BANNER) { // exit
			p.closeInventory();
		}
	}
}