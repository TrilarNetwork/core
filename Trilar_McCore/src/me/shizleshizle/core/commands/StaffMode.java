package me.shizleshizle.core.commands;

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

public class StaffMode implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "StaffMode" + ChatColor.GOLD + " >> "
			+ ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("StaffMode")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.JR_HELPER)) {
					if (args.length == 0) {
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/StaffMode");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			}
		}
		return false;
	}
}
