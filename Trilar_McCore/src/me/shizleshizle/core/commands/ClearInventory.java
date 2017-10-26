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

public class ClearInventory implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "ClearInventory" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearinventory")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.clearInventory();
						p.sendMessage(prefix + ChatColor.YELLOW + "Your inventory has been cleared!"); 
					} else if (args.length == 1) {
						Player x2 = Bukkit.getPlayerExact(args[0]);
						if (!x2.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, x2.getName());
						} else {
							User t = new User(x2);
							t.clearInventory();
							p.sendMessage(prefix + ChatColor.YELLOW + "You have cleared " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "'s  inventory!"); 
							t.sendMessage(prefix + ChatColor.YELLOW + "Your inventory has been cleared!"); 
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/clearinventory");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/clearinventory");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
