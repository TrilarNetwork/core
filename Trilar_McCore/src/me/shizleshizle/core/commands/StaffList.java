package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class StaffList implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "List" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
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
				StringBuilder ldeve = new StringBuilder();
				StringBuilder deve = new StringBuilder();
				StringBuilder owners = new StringBuilder();
				StringBuilder manager = new StringBuilder();
				StringBuilder sradm = new StringBuilder();
				StringBuilder adm = new StringBuilder();
				StringBuilder srmode = new StringBuilder();
				StringBuilder mode = new StringBuilder();
				StringBuilder jrmode = new StringBuilder();
				StringBuilder srhelpers = new StringBuilder();
				StringBuilder helpers = new StringBuilder();
				StringBuilder jrhelpers = new StringBuilder();
				String ldev = "";
				String dev = "";
				String own = "";
				String man = "";
				String sra = "";
				String adn = "";
				String srm = "";
				String mod = "";
				String jrm = "";
				String srh = "";
				String hel = "";
				String jrh = "";
				for (String s : staff.keySet()) {
					switch (staff.get(s)) {
					case JR_HELPER:
						jrhelpers.append(s).append(" ");
						break;
					case HELPER:
						helpers.append(s).append(" ");
						break;
					case SR_HELPER:
						srhelpers.append(s).append(" ");
						break;
					case JR_MODERATOR:
						jrmode.append(s).append(" ");
						break;
					case MODERATOR:
						mode.append(s).append(" ");
						break;
					case SR_MODERATOR:
						srmode.append(s).append(" ");
						break;
					case ADMIN:
						adm.append(s).append(" ");
						break;
					case SR_ADMIN:
						sradm.append(s).append(" ");
						break;
					case MANAGER:
						manager.append(s).append(" ");
						break;
					case OWNER:
						owners.append(s).append(" ");
						break;
					case DEVELOPER:
						deve.append(s).append(" ");
						break;
					case LEAD_DEVELOPER:
						ldeve.append(s).append(" ");
						break;
					default:
						break;
					}
				}
				if (!ldeve.toString().isEmpty()) {
					ldev = ChatColor.GOLD + "[ " + PermGroup.LEAD_DEVELOPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.LEAD_DEVELOPER.getColor() + ldeve.toString().trim();
				}
				if (!deve.toString().isEmpty()) {
					dev = ChatColor.GOLD + "[ " + PermGroup.DEVELOPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.DEVELOPER.getColor() + deve.toString().trim();
				}
				if (!owners.toString().isEmpty()) {
					own = ChatColor.GOLD + "[ " + PermGroup.OWNER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.OWNER.getColor() + owners.toString().trim();
				}
				if (!manager.toString().isEmpty()) {
					man = ChatColor.GOLD + "[ " + PermGroup.MANAGER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MANAGER.getColor() + manager.toString().trim();
				}
				if (!sradm.toString().isEmpty()) {
					sra = ChatColor.GOLD + "[ " + PermGroup.SR_ADMIN.getPrefix() + ChatColor.GOLD + ": " + PermGroup.ADMIN.getColor() + sradm.toString().trim();
				}
				if (!adm.toString().isEmpty()) {
					adn = ChatColor.GOLD + "[ " + PermGroup.ADMIN.getPrefix() + ChatColor.GOLD + ": " + PermGroup.ADMIN.getColor() + adm.toString().trim();
				}
				if (!srmode.toString().isEmpty()) {
					srm = ChatColor.GOLD + "[ " + PermGroup.SR_MODERATOR.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MODERATOR.getColor() + srmode.toString().trim();
				}
				if (!mode.toString().isEmpty()) {
					mod = ChatColor.GOLD + "[ " + PermGroup.MODERATOR.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MODERATOR.getColor() + mode.toString().trim();
				}
				if (!jrmode.toString().isEmpty()) {
					jrm = ChatColor.GOLD + "[ " + PermGroup.JR_MODERATOR.getPrefix() + ChatColor.GOLD + ": " + PermGroup.MODERATOR.getColor() + jrmode.toString().trim();
				}
				if (!srhelpers.toString().isEmpty()) {
					srh = ChatColor.GOLD + "[ " + PermGroup.SR_HELPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.HELPER.getColor() + srhelpers.toString().trim();
				}
				if (!helpers.toString().isEmpty()) {
					hel = ChatColor.GOLD + "[ " + PermGroup.HELPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.HELPER.getColor() + helpers.toString().trim();
				}
				if (!jrhelpers.toString().isEmpty()) {
					jrh = ChatColor.GOLD + "[ " + PermGroup.JR_HELPER.getPrefix() + ChatColor.GOLD + ": " + PermGroup.HELPER.getColor() + jrhelpers.toString().trim();
				}
				sender.sendMessage(ChatColor.GOLD + "]=-");
				sender.sendMessage(ChatColor.GOLD + "[");
				sender.sendMessage(ChatColor.GOLD + "[ " + ChatColor.YELLOW + "Online Players: " + ChatColor.GOLD + "[" + ChatColor.DARK_AQUA + online + ChatColor.GOLD 
						+ "/" + ChatColor.DARK_AQUA + Bukkit.getMaxPlayers() + ChatColor.GOLD + "]");
				sender.sendMessage(ChatColor.GOLD + "[");
				if (!ldeve.toString().isEmpty()) {
					sender.sendMessage(ldev);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!deve.toString().isEmpty()) {
					sender.sendMessage(dev);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!owners.toString().isEmpty()) {
					sender.sendMessage(own);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!manager.toString().isEmpty()) {
					sender.sendMessage(man);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!sradm.toString().isEmpty()) {
					sender.sendMessage(sra);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!adm.toString().isEmpty()) {
					sender.sendMessage(adn);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!srmode.toString().isEmpty()) {
					sender.sendMessage(srm);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!mode.toString().isEmpty()) {
					sender.sendMessage(mod);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!jrmode.toString().isEmpty()) {
					sender.sendMessage(jrm);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!srhelpers.toString().isEmpty()) {
					sender.sendMessage(srh);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!helpers.toString().isEmpty()) {
					sender.sendMessage(hel);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				if (!jrhelpers.toString().isEmpty()) {
					sender.sendMessage(jrh);
					sender.sendMessage(ChatColor.GOLD + "[");
				}
				sender.sendMessage(ChatColor.GOLD + "]=-");
				staff.clear();
			} else {
				sender.sendMessage(PREFIX + "There are no players online.");
			}
		}
		return false;
	}
}