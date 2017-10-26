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

public class DayCmd implements CommandExecutor {
	public String prefix = Time.prefix;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("day")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length != 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/day");
					} else {
						p.setDay(true);
						p.sendMessage(prefix + "You have set the time in all worlds to " + ChatColor.GOLD + "0" + ChatColor.YELLOW + " ticks!");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/day");
				}
			}
		}
		return false;
	}
}
