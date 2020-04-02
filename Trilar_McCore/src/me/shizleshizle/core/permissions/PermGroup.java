
package me.shizleshizle.core.permissions;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.DARK_AQUA;
import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.DARK_RED;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.YELLOW;
import static org.bukkit.ChatColor.WHITE;

import org.bukkit.ChatColor;

public enum PermGroup {
	
	LEAD_DEVELOPER(550, GRAY + "(" + DARK_AQUA + "Lead" + GRAY + "-" + DARK_AQUA + "Developer" + GRAY + ") " + AQUA, "Lead-Developer", AQUA, "&b"),
	DEVELOPER(525, GRAY + "(" + YELLOW + "Developer" + GRAY + ") " + GOLD, "Developer", GOLD, "&6"),
	OWNER(500, GRAY + "(" + DARK_RED + "Owner" + GRAY + ") " + DARK_RED, "Owner", DARK_RED, "&c"),
	MANAGER(450, GRAY + "(" + DARK_AQUA + "Manager" + GRAY + ") " + DARK_AQUA, "Manager", DARK_AQUA, "&3"),
	SR_ADMIN(425, GRAY + "(" + RED + "Sr" + RED + "-" + RED + "Admin" + GRAY + ") " + RED, "Sr-Admin", RED, "&c"),
	ADMIN(400, GRAY + "(" + RED + "Admin" + GRAY + ") " + RED, "Admin", RED, "&c"),
	JR_ADMIN(375, GRAY + "(" + RED + "Jr" + RED + "-"  + RED +  "Admin" + GRAY + ") " + RED, "Jr-Admin", RED, "&c"),
	SR_MODERATOR(325, GRAY + "(" + AQUA + "Sr" + AQUA + "-" + AQUA + "Moderator" + GRAY + ") " + AQUA, "Sr-Moderator", AQUA, "&b"),
	MODERATOR(300, GRAY + "(" + AQUA + "Moderator" + GRAY + ") " + AQUA, "Moderator", AQUA, "&b"),
	JR_MODERATOR(275, GRAY + "(" + AQUA + "Jr" + AQUA + "-" + AQUA + "Moderator" + GRAY + ") " + AQUA, "Jr-Moderator", AQUA, "&b"),
	SR_HELPER(225, GRAY + "(" + GOLD + "Sr" + GOLD + "-" + GOLD + "Helper" + GRAY + ") " + GOLD, "Sr-Helper", GOLD, "&6"),
	HELPER(200, GRAY + "(" + GOLD + "Helper" + GRAY + ") " + GOLD, "Helper", GOLD, "&6"),
	JR_HELPER(175, GRAY + "(" + GOLD + "Jr" + GOLD + "-" + GOLD + "Helper" + GRAY + ") " + GOLD, "Jr-Helper", GOLD, "&6"),
	HEAD_BUILDER(150, GRAY + "(" + GREEN + "Head-Builder" + GRAY + ") " + GREEN, "Head-Builder", GREEN, "&a"),
	BUILDER(100, GRAY + "(" + GREEN + "Builder" + GRAY + ") " + GREEN, "Builder", GREEN, "&a"),
	YOUTUBE(99, GRAY + "(" + WHITE + "You" + RED + "Tube" + GRAY + ") " + RED, "YouTube", WHITE, "&f"),
	TRILAR(90, GRAY + "(" + GOLD + "Trilar" + GRAY + ") " + AQUA, "Trilar", AQUA, "&7"),
	VIP3(80, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&7"),
	VIP2(60, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&7"),
	VIP1(40, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&7"),
	VIP(20, GRAY + "(" + AQUA + "Vip" + GRAY + ") " + AQUA, "Vip", AQUA, "&7"),
	MEMBER(1, GRAY + "(" + DARK_GRAY + "Member" + GRAY + ") " + GREEN, "Member", GREEN, "&7");
	
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
