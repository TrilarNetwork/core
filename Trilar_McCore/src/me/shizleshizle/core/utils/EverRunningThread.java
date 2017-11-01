package me.shizleshizle.core.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.shizleshizle.core.Main;

public class EverRunningThread implements Runnable {
	private static List<String> msgs = Main.c.getConfig().getStringList("settings.broadcastMessages");
	private static int i = 0;

	@Override
	public void run() {
		if (AutoB.isBroadcasting()) {
			Bukkit.broadcastMessage(ChatColor.GOLD + "<=====================>");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msgs.get(i)).trim());
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(ChatColor.GOLD + ">=====================<");
			i++;
			if (i > msgs.size()) {
				i = 0;
			}
			Main.sleepBroadcast();
		}
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
