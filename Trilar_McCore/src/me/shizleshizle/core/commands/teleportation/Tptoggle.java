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

public class Tptoggle implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tptoggle")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Tp.PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 0) {
						if (p.hasTpDisabled()) {
							p.setTpDisabled(false);
							p.sendMessage(Tp.PREFIX + "You have " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " teleporting!");
						} else {
							p.setTpDisabled(true);
							p.sendMessage(Tp.PREFIX + "You have " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " teleporting!");
						}
					} else if (args.length == 1) {
						User t = new User(Bukkit.getPlayer(args[0]));
						if (!t.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						} else {
							if (t.hasTpDisabled()) {
								t.setTpDisabled(false);
								t.sendMessage(Tp.PREFIX + "Your teleporting has been " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + "!");
								p.sendMessage(Tp.PREFIX + "You have " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " teleporting for " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
							} else {
								t.setTpDisabled(true);
								t.sendMessage(Tp.PREFIX + "Your teleporting has been " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + "!");
								p.sendMessage(Tp.PREFIX + "You have " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " teleporting for " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
							}
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tptoggle [player]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tptoggle");
				}
			}
		}
		return false;
	}
}