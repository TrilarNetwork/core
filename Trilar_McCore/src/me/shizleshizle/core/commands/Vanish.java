package me.shizleshizle.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.commands.cmdutils.VanishUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Vanish implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Vanish" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("vanish")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						if (p.isVanished()) {
							p.setVanished(false);
							p.sendMessage(prefix + "You have been revealed!");
						} else {
							p.setVanished(true);
							p.sendMessage(prefix + "You have been vanished!");
						}
					} else if (args.length == 1) {
						User t = new User(Bukkit.getPlayer(args[0]));
						if (!t.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						} else {
							if (t.isVanished()) {
								t.setVanished(false);
								t.sendMessage(prefix + "You have been revealed!");
								p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has been revealed!");
							} else {
								t.setVanished(true);
								t.sendMessage(prefix + "You have been vanished!");
								p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has been vanished!");
							}
						}
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("add")) {
							User t = new User(Bukkit.getPlayer(args[0]));
							if (!t.isOnline()) {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
							} else {
								if (VanishUtils.canSee(t)) {
									p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " can already see vanished players!");
								} else {
									VanishUtils.addSee(t);
									p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " can now see vanished players!");
								}
							}
						} else if (args[0].equalsIgnoreCase("remove")) {
							User t = new User(Bukkit.getPlayer(args[0]));
							if (!t.isOnline()) {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
							} else {
								if (VanishUtils.canSee(t)) {
									VanishUtils.removeSee(t);
									p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " can no longer see vanished players!");
								} else {
									p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " can't see vanished players!");
								}
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/vanish <add/remove> <player>");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/vanish <player>");
					}
				}
			}
		}
		return false;
	}
}