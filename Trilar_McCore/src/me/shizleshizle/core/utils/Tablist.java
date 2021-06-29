package me.shizleshizle.core.utils;

import me.shizleshizle.core.objects.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.GRAY;

public class Tablist {
	
	public static void updateTablist(Player... players) {
		try {
			for (Player p : players) {
				User p2 = new User(p);
				final String header = AQUA + "https://www.ourpersonalwebsite.com";
				final String footer = GRAY + "Rank: " + p2.getGroup().getPrefix();
				p2.sendHeaderAndFooter(header, footer);
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
