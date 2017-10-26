package me.shizleshizle.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.permissions.PermissionGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Ranks implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Ranks" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("rank")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
				return true;
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.SERVER_MANAGER) || (p.getName().equals("shizleshizle") || p.getName().equals("iMelvin"))) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("list")) {
							p.sendMessage(prefix + "There are a total of " + ChatColor.GOLD + PermGroup.getTotal() + ChatColor.YELLOW + " ranks! Here they are:");
							for (PermGroup s : PermGroup.values()) {
									p.sendMessage(s.getPrefix());
							}
							p.sendMessage(ChatColor.YELLOW + "Default doesn't have a prefix, but default is the rank people get when they join for the first time.");
							return true;
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/rank <list|set|get|list|add|remove> [user|rank|group] [rank|permission]");
							return true;
						}
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("get")) {
							PermGroup pg = Perm.getGroup(args[1]);
							if (pg != null) {
								p.sendMessage(prefix + "Player " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " is in group " 
										+ ChatColor.GOLD + pg.getName() + ChatColor.YELLOW + "!");
								return true;
							} else {
								p.sendMessage(prefix + "Database could not find player " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
								return true;
							}
						} else if (args[0].equalsIgnoreCase("list")) {
							PermGroup pg = Perm.getGroup(args[1]);
							p.sendMessage(prefix + ChatColor.GOLD + pg.getName() + ChatColor.YELLOW + " has these permissions:");
							for (String pe : PermissionGroup.getPermissions(pg)) {
								p.sendMessage(ChatColor.GRAY + pe);
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/rank <list|set|get> [user] [rank]");
							return true;
						}
					} else if (args.length == 3) {
						if (args[0].equalsIgnoreCase("set")) {
							String user = args[1];
							PermGroup g = null;
							for (PermGroup s : PermGroup.values()) {
								if (s.getName().equalsIgnoreCase(args[2])) {
									g = s;
								}
							}
							if (g == null) {
								p.sendMessage(prefix + "Invalid rank!");
								return true;
							} else {
								Main.sql.getReady();
								if (g.equals(PermGroup.OWNER) && (p.getName().equals("shizleshizle") || p.getName().equals("iMelvin") || Perm.hasPerm(p, PermGroup.OWNER))) { 
									Perm.updateGroup(user, g);
								} else if (g.equals(PermGroup.LEAD_DEVELOPER) && p.getName().equals("shizleshizle")) {
									Perm.updateGroup(user, g);
								} else if (g.equals(PermGroup.DEVELOPER) && (p.getName().equals("shizleshizle") || p.getName().equals("iMelvin") 
										|| Perm.hasPerm(p, PermGroup.DEVELOPER))) {
									Perm.updateGroup(user, g);
								} else if (g.equals(PermGroup.SERVER_MANAGER) && ( (p.getName().equals("shizleshizle") || p.getName().equals("iMelvin")) 
										|| Perm.hasPerm(p, PermGroup.SERVER_MANAGER) )) {	
									Perm.updateGroup(user, g);
								} else if (g.getId() < PermGroup.SERVER_MANAGER.getId() && Perm.hasPerm(p, PermGroup.SERVER_MANAGER)) {
									Perm.updateGroup(user, g);
								} else {
									p.sendMessage(prefix + "You can not set this rank!");
									return true;
								}
								Player t = Bukkit.getPlayerExact(user);
								if (t.getName().equals(p.getName())) {
									p.sendMessage(prefix + "Your rank has been updated to " + ChatColor.GOLD + g.getName() + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(prefix + "You have updated " + ChatColor.GOLD + user + ChatColor.YELLOW + "'s rank to " 
											+ ChatColor.GOLD + g.getName() + ChatColor.YELLOW + "!");
									if (t.isOnline()) {
										t.sendMessage(prefix + "Your rank has been updated to " + ChatColor.GOLD + g.getName() + ChatColor.YELLOW + " by " + ChatColor.GOLD
												+ p.getName() + ChatColor.YELLOW + "!");
									}
								}
								return true;
							}
						} else if (args[0].equalsIgnoreCase("add")) {
							PermGroup pg = Perm.getGroup(args[1]);
							if (pg == null) {
								p.sendMessage(prefix + "Invalid group!");
							} else {
								String perm = args[2].trim();
								if (!PermissionGroup.getPermissions(pg).contains(perm)) {
									PermissionGroup.addPermission(pg, perm);
									p.sendMessage(prefix + "You have added '" + ChatColor.GOLD + perm + ChatColor.YELLOW + "' to " + ChatColor.GOLD + pg.getName() 
										+ ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(prefix + "Group " + ChatColor.GOLD + pg.getName() + ChatColor.YELLOW + " already has permission '" 
											+ ChatColor.GOLD + perm + ChatColor.YELLOW + "'!");
								}
							}
						} else if (args[0].equalsIgnoreCase("remove")) {
							PermGroup pg = Perm.getGroup(args[1]);
							if (pg == null) {
								p.sendMessage(prefix + "Invalid group!");
							} else {
								String perm = args[2].trim();
								if (PermissionGroup.getPermissions(pg).contains(perm)) {
									PermissionGroup.removePermission(pg, perm);
									p.sendMessage(prefix + "You have removed '" + ChatColor.GOLD + perm + ChatColor.YELLOW + "' from " + ChatColor.GOLD 
											+ pg.getName() + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(prefix + "Group " + ChatColor.GOLD + pg.getName() + ChatColor.YELLOW + " does not have permission '" 
											+ ChatColor.GOLD + perm + ChatColor.YELLOW + "'!");
								}
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/rank <list|set|get|add|remove> [user|rank] [rank|permission]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/rank <list|set|get> [user] [rank]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/rank");
				}
			}
		}
		return false;
	}
}