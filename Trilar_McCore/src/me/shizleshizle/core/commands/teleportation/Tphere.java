package me.shizleshizle.core.commands.teleportation;

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

public class Tphere implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Teleportation" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tphere")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 1) {
						User t = new User(Bukkit.getPlayer(args[0]));
						if (!t.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						} else {
							if (t.hasTpDisabled()) {
								p.sendMessage(PREFIX + "This player has teleportation disabled!");
							} else {
								t.teleport(p.getLocation());
								t.sendMessage(PREFIX + "You have been teleported to " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
								p.sendMessage(PREFIX + "You have teleported " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " to yourself!");
							}
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tphere <player>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tphere");
				}
			}
		}
		return false;
	}
}