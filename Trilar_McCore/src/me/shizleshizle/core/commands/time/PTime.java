package me.shizleshizle.core.commands.time;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class PTime implements CommandExecutor {
	public String prefix = Time.prefix;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ptime")) {
			if (Main.isLobby()) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(prefix + "You must be a player to perform this command!");
				} else {
					Player x = (Player) sender;
					User p = new User(x);
					if (Perm.hasPerm(p, PermGroup.BUILDER)) {
						if (args.length == 1) {
							if (args[0].equalsIgnoreCase("day")) {
								p.setUserTime(13000, true);
								p.sendMessage(prefix + "Your time has been set to " + ChatColor.GOLD + "day" + ChatColor.YELLOW + "!");
							} else if (args[0].equalsIgnoreCase("night")) {
								p.setUserTime(0, true);
								p.sendMessage(prefix + "Your time has been set to " + ChatColor.GOLD + "night" + ChatColor.YELLOW + "!");
							} else if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("normal")) {
								p.resetUserTime();
								p.sendMessage(prefix + "Your time has been reset!");
							} else {
								ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/ptime <day|night|reset>");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/ptime <day|night|reset>");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/ptime");
					}
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "ptime");
			}
		}
		return false;
	}
}
