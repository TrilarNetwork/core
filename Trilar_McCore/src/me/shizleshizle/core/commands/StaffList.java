package me.shizleshizle.core.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;

public class StaffList implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "List" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	private HashMap<String, PermGroup> staff = new HashMap<>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("staff")) {
			int online = 0;
			for (Player s : Bukkit.getOnlinePlayers()) {
				User p = new User(s);
				if (!p.isVanished()) {
					online++;
				}
			}
			int amount = 0;
			for (Player ap : Bukkit.getOnlinePlayers()) {
				User p = new User(ap);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (!p.isVanished()) {
						staff.put(p.getName(), p.getGroup());
						amount++;
					}
				}
			}
			if (amount >= 1) {
				String ldeve = "";
				String deve = "";
				String owners = "";
				String sm = "";
				String adm = "";
				String mode = "";
				String helpers = "";
				String hb = "";
				for (String s : staff.keySet()) {
					switch (staff.get(s)) {
					case HEAD_BUILDER:
						hb += s + " ";
						break;
					case HELPER:
						helpers += s + " ";
						break;
					case MODERATOR:
						mode += s + " ";
						break;
					case ADMIN:
						adm += s + " ";
						break;
					case SERVER_MANAGER:
						sm += s + " ";
						break;
					case OWNER:
						owners += s + " ";
						break;
					case DEVELOPER:
						deve += s + " ";
						break;
					case LEAD_DEVELOPER:
						ldeve += s + " ";
						break;
					default:
						break;
					}
				}
				if (!ldeve.isEmpty()) {
					ldeve = ChatColor.GOLD + "[ " + PermGroup.LEAD_DEVELOPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.LEAD_DEVELOPER.getColor() + ldeve.trim();
				}
				if (!deve.isEmpty()) {
					deve = ChatColor.GOLD + "[ " + PermGroup.DEVELOPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.DEVELOPER.getColor() + deve.trim();
				}
				if (!owners.isEmpty()) {
					owners = ChatColor.GOLD + "[ " + PermGroup.OWNER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.OWNER.getColor() + owners.trim();
				}
				if (!sm.isEmpty()) {
					sm = ChatColor.GOLD + "[ " + PermGroup.SERVER_MANAGER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.SERVER_MANAGER.getColor() + sm.trim();
				}
				if (!adm.isEmpty()) {
					adm = ChatColor.GOLD + "[ " + PermGroup.ADMIN.getPrefix() + ChatColor.GOLD + ": " + PermGroup.ADMIN.getColor() + adm.trim();
				}
				if (!mode.isEmpty()) {
					mode = ChatColor.GOLD + "[ " + PermGroup.MODERATOR.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MODERATOR.getColor() + mode.trim();
				}
				if (!helpers.isEmpty()) {
					helpers = ChatColor.GOLD + "[ " + PermGroup.HELPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.HELPER.getColor() + helpers.trim();
				}
				if  (!hb.isEmpty()) {
					hb = ChatColor.GOLD + "[ " + PermGroup.HEAD_BUILDER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.HEAD_BUILDER.getColor() + hb.trim();
				}
				sender.sendMessage(ChatColor.GOLD + "]=-");
				sender.sendMessage(ChatColor.GOLD + "[");
				sender.sendMessage(ChatColor.GOLD + "[ " + ChatColor.YELLOW + "Online Players: " + ChatColor.GOLD + "[" + ChatColor.DARK_AQUA + online + ChatColor.GOLD 
						+ "/" + ChatColor.DARK_AQUA + Bukkit.getMaxPlayers() + ChatColor.GOLD + "]");
				sender.sendMessage(ChatColor.GOLD + "[");
				if (!ldeve.isEmpty()) {
					sender.sendMessage(ldeve);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!deve.isEmpty()) {
					sender.sendMessage(deve);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!owners.isEmpty()) {
					sender.sendMessage(owners);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!sm.isEmpty()) {
					sender.sendMessage(sm);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!adm.isEmpty()) {
					sender.sendMessage(adm);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!mode.isEmpty()) {
					sender.sendMessage(mode);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!helpers.isEmpty()) {
					sender.sendMessage(helpers);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!hb.isEmpty()) {
					sender.sendMessage(hb);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				sender.sendMessage(ChatColor.GOLD + "]=-");
				staff.clear();
			} else {
				sender.sendMessage(prefix + "There are no players online.");
			}
		}
		return false;
	}
}