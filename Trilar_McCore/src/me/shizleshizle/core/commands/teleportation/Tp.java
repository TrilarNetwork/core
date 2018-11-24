package me.shizleshizle.core.commands.teleportation;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tp implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Teleportation" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tp <player> [player]");
					} else if (args.length == 1) {
						User t = new User(Bukkit.getPlayer(args[0]));
						if (!t.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						} else {
							if (t.hasTpDisabled()) {
								p.sendMessage(prefix + "This player has teleportation " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + "!");
							} else {
								p.teleport(t.getLocation());
								p.sendMessage(prefix + "You have been teleported to " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
							}
						}
					} else if (args.length == 2) {
						User t = new User(Bukkit.getPlayer(args[0]));
						User t2 = new User(Bukkit.getPlayer(args[1]));
						if (!t.isOnline() && !t2.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYERS_OFFLINE, args[0] + " " +  args[1]);
						} else if (!t.isOnline() || !t2.isOnline()) {
							if (t.isOnline()) {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
							} else {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
							}
						} else {
							if (t.hasTpDisabled()) {
								p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has teleportation " + ChatColor.GOLD + "disabled" 
										+ ChatColor.YELLOW + "!");
							} else {						
								t.teleport(t2.getLocation());
								p.sendMessage(prefix + "You have teleported " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " to " + ChatColor.GOLD 
										+ t2.getName() + ChatColor.YELLOW + "!");
								t.sendMessage(prefix + "You have been teleported to " + ChatColor.GOLD + t2.getName() + ChatColor.YELLOW + "!");
							}
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tp <player> [player]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tp");
				}
			}
		}
		return false;
	}
}
