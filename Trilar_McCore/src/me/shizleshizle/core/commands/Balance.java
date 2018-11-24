package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Balance implements CommandExecutor {
	private String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Feed" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("balance")) {
			if (!Main.isLobby()) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(prefix + "You must be a player to perform this command!");
				} else {
					Player x = (Player) sender;
					User p = new User(x);
					String valuta = Main.econ.currencyNamePlural();
					if (args.length == 0) {
						OfflinePlayer op = p.getUser();
						double b = Main.econ.getBalance(op);
						p.sendMessage(prefix + "Your balance is " + ChatColor.GOLD + b + ChatColor.YELLOW + " " + valuta + "!");
					} else if (args.length == 3) {
						if (Perm.hasPerm(p, PermGroup.ADMIN)) {
							User p2 = new User(Bukkit.getPlayerExact(args[0]));
							OfflinePlayer op = p2.getUser();
							double b = Main.econ.getBalance(op);
							int amnt = 0;
							if (args[0].equalsIgnoreCase("set")) {
								try {
									amnt = Integer.parseInt(args[2]);
								} catch (NumberFormatException e) {
									p.sendMessage(prefix + "You must enter a number!");
								}
								setBalance(op, amnt);
								if (op.getName().equals(p.getName())) {
									p.sendMessage(prefix + "Your balance has been set to " + ChatColor.GOLD + amnt + ChatColor.YELLOW + " " + valuta + " by " + ChatColor.GOLD
											+ p.getName() + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(prefix + "Player " + ChatColor.GOLD + op.getName() + ChatColor.YELLOW + "'s balance has been set to " + ChatColor.GOLD 
											+ amnt + ChatColor.YELLOW + "!");
									if (op.isOnline()) {
										Player t = op.getPlayer();
										t.sendMessage(prefix + "Your balance has been set to " + ChatColor.GOLD + amnt + ChatColor.YELLOW + " " + valuta + " by " + ChatColor.GOLD
												+ p.getName() + ChatColor.YELLOW + "!");
									}
								}
								return true;
							} else if (args[0].equalsIgnoreCase("give")) {
								try {
									amnt = Integer.parseInt(args[2]);
								} catch (NumberFormatException e) {
									p.sendMessage(prefix + "You must enter a number!");
								}
								Main.econ.depositPlayer(op, amnt);
								if (op.getName().equals(p.getName())) {
									p.sendMessage(prefix + "Your balance has been incremented by " + ChatColor.GOLD + amnt + ChatColor.YELLOW + " " + valuta + " by " 
											+ ChatColor.GOLD
											+ p.getName() + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(prefix + "You have given " + ChatColor.GOLD + amnt + ChatColor.YELLOW + " " + valuta + " to " + ChatColor.GOLD
											+ op.getName() + ChatColor.YELLOW + "!");
									if (op.isOnline()) {
										Player t = op.getPlayer();
										t.sendMessage(prefix + "Your balance has been incremented by " + ChatColor.GOLD + amnt + ChatColor.YELLOW + " " + valuta + " by "
												+ ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
									}
								}
							} else if (args[0].equalsIgnoreCase("take")) {
								try {
									amnt = Integer.parseInt(args[2]);
								} catch (NumberFormatException e) {
									p.sendMessage(prefix + "You must enter a number!");
								}
								int fin;
								if (b - amnt <= 0) {
									fin = 0;
								} else {
									fin = (int) b - amnt;
								}
								Main.econ.withdrawPlayer(op, b);
								Main.econ.depositPlayer(op, fin);
								if (op.getName().equals(p.getName())) {
									p.sendMessage(prefix + "Your balance has been decreased by " + ChatColor.GOLD + amnt + ChatColor.YELLOW + " "
											+ valuta + " by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(prefix + "You have taken " + ChatColor.GOLD + amnt + ChatColor.YELLOW + " " + valuta + " to "
											+ ChatColor.GOLD + op.getName() + ChatColor.YELLOW + "!");
									if (op.isOnline()) {
										Player t = op.getPlayer();
										t.sendMessage(prefix + "Your balance has been decreased by " + ChatColor.GOLD + amnt + ChatColor.YELLOW
												+ " " + valuta + " by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
									}
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE,	"/balance <give|take|set> <player> <amount>");
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/balance <set|give|take> <player> <amount>");
						}
					} else if (args.length == 1) {
						if (Perm.hasPerm(p, PermGroup.ADMIN)) {
							User p2 = new User(Bukkit.getPlayerExact(args[0]));
							OfflinePlayer op = p2.getUser();
							double b = Main.econ.getBalance(op);
							p.sendMessage(prefix + "Player " + ChatColor.GOLD + op.getName() + ChatColor.YELLOW + " has a balance of " + ChatColor.GOLD
									+ b + ChatColor.YELLOW + " " + valuta + "!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/balance");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/balance <player>");
					}
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "balance");
			}
		}
		return false;
	}

	public static boolean canBuy(Player p, int price) {
		double b = Main.econ.getBalance(p);
		return !(b < price);
	}

	public static void setBalance(OfflinePlayer op, int bal) {
		double b = Main.econ.getBalance(op);
		Main.econ.withdrawPlayer(op, b);
		Main.econ.depositPlayer(op, bal);
	}
}