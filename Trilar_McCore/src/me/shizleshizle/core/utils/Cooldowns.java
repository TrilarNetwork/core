package me.shizleshizle.core.utils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.shizleshizle.core.Main;

public class Cooldowns {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Cooldowns" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	public static HashMap<UUID, Integer> cooldown = new HashMap<UUID, Integer>();
	
	public static void runCooldown() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (cooldown.isEmpty()) {
					return;
				}
				for (UUID uuid : cooldown.keySet()) {
					int ct = cooldown.get(uuid);
					if (ct <= 0) {
						cooldown.remove(uuid);
						if (Bukkit.getPlayer(uuid).isOnline()) {
							Bukkit.getPlayer(uuid).sendMessage(prefix + "Your cooldown has expired!");
						}
					} else {
						cooldown.put(uuid, ct - 1);
					}
				}
			}
		}.runTaskTimer(Main.p, 0, 20);
	}	
}
