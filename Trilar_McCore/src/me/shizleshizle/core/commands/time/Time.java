package me.shizleshizle.core.commands.time;

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

public class Time implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Time" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("time")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 2) {
						if (args[0].equalsIgnoreCase("set")) {
							if (args[1].equalsIgnoreCase("day")) {
								p.setDay(false);
								p.sendMessage(prefix + "You have set the time in your world to " + ChatColor.GOLD + "0" + ChatColor.YELLOW + " ticks!");
							} else if (args[1].equalsIgnoreCase("night")) {
								p.setNight(false);
								p.sendMessage(prefix + "You have set the time in your world to " + ChatColor.GOLD + "13000" + ChatColor.YELLOW + " ticks!");
							} else {
								ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/time set <day|night> [allwords]");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/time set <day|night> [allwords]");
						}
					} else if (args.length == 3) {
						if (args[0].equalsIgnoreCase("set")) {
							if (args[1].equalsIgnoreCase("day")) {
								p.setDay(true);
								p.sendMessage(prefix + "You have set the time in all world to " + ChatColor.GOLD + "0" + ChatColor.YELLOW + " ticks!");
							} else if (args[1].equalsIgnoreCase("night")){
								p.setNight(true);
								p.sendMessage(prefix + "You have set the time in all world to " + ChatColor.GOLD + "13000" + ChatColor.YELLOW + " ticks!");
							} else {
								ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/time set <day|night> [allwords]");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/time set <day|night> [allwords]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/time set <day|night> [allwords]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/time");
				}
			}
		}
		return false;
	}
}
