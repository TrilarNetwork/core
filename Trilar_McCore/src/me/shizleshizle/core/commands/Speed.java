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

public class Speed implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Speed" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	public enum S {
		WALK, FLY
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("speed")) {
			if (!Main.isLobby()) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(prefix + "You must be a player to perform this command!");
				} else {
					Player x = (Player) sender;
					User p = new User(x);
					if (Perm.hasPerm(p, PermGroup.BUILDER)) {
						if (args.length == 0) {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/speed <speed> [type] [player]");
						} else if (args.length == 1) {
							int speed = 0;
							try {
								speed = Integer.parseInt(args[0]);
							} catch (NumberFormatException e) {
								p.sendMessage(prefix + "You must enter a number!");
							}
							if (speed > 10 || speed < 0) {
								p.sendMessage(prefix + "Speed can only be between 0 and 10!");
							} else {
								if (p.isFlying()) {
									setSpeed(p, S.FLY, speed, false);
								} else {
									setSpeed(p, S.WALK, speed, false);
								}
							}
						} else if (args.length == 2) {
							int speed = 0;
							try {
								speed = Integer.parseInt(args[0]);
							} catch (NumberFormatException e) {
								p.sendMessage(prefix + "You must enter a number!");
							}
							if (speed > 10 || speed < 0) {
								p.sendMessage(prefix + "Speed can only be between 0 and 10!");
							} else {
								if (args[1].equalsIgnoreCase("fly") || args[1].equalsIgnoreCase("flying")) {
									setSpeed(p, S.FLY, speed, false);
								} else if (args[1].equalsIgnoreCase("walk") || args[1].equalsIgnoreCase("walking")) {
									setSpeed(p, S.WALK, speed, false);
								} else {
									p.sendMessage(prefix + "Invalid type! Types: Fly/Flying or Walk/Walking.");
								}
							}
						} else if (args.length == 3) {
							if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
								User t = new User(Bukkit.getPlayer(args[2]));
								if (!t.isOnline()) {
									ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[2]);
								} else {
									int speed = 0;
									try {
										speed = Integer.parseInt(args[0]);
									} catch (NumberFormatException e) {
										p.sendMessage(prefix + "You must enter a number!");
									}
									if (speed > 10 || speed < 0) {
										p.sendMessage(prefix + "Speed can only be between 0 and 10!");
									} else {
										if (args[1].equalsIgnoreCase("fly") || args[1].equalsIgnoreCase("flying")) {
											setSpeed(t, S.FLY, speed, false);
										} else if (args[1].equalsIgnoreCase("walk") || args[1].equalsIgnoreCase("walking")) {
											setSpeed(t, S.WALK, speed, false);
										} else {
											p.sendMessage(prefix + "Invalid type! Types: Fly/Flying or Walk/Walking.");
										}
									}
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/speed <player>");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/speed <speed> [type] [player]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/speed");
					}
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "speed");
			}
		}
		return false;
	}

	public static void setSpeed(User p, S s, int speed, boolean silent) {
		if (s.equals(S.FLY)) {
			switch (speed) {
				case 0:
					p.setFlySpeed(0F);
					break;
				case 1:
					p.setFlySpeed(0.1F);
					break;
				case 2:
					p.setFlySpeed(0.2F);
					break;
				case 3:
					p.setFlySpeed(0.3F);
					break;
				case 4:
					p.setFlySpeed(0.4F);
					break;
				case 5:
					p.setFlySpeed(0.5F);
					break;
				case 6:
					p.setFlySpeed(0.6F);
					break;
				case 7:
					p.setFlySpeed(0.7F);
					break;
				case 8:
					p.setFlySpeed(0.8F);
					break;
				case 9:
					p.setFlySpeed(0.9F);
					break;
				case 10:
					p.setFlySpeed(1F);
					break;
			}
			if (!silent) {
				p.sendMessage(prefix + "Your " + ChatColor.GOLD + "Flying" + ChatColor.YELLOW + " speed has been set to " + ChatColor.GOLD + speed
						+ ChatColor.YELLOW + "!");
			}
		} else {
			switch (speed) {
				case 0:
					p.setWalkSpeed(0F);
					break;
				case 1:
					p.setWalkSpeed(0.1F);
					break;
				case 2:
					p.setWalkSpeed(0.2F);
					break;
				case 3:
					p.setWalkSpeed(0.3F);
					break;
				case 4:
					p.setWalkSpeed(0.4F);
					break;
				case 5:
					p.setWalkSpeed(0.5F);
					break;
				case 6:
					p.setWalkSpeed(0.6F);
					break;
				case 7:
					p.setWalkSpeed(0.7F);
					break;
				case 8:
					p.setWalkSpeed(0.8F);
					break;
				case 9:
					p.setWalkSpeed(0.9F);
					break;
				case 10:
					p.setWalkSpeed(1F);
					break;
			}
			if (!silent) {
				p.sendMessage(prefix + "Your " + ChatColor.GOLD + "Walking" + ChatColor.YELLOW + " speed has been set to " + ChatColor.GOLD
						+ speed + ChatColor.YELLOW + "!");
			}
		}
	}
}