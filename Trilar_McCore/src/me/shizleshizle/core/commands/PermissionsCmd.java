package me.shizleshizle.core.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class PermissionsCmd implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Permissions" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("permission")) {
			if (sender instanceof Player) {
				User p = new User((Player) sender);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 1) {
						User t = new User(Bukkit.getPlayer(args[0]));
						if (t.isOnline()) {
							p.sendMessage(PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has these permissions:");
							for (String perm : t.getPermissions()) {
								p.sendMessage(ChatColor.GOLD + perm);
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						}
					} else if (args.length == 3) {
						if (args[0].equalsIgnoreCase("add")) {
							User t = new User(Bukkit.getPlayer(args[1]));
							if (t.isOnline()) {
								String perm = args[2].trim();
								if (t.getPermissions().contains(perm)) {
									p.sendMessage(PREFIX + "Player " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " already has permission '" + ChatColor.GOLD + perm + ChatColor.YELLOW + "'!");
								} else {
									t.addPermission(perm);
									p.sendMessage(PREFIX + "You have added '" + ChatColor.GOLD + perm + ChatColor.YELLOW + "' to " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
							}
						} else if (args[0].equalsIgnoreCase("remove")) {
							User t = new User(Bukkit.getPlayer(args[1]));
							if (t.isOnline()) {
								ArrayList<String> perms = new ArrayList<>();
								String perm = args[2].trim();
								perms.add(perm);
								if (t.getPermissions().contains(perm)) {
									t.removePermissions(perms);
									p.sendMessage(PREFIX + "You have removed '" + ChatColor.GOLD + perm + ChatColor.YELLOW + "' from " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(PREFIX + "Player " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " does not have permission '" + ChatColor.GOLD + perm + ChatColor.YELLOW + "'!");
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/permission <user|add|remove> [user] [permission]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/permission <user|add|remove> [user] [permission]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/permission");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
