package me.shizleshizle.core.utils;

import org.bukkit.ChatColor;

import me.shizleshizle.core.Main;

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
	
	public static String getStrings() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Main.msgs.size(); i++) {
			sb.append(ChatColor.WHITE + Main.msgs.get(i)).append(ChatColor.GOLD + ", ");
		}
		String s = sb.toString().substring(0, sb.length() - 2);
		return s;
	}
}
