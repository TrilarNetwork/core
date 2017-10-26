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

public class Repair implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Repair" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("repair")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.BUILDER)) {
					if (args.length == 0) {
						p.repairInHand();
						p.sendMessage(prefix + "You have repaired the item in your hand!");
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("all")) {
							p.repairAll();
							p.sendMessage(prefix + "You have repaired all your items!");
						} else if (args[0].equalsIgnoreCase("armor")) {
							p.repairArmor();
							p.sendMessage(prefix + "You have repaired all your armor!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/repair <all|armor>");
						}
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/repair");
				}
			}
		}
		return false;
	}
}