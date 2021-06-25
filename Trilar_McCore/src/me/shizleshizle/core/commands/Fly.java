package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
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

import static org.bukkit.ChatColor.*;

public class Fly implements CommandExecutor {
	private final String PREFIX = YELLOW.toString() + BOLD + "Fly" + GOLD + " >> " + YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			} else {
				User p = new User((Player) sender);
				if (args.length == 0) {
					if (Perm.hasPerm(p, PermGroup.HELPER)) {
						if (p.getAllowFlight()) {
							p.setFly(false);
							p.sendMessage(PREFIX + "Fly mode has been " + GOLD + "disabled" + YELLOW + "!");
						} else {
							p.setFly(true);
							p.sendMessage(PREFIX + "Fly mode has been " + GOLD + "enabled" + YELLOW + "!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/fly");
					}
				} else if (args.length == 1) {
					if (Perm.hasPerm(p, PermGroup.ADMIN)) {
						Player tp = Bukkit.getPlayer(args[0]);
						if (tp != null) {
							User t = new User(tp);
							if (t.getAllowFlight()) {
								t.setFly(false);
								t.sendMessage(PREFIX + "Fly mode has been " + GOLD + "disabled" + YELLOW + " by " + GOLD + p.getName() + YELLOW + "!");
								p.sendMessage(PREFIX + "You have " + GOLD + "disabled" + YELLOW + " fly mode for " + GOLD + t.getName() + YELLOW + "!");
							} else {
								t.setFly(true);
								t.sendMessage(PREFIX + "Fly mode has been " + GOLD + "enabled" + YELLOW + " by " + GOLD + p.getName() + YELLOW + "!");
								p.sendMessage(PREFIX + "You have " + GOLD + "enabled" + YELLOW + " fly mode for " + GOLD + t.getName() + YELLOW + "!");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/fly <player>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/fly");
				}
			}
		}
		return false;
	}
}
