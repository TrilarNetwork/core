package me.shizleshizle.core.commands.homes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Sethome implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Homes" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sethome")) {
			if (!Main.isLobby()) {
				if (sender instanceof Player) {
					Player pl = (Player) sender;
					User p = new User(pl);
					if (Perm.hasPerm(p, PermGroup.MEMBER)) {
						if (args.length == 0) {
							if (HomeUtils.canSetHome(p) || Perm.hasPerm(p, PermGroup.HELPER)) {
								if (HomeUtils.hasHome(p.getName(), "home")) {
									p.sendMessage(PREFIX + "You have already set home " + ChatColor.GOLD + "home" + ChatColor.YELLOW + "!");
								} else {
									HomeUtils.setHome(p.getName(), "home", p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 
											p.getLocation().getYaw(), p.getLocation().getPitch(), p.getWorld().getName());
									p.sendMessage(PREFIX + "You have set home " + ChatColor.GOLD + "home" + ChatColor.YELLOW + "!");
								}
							} else {
								p.sendMessage(PREFIX + "You cannot set a new home as you have reached your limit!");
							}
						} else if (args.length == 1) {
							if (HomeUtils.canSetHome(p) || Perm.hasPerm(p, PermGroup.HELPER)) {
								if (HomeUtils.hasHome(p.getName(), args[0])) {
									p.sendMessage(PREFIX + "You have already set home " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
								} else {
									HomeUtils.setHome(p.getName(), args[0], p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 
											p.getLocation().getYaw(), p.getLocation().getPitch(), p.getWorld().getName());
									p.sendMessage(PREFIX + "You have set home " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
								}
							} else {
								p.sendMessage(PREFIX + "You cannot set a new home as you have reached your limit!");
							}
						} else if (args.length == 2) {
							if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
								if (HomeUtils.hasHome(args[1], args[0])) {
									p.sendMessage(PREFIX + "Player " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " already has home "
											+ ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
								} else {
									HomeUtils.setHome(args[1], args[0], p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 
											p.getLocation().getYaw(), p.getLocation().getPitch(), p.getWorld().getName());
									p.sendMessage(PREFIX + "You have set home " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + " for player "
											+ ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
									if (Bukkit.getPlayerExact(args[1]) != null) {
										Player t = Bukkit.getPlayerExact(args[1]);
										t.sendMessage(PREFIX + "Player " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has set home "
										+ ChatColor.GOLD + args[0] + ChatColor.YELLOW + " for you!");
									}
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/sethome <name> <player>");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/sethome <name> [player]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/sethome");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "sethome");
			}
		}
		return false;
	}
}
