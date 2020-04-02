package me.shizleshizle.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Back implements CommandExecutor {
	private final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Back" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("back")) {
			if (sender instanceof Player) {
				User p = new User((Player) sender);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						if (p.hasBack()) {
							Location loc = p.getBack();
							if (loc == null) {
								p.sendMessage(PREFIX + "You do not have a previous location!");
							} else {
								p.toBack();
								p.sendMessage(PREFIX + "You have been returned to your previous location!");
							}
						} else {
							p.sendMessage(PREFIX + "You do not have a previous location!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/back");
					} 
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/back");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}