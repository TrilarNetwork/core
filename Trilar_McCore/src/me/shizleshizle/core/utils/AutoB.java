package me.shizleshizle.core.utils;

import me.shizleshizle.core.Main;
import org.bukkit.ChatColor;

public class AutoB {
	private static boolean b = true;
	
	public static void enable() {
		b = true;
	}
	
	public static void disable() {
		b = false;
	}
	
	public static boolean isBroadcasting() {
		return b;
	}

	public static void setBroadcasting(boolean broadcasting) {
		broadcasting = b;
	}

	public static void saveToConfig() {
		Main.c.getConfig().set("settings.enableAutoBroadcast", b);
		Main.c.saveConfig();
	}
	
	public static String getStrings() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Main.msgs.size(); i++) {
			sb.append(ChatColor.WHITE).append(Main.msgs.get(i)).append(ChatColor.GOLD).append(", ");
		}
		return sb.toString().substring(0, sb.length() - 2);
	}
}
