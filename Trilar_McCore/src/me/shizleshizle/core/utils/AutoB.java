package me.shizleshizle.core.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.shizleshizle.core.Main;

public class AutoB {
	private static List<String> msgs = Main.c.getConfig().getStringList("settings.broadcastMessages");
	private static boolean b = true;
	private static int i = 0;
	
	public static void broadcast() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.p, () -> {
			if (b) {
				Bukkit.broadcastMessage(ChatColor.GOLD + "<=====================>");
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msgs.get(i)).trim());
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage(ChatColor.GOLD + ">=====================<");
				i++;
				if (i > msgs.size()) {
					i = 0;
				}
			}
		}, Main.abdelay*20, 20);
	}
	
	public static void enable() {
		b = true;
		broadcast();
	}
	
	public static void disable() {
		b = false;
	}
	
	public static boolean isBroadcasting() {
		return b;
	}
	
	public static String getStrings() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < msgs.size(); i++) {
			sb.append(ChatColor.WHITE + msgs.get(i)).append(ChatColor.GOLD + ", ");
		}
		String s = sb.toString().substring(0, sb.length() - 2);
		return s;
	}
}
