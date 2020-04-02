package me.shizleshizle.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;

public class Tablist {
	
	public static void updateTablist(Player... players) {
		try {
			for (Player p : players) {
				User p2 = new User(p);
				Title.sendHeaderAndFooter(p, Main.prefix, ChatColor.AQUA + "https://www.trilarnetwork.nl"
								+ ChatColor.GOLD + "\n   Online:" + ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + " -» " + ChatColor.GOLD + getTotal() + " players" 
								+ ChatColor.GRAY + "\n Rank: " + p2.getGroup().getPrefix());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int getTotal() {
		int i = 0;
		for (Player x : Bukkit.getOnlinePlayers()) {
			User p = new User(x);
			if (!p.isVanished()) {
				i++;
			}
		}
		return i;
	}
}
