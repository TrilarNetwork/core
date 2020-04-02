package me.shizleshizle.core.commands;

import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

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

public class Heal implements CommandExecutor {
	private final String PREFIX = YELLOW.toString() + BOLD + "Heal" + GOLD + " >> " + YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("heal")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HEAD_BUILDER)) {
					if (args.length == 0) {
						p.heal(20, 20);
						p.sendMessage(PREFIX + "You have been healed!");
					} else if (args.length == 1) {
						if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
							Player t2 = Bukkit.getPlayer(args[0]);
							User t = new User(t2);
							if (!t.isOnline()) {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
							} else {
								t.heal(20, 20);
								if (t.getName().equals(p.getName())) {
									p.sendMessage(PREFIX + "You have been healed!");
								} else {
									t.sendMessage(PREFIX + "Your healed by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
									p.sendMessage(PREFIX + "You have healed " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
								}
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/heal <player>");
						}
					} else if (args.length == 2) {
						if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
							Player t2 = Bukkit.getPlayer(args[0]);
							User t = new User(t2);
							if (!t.isOnline()) {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
							} else {
								int health = 0;
								try {
									health = Integer.parseInt(args[1]);
								} catch (NumberFormatException e) {
									p.sendMessage(PREFIX + "You must enter a number!");
								}
								if (health < 0 || health > Main.maxHealth) {
									p.sendMessage(PREFIX + "Incorrect value! Value must be between " + ChatColor.GOLD + "0" + ChatColor.YELLOW + " and " + ChatColor.GOLD
											+ Main.maxHealth + ChatColor.YELLOW + "!");
								} else {
									t.heal(health, 20);
									if (t.getName().equals(p.getName())) {
										t.sendMessage(PREFIX + "Your health has been set to " + ChatColor.GOLD + health + ChatColor.YELLOW + "! ");
									} else {
										t.sendMessage(PREFIX + "Your health has been set to " + ChatColor.GOLD + health + ChatColor.YELLOW + " by " + ChatColor.GOLD
												+ p.getName() + ChatColor.YELLOW
												+ "!");
										p.sendMessage(PREFIX + "You set " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "'s health to " + ChatColor.GOLD
												+ health + ChatColor.YELLOW + "!");
									}
								}
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/heal <player> [amount]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/heal <player> [amount]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/heal");
				}
			}
		}
		return false;
	}
}