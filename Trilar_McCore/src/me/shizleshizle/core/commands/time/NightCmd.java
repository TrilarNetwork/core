package me.shizleshizle.core.commands.time;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCmd implements CommandExecutor {
	public final String PREFIX = Time.PREFIX;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("night")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length != 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/night");
					} else {
						p.setNight(true);
						p.sendMessage(PREFIX + "You have set the time in all world to " + ChatColor.GOLD + "13000" + ChatColor.YELLOW + " ticks!");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/night");
				}
			}
		}
		return false;
	}
}
