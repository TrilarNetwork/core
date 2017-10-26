package me.shizleshizle.core.permissions;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.DARK_AQUA;
import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.DARK_GREEN;
import static org.bukkit.ChatColor.DARK_RED;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.YELLOW;

import org.bukkit.ChatColor;

public enum PermGroup {
	
	LEAD_DEVELOPER(550, GRAY + "(" + DARK_AQUA + "Lead" + GRAY + "-" + DARK_AQUA + "Developer" + GRAY + ") " + AQUA, "Lead-Developer", AQUA, "&3"),
	DEVELOPER(525, GRAY + "(" + YELLOW + "Developer" + GRAY + ") " + GOLD, "Developer", GOLD, "&6"),
	OWNER(500, GRAY + "(" + DARK_RED + "Owner" + GRAY + ") " + RED, "Owner", RED, "&c"),
	SERVER_MANAGER(450, GRAY + "(" + RED + "Server" + GRAY + "-" + RED + "Manager" + GRAY + ") " + RED, "Server-Manager", RED, "&e"),
	ADMIN(400, GRAY + "(" + DARK_RED + "Admin" + GRAY + ") " + RED, "Admin", RED, "&c"),
	MODERATOR(300, GRAY + "(" + GOLD + "Moderator" + GRAY + ") " + YELLOW, "Moderator", YELLOW, "&9"),
	HELPER(200, GRAY + "(" + DARK_GREEN + "Helper" + GRAY + ") " + GREEN, "Helper", GREEN, "&b"),
	HEAD_BUILDER(199, GRAY + "(" + GREEN + "Head-Builder" + GRAY + ") " + GREEN, "Head-Builder", GREEN, "&a"),
	BUILDER(100, GRAY + "(" + DARK_GREEN + "Builder" + GRAY + ") " + GREEN, "Builder", GREEN, "&2"),
	VIP4(90, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&b"),
	VIP3(80, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&b"),
	VIP2(60, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&b"),
	VIP1(40, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&b"),
	VIP(20, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&b"),
	MEMBER(1, GRAY + "(" + DARK_GRAY + "Member" + GRAY + ") " + GREEN, "Member", GREEN, "&8");
	
	private int group;
	private String pref;
	private String name;
	private ChatColor color;
	private String cc;
	
	PermGroup(int id, String prefix, String name, ChatColor color, String cc) {
		group = id;
		pref = prefix;
		this.name = name;
		this.color = color;
		this.cc = cc;
	}
	
	public int getId() {
		return group;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public String getChatColor() {
		return cc;
	}
	
	public String getPrefix() {
		return pref;
	}
	
	public String getName() {
		return name;
	}
	
	public static PermGroup get(int i) {
		for (PermGroup s : values()) {
			if (s.group == i) {
				return s;
			}
		}
		return null;
	}
	
	public static PermGroup get(String name) {
		for (PermGroup s : values()) {
			if (s.toString().replace("_", "").equalsIgnoreCase(name.replace("_", "").replace("+", "p"))) {
				return s;
			}
		}
		return null;
	}
	
	public static int getTotal() {
		int tot= 0;
		for (int i = 0; i < values().length; i++) {
			tot++;
		}
		return tot;
	}
}