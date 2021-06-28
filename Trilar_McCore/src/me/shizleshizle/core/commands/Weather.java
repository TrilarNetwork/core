package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.objects.WeatherTypes;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Weather implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Weather" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("weather")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("downfall")) {
							p.setWeather(WeatherTypes.STORM);
						} else if (args[0].equalsIgnoreCase("clear")) {
							p.setWeather(WeatherTypes.CLEAR);
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/weather");
						}
					} else if (args.length == 2) {
						if (args[1].equalsIgnoreCase("self")) {
							if (args[0].equalsIgnoreCase("downfall")) {
								p.setUserWeather(WeatherType.DOWNFALL);
							} else if (args[0].equalsIgnoreCase("clear")) {
								p.setUserWeather(WeatherType.CLEAR);
							} else {
								ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/weather");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/weather <downfall|clear> [self]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/weather <downfall|clear> [self]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/weather");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		} else if (cmd.getName().equalsIgnoreCase("sun")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.setWeather(WeatherTypes.CLEAR);
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/sun");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/sun");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		} else if (cmd.getName().equalsIgnoreCase("storm")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.setWeather(WeatherTypes.STORM);
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/storm");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/storm");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
