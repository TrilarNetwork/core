package me.shizleshizle.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Gamemode implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "GameMode" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gamemode")) {
			if (sender instanceof Player) {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/gamemode <0|1|2|3|>");
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage(prefix + "Your GameMode has been updated to " + ChatColor.GOLD + "Survival"
									+ ChatColor.YELLOW + "!");
						} else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage(prefix + "Your GameMode has been updated to " + ChatColor.GOLD + "Creative"
									+ ChatColor.YELLOW + "!");
						} else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
							p.setGameMode(GameMode.ADVENTURE);
							p.sendMessage(prefix + "Your GameMode has been updated to " + ChatColor.GOLD + "Adventure"
									+ ChatColor.YELLOW + "!");
						} else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectate")) {
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage(prefix + "Your GameMode has been updated to " + ChatColor.GOLD + "Spectator"
									+ ChatColor.YELLOW + "!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/gamemode <gamemode>");
						}
					} else if (args.length == 2) {
						Player t = Bukkit.getPlayer(args[1]);
						if (t != null) {
							if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("survival")) {
								t.setGameMode(GameMode.SURVIVAL);
								t.sendMessage(prefix + "Your GameMode has been updated to " + ChatColor.GOLD
										+ "Survival" + ChatColor.YELLOW + "!");
							} else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("creative")) {
								t.setGameMode(GameMode.CREATIVE);
								t.sendMessage(prefix + "Your GameMode has been updated to " + ChatColor.GOLD
										+ "Creative" + ChatColor.YELLOW + "!");
							} else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("adventure")) {
								t.setGameMode(GameMode.ADVENTURE);
								t.sendMessage(prefix + "Your GameMode has been updated to " + ChatColor.GOLD
										+ "Adventure" + ChatColor.YELLOW + "!");
							} else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spectate")) {
								t.setGameMode(GameMode.SPECTATOR);
								t.sendMessage(prefix + "Your GameMode has been updated to " + ChatColor.GOLD
										+ "Spectator" + ChatColor.YELLOW + "!");
							} else {
								ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE,
										"/gamemode <gamemode> <player>");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/gamemode <gamemode> [player]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/gamemode");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		} else if (cmd.getName().equalsIgnoreCase("gms")) {
			if (sender instanceof Player) {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(prefix + "Your gamemode has been updated to " + ChatColor.GOLD + "Survival"
								+ ChatColor.YELLOW + "!");
					} else if (args.length == 1) {
						Player t = Bukkit.getPlayer(args[0]);
						if (t != null) {
							t.setGameMode(GameMode.SURVIVAL);
							t.sendMessage(prefix + "Your gamemode has been updated to " + ChatColor.GOLD + "Survival"
									+ ChatColor.YELLOW + "!");
							p.sendMessage(prefix + "You have changed " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW
									+ "'s gamemode to " + ChatColor.GOLD + "Survival" + ChatColor.YELLOW + "!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/gms");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/gms");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		} else if (cmd.getName().equalsIgnoreCase("gmc")) {
			if (sender instanceof Player) {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(prefix + "Your gamemode has been updated to " + ChatColor.GOLD + "Creative"
								+ ChatColor.YELLOW + "!");
					} else if (args.length == 1) {
						Player t = Bukkit.getPlayer(args[0]);
						if (t != null) {
							t.setGameMode(GameMode.CREATIVE);
							t.sendMessage(prefix + "Your gamemode has been updated to " + ChatColor.GOLD + "Creative"
									+ ChatColor.YELLOW + "!");
							p.sendMessage(prefix + "You have changed " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW
									+ "'s gamemode to " + ChatColor.GOLD + "Creative" + ChatColor.YELLOW + "!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/gmc");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/gmc");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		} else if (cmd.getName().equalsIgnoreCase("gma")) {
			if (sender instanceof Player) {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(prefix + "Your gamemode has been updated to " + ChatColor.GOLD + "Adventure"
								+ ChatColor.YELLOW + "!");
					} else if (args.length == 1) {
						Player t = Bukkit.getPlayer(args[0]);
						if (t != null) {
							t.setGameMode(GameMode.ADVENTURE);
							t.sendMessage(prefix + "Your gamemode has been updated to " + ChatColor.GOLD + "Adventure"
									+ ChatColor.YELLOW + "!");
							p.sendMessage(prefix + "You have changed " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW
									+ "'s gamemode to " + ChatColor.GOLD + "Adventure" + ChatColor.YELLOW + "!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/gma");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/gma");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		} else if (cmd.getName().equalsIgnoreCase("gmsp")) {
			if (sender instanceof Player) {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(prefix + "Your gamemode has been updated to " + ChatColor.GOLD + "Spectator"
								+ ChatColor.YELLOW + "!");
					} else if (args.length == 1) {
						Player t = Bukkit.getPlayer(args[0]);
						if (t != null) {
							t.setGameMode(GameMode.SPECTATOR);
							t.sendMessage(prefix + "Your gamemode has been updated to " + ChatColor.GOLD + "Spectator"
									+ ChatColor.YELLOW + "!");
							p.sendMessage(prefix + "You have changed " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW
									+ "'s gamemode to " + ChatColor.GOLD + "Spectator" + ChatColor.YELLOW + "!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/gmsp");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/gmsp");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
