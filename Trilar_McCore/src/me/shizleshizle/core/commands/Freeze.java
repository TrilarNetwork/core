package me.shizleshizle.core.commands;

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

public class Freeze implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Freeze" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	public static boolean hasSent = false;
	public static boolean hasSent2 = false;
	public static boolean hasSent3 = false;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("freeze")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length != 1) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/freeze <player>");
					} else {
						User t = new User(Bukkit.getPlayer(args[0]));
						if (!t.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						} else {
							if (t.isFrozen()) {
								t.freezeUser(false);
								t.setGod(false);
								p.sendMessage(PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has been unfrozen!");
								t.sendMessage(PREFIX + "You have been unfrozen by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
							} else {
								t.freezeUser(true);
								t.setGod(true);
								p.sendMessage(PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has been frozen!");
								t.sendMessage(PREFIX + "You have been frozen by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
							}
						}
					}
				} else {
					ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/freeze");
				}
			}
		}
		return false;
	}
}
