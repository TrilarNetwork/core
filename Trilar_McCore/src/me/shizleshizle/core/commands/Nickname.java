package me.shizleshizle.core.commands;

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
import me.shizleshizle.core.utils.NickNameManager;

public class Nickname implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Nicknames" + ChatColor.GOLD + " >> "
			+ ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("nickname")) {
			if (!Main.isLobby()) {
				if (sender instanceof Player) {
					Player pl = (Player) sender;
					User p = new User(pl);
					if (Perm.hasPerm(p, PermGroup.HELPER)) {
						if (args.length == 1) {
							if (args[0].equalsIgnoreCase("off")) {
								if (p.hasNick()) {
									p.removeNick();
									p.sendMessage(prefix + "Your Nickname has been removed!");
								}
							} else {
								String nick = args[0].trim();
								p.setNick(nick);
								nick = ChatColor.translateAlternateColorCodes('&', nick);
								p.sendMessage(prefix + "Your Nickname has been changed to " + nick + ChatColor.YELLOW + "!");
							}
						} else if (args.length == 2) {
							if (args[1].equalsIgnoreCase("off")) {
								Player tp = Bukkit.getPlayer(args[0]);
								if (tp != null) {
									User t = new User(tp);
									if (t.hasNick()) {
										t.removeNick();
										p.sendMessage(prefix + "You have removed " + ChatColor.GOLD + t.getName()
												+ ChatColor.YELLOW + "'s nickname!");
										t.sendMessage(prefix + "Your Nickname has been removed by " + ChatColor.GOLD
												+ p.getName() + ChatColor.YELLOW + "!");
									} else {
										p.sendMessage(prefix + "Player " + ChatColor.GOLD + args[0] + ChatColor.YELLOW
												+ " does not have a nickname!");
									}
								} else {
									ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
								}
							} else if (args[1].equalsIgnoreCase("realname")) {
								if (NickNameManager.getPlayerFromNick(args[0]) != null) {
									p.sendMessage(prefix + "Their real name is " + ChatColor.GOLD + NickNameManager.getPlayerFromNick(args[0] + ChatColor.YELLOW + "!"));
								} else {
									p.sendMessage(prefix + "There is no player with that nickname!");
								}
							} else {
								User t = new User(Bukkit.getPlayerExact(args[0]));
								if (t.isOnline()) {
									String nick = args[1].trim();
									t.setNick(nick);
									p.sendMessage(prefix + "You have set " + ChatColor.GOLD + t.getName()
									+ ChatColor.YELLOW + "'s nickname to " + nick + ChatColor.YELLOW + "!");
									t.sendMessage(prefix + "Your Nickname has been set to " + ChatColor.GOLD + nick + ChatColor.YELLOW + " to " 
											+ ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
								} else {
									ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
								}
							} 
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/nickname <player|off> [player|realname]");
						}
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "nickname");
			}
		}
		return false;
	}
}