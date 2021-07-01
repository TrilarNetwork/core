package me.shizleshizle.core.commands.homes;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.cmdutils.HomeUtils;
import me.shizleshizle.core.commands.teleportation.Tp;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Homes" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("home")) {
			if (!Main.isLobby()) {
				if (sender instanceof Player) {
					Player pl = (Player) sender;
					User p = new User(pl);
					if (Perm.hasPerm(p, PermGroup.MEMBER)) {
						if (args.length == 0) {
							if (HomeUtils.hasHome(p.getName(), "home")) {
								HomeUtils.toHome(p, "home");
							} else {
								p.sendMessage(PREFIX + "Please specify a name or set a home!");
							}
						} else if (args.length == 1) {
							if (args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("list")) {
								p.sendMessage(PREFIX + "These are your homes, you have " + ChatColor.GOLD + HomeUtils.getHomes(p.getName()) + ChatColor.YELLOW
										+ " home(s):");
								p.sendMessage(HomeUtils.listHomes(p.getName()));
							} else {
								if (HomeUtils.hasHome(p.getName(), args[0])) {
									HomeUtils.toHome(p, args[0]);
								} else {
									p.sendMessage(PREFIX + "You have not set home " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
								}
							}
						} else if (args.length == 2) {
							if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
								if (args[0].equalsIgnoreCase("info")) {
									int has = HomeUtils.getHomes(args[1]);
									int max = HomeUtils.getMaxHomes(args[1]);
									p.sendMessage(PREFIX + "Player " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " has " + ChatColor.GOLD + has
											+ ChatColor.YELLOW + " homes, and can set a total of " + ChatColor.GOLD + max + ChatColor.YELLOW + " homes!");
								} else if (args[0].equalsIgnoreCase("list")) {
									p.sendMessage(PREFIX + "These are " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "'s homes, they have "
											+ ChatColor.GOLD + HomeUtils.getHomes(args[1]) + ChatColor.YELLOW + " homes:");
									p.sendMessage(HomeUtils.listHomes(args[1]));
								} else {
									if (HomeUtils.hasHome(args[1], args[0])) {
										HomeUtils.adminToPlayerHome(p, args[1], args[0]);
									} else {
										p.sendMessage(PREFIX + "Player " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " does not have home "
												+ ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
										p.sendMessage(PREFIX + "Usage: /home <name|info|list> [player]");
									}
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/home <name> <player>");
							}
						} else if (args.length == 3) {
							if (Perm.hasPerm(p, PermGroup.ADMIN)) {
								if (args[0].equalsIgnoreCase("add")) {
									int h = 0;
									try {
										h = Integer.parseInt(args[2]);
									} catch (NumberFormatException e) {
										p.sendMessage(PREFIX + "You must enter a number! (/home <add|remove|set> <playername> <amount>)");
									}
									int fin = HomeUtils.getMaxHomes(args[1]) + h;
									HomeUtils.setMaxHomes(args[1], fin);
									p.sendMessage(PREFIX + "You have added " + ChatColor.GOLD + h + ChatColor.YELLOW + " homes to "
											+ ChatColor.GOLD + args[1] + ChatColor.YELLOW + " making a total of " + ChatColor.GOLD + fin + ChatColor.YELLOW + "!");
									if (Bukkit.getPlayerExact(args[1]) != null) {
										Player t = Bukkit.getPlayerExact(args[1]);
										if (t == null) {
											p.sendMessage(Tp.PREFIX + "Player is not online!");
										} else {
											t.sendMessage(PREFIX + "You have received " + ChatColor.GOLD + h + ChatColor.YELLOW + " homes, making a total of "
													+ ChatColor.GOLD + fin + ChatColor.YELLOW + "!");
											t.sendMessage(PREFIX + "You have received these homes from " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
										}
									}
								} else if (args[0].equalsIgnoreCase("remove")) {
									int h = 0;
									try {
										h = Integer.parseInt(args[2]);
									} catch (NumberFormatException e) {
										p.sendMessage(PREFIX + "You must enter a number! (/home <add|remove|set> <playername> <amount>");
									}
									int fin = HomeUtils.getMaxHomes(args[1]) - h;
									if (fin < 0) {
										fin = 0;
									}
									HomeUtils.setMaxHomes(args[1], fin);
									p.sendMessage(PREFIX + "You have removed " + ChatColor.GOLD + h + ChatColor.YELLOW + " homes of " + ChatColor.GOLD
											+ args[1] + ChatColor.YELLOW + " making a total of " + ChatColor.GOLD + fin + ChatColor.YELLOW + "!");
									if (Bukkit.getPlayerExact(args[1]) != null) {
										Player t = Bukkit.getPlayerExact(args[1]);
										if (t == null) {
											p.sendMessage(Tp.PREFIX + "Player is not online!");
										} else {
											t.sendMessage(PREFIX + "Your homes have been decreased by " + ChatColor.GOLD + h + ChatColor.YELLOW + " making a total of "
													+ ChatColor.GOLD + fin + ChatColor.YELLOW + "!");
											t.sendMessage(PREFIX + "Your homes have been decreased by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
										}
									}
								} else if (args[0].equalsIgnoreCase("set")) {
									int h = 0;
									try {
										h = Integer.parseInt(args[2]);
									} catch (NumberFormatException e) {
										p.sendMessage(PREFIX + "You must enter a number! (/home <add|remove|set> <playername> <amount>");
									}
									HomeUtils.setMaxHomes(args[1], h);
									p.sendMessage(PREFIX + "You have set maximum homes of " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " to "
											+ ChatColor.GOLD + h + ChatColor.YELLOW + "!");
									if (Bukkit.getPlayerExact(args[1]) != null) {
										Player t = Bukkit.getPlayerExact(args[1]);
										if (t == null) {
											p.sendMessage(Tp.PREFIX + "Player is not online!");
										} else {
											t.sendMessage(PREFIX + "Your homes have been set to " + ChatColor.GOLD + h + ChatColor.YELLOW + " by " + ChatColor.GOLD
													+ p.getName() + ChatColor.YELLOW + "!");
										}
									}
								} else {
									ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/home <add|remove|set|homename> <player> [amount]");
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/home <add|remove|set|homename> <player> [amount]");
							}
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/home");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "home");
			}
		}
		return false;
	}
}
