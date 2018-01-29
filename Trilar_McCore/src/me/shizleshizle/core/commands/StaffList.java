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
				String manager = "";
				String sradm = "";
				String adm = "";
				String jradm = "";
				String srmode = "";
				String mode = "";
				String jrmode = "";
				String srhelpers = "";
				String helpers = "";
				String jrhelpers = "";
				for (String s : staff.keySet()) {
					switch (staff.get(s)) {
					case JR_HELPER:
						jrhelpers += s + " ";
						break;
					case HELPER:
						helpers += s + " ";
						break;
					case SR_HELPER:
						srhelpers += s + " ";
						break;
					case JR_MODERATOR:
						jrmode += s + " ";
						break;
					case MODERATOR:
						mode += s + " ";
						break;
					case SR_MODERATOR:
						srmode += s + " ";
						break;
					case JR_ADMIN:
						jradm += s + " ";
						break;
					case ADMIN:
						adm += s + " ";
						break;
					case SR_ADMIN:
						sradm += s + " ";
						break;
					case MANAGER:
						manager += s + " ";
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
				if (!manager.isEmpty()) {
					manager = ChatColor.GOLD + "[ " + PermGroup.MANAGER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MANAGER.getColor() + manager.trim();
				}
				if (!sradm.isEmpty()) {
					sradm = ChatColor.GOLD + "[ " + PermGroup.SR_ADMIN.getPrefix() + ChatColor.GOLD + ": " + PermGroup.ADMIN.getColor() + sradm.trim();
				}
				if (!adm.isEmpty()) {
					adm = ChatColor.GOLD + "[ " + PermGroup.ADMIN.getPrefix() + ChatColor.GOLD + ": " + PermGroup.ADMIN.getColor() + adm.trim();
				}
				if (!jradm.isEmpty()) {
					jradm = ChatColor.GOLD + "[ " + PermGroup.JR_ADMIN.getPrefix() + ChatColor.GOLD + ": " + PermGroup.ADMIN.getColor() + jradm.trim();
				}
				if (!srmode.isEmpty()) {
					srmode = ChatColor.GOLD + "[ " + PermGroup.SR_MODERATOR.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MODERATOR.getColor() + srmode.trim();
				}
				if (!mode.isEmpty()) {
					mode = ChatColor.GOLD + "[ " + PermGroup.MODERATOR.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MODERATOR.getColor() + mode.trim();
				}
				if (!jrmode.isEmpty()) {
					jrmode = ChatColor.GOLD + "[ " + PermGroup.JR_MODERATOR.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MODERATOR.getColor() + jrmode.trim();
				}
				if (!srhelpers.isEmpty()) {
					srhelpers = ChatColor.GOLD + "[ " + PermGroup.SR_HELPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.HELPER.getColor() + srhelpers.trim();
				}
				if (!helpers.isEmpty()) {
					helpers = ChatColor.GOLD + "[ " + PermGroup.HELPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.HELPER.getColor() + helpers.trim();
				}
				if (!jrhelpers.isEmpty()) {
					jrhelpers = ChatColor.GOLD + "[ " + PermGroup.JR_HELPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.HELPER.getColor() + jrhelpers.trim();
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
				if (!manager.isEmpty()) {
					sender.sendMessage(manager);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!sradm.isEmpty()) {
					sender.sendMessage(sradm);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!adm.isEmpty()) {
					sender.sendMessage(adm);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!jradm.isEmpty()) {
					sender.sendMessage(jradm);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!srmode.isEmpty()) {
					sender.sendMessage(srmode);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!mode.isEmpty()) {
					sender.sendMessage(mode);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!jrmode.isEmpty()) {
					sender.sendMessage(jrmode);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!srhelpers.isEmpty()) {
					sender.sendMessage(srhelpers);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!helpers.isEmpty()) {
					sender.sendMessage(helpers);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!jrhelpers.isEmpty()) {
					sender.sendMessage(jrhelpers);
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