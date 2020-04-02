package me.shizleshizle.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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
import net.md_5.bungee.api.ChatColor;

public class Pay implements CommandExecutor {
	private final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Pay" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pay")) {
			if (!Main.isLobby()) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(PREFIX + "You must be a player to perform this command!");
				} else {
					Player x = (Player) sender;
					User p = new User(x);
					if (Perm.hasPerm(p, PermGroup.MEMBER)) {
						if (args.length != 2) {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/pay <player> <amount>");
						} else {
							User t = new User(Bukkit.getPlayerExact(args[0]));
							OfflinePlayer op = t.getUser();
							OfflinePlayer payer = p.getUser();
							int amnt = 0;
							try {
								amnt = Integer.parseInt(args[1]);
							} catch (NumberFormatException e) {
								p.sendMessage(PREFIX + "You must enter a number!");
							}
							double bal = Main.econ.getBalance(payer) - amnt;
							if (bal < 0) {
								Main.econ.withdrawPlayer(payer, Main.econ.getBalance(payer));
							} else {
								Main.econ.withdrawPlayer(payer, amnt);
							}
							Main.econ.depositPlayer(op, amnt);
							p.sendMessage(PREFIX + "You have payed " + ChatColor.GOLD + op.getName() + " " + amnt + ChatColor.YELLOW + " " + Main.econ.currencyNamePlural() + "!");
							if (t.isOnline()) {
								t.sendMessage(PREFIX + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has payed you " + ChatColor.GOLD + amnt + " "
										+ ChatColor.YELLOW + Main.econ.currencyNamePlural() + "!");
							}
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/pay");
					}
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "pay");
			}
		}
		return false;
	}
}