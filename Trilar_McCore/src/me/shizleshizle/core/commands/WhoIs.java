package me.shizleshizle.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.commands.cmdutils.WhoIsUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class WhoIs implements CommandExecutor {
	public final String PREFIX = ChatColor.YELLOW + "WhoIs" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("whois")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
					if (args.length != 1) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/whois <player>");
					} else {
						OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
						if (!op.isOnline()) {
							p.sendMessage(ChatColor.GOLD + "-=[ " + ChatColor.YELLOW + "WhoIs: " + op.getName() + ChatColor.GOLD + " ]=-");
							p.sendMessage(ChatColor.YELLOW + "Unique ID: " + WhoIsUtils.getOfflineID(op));
							p.sendMessage(ChatColor.YELLOW + "Health: " + WhoIsUtils.getOfflineHealth(op));
							p.sendMessage(ChatColor.YELLOW + "Hunger: " + WhoIsUtils.getOfflineHunger(op));
							p.sendMessage(ChatColor.YELLOW + "Experience: " + WhoIsUtils.getOfflineXP(op));
							p.sendMessage(ChatColor.YELLOW + "IP Address: " + WhoIsUtils.getAddress(op.getName()));
							p.sendMessage(ChatColor.YELLOW + "GameMode: " + WhoIsUtils.getOfflineGameMode(op));
							p.sendMessage(ChatColor.YELLOW + "God mode: " + WhoIsUtils.getOfflineGod(op));
							p.sendMessage(ChatColor.YELLOW + "Frozen: " + WhoIsUtils.getOfflineFrozen(op));
							p.sendMessage(ChatColor.YELLOW + "Tp-Toggle: " + WhoIsUtils.getOfflineTpToggle(op));
							p.sendMessage(ChatColor.YELLOW + "Vanished: " + WhoIsUtils.getOfflineVanished(op));
						} else {
							User t = new User(op.getPlayer());
							p.sendMessage(ChatColor.GOLD + "-=[ " + ChatColor.YELLOW + "WhoIs: " + t.getName() + ChatColor.GOLD + " ]=-");
							p.sendMessage(ChatColor.YELLOW + "Display Name: " + t.getDisplayName());
							p.sendMessage(ChatColor.YELLOW + "Unique ID: " + WhoIsUtils.getID(t));
							p.sendMessage(ChatColor.YELLOW + "Health: " + WhoIsUtils.getHealth(t));
							p.sendMessage(ChatColor.YELLOW + "Hunger: " + WhoIsUtils.getFood(t));
							p.sendMessage(ChatColor.YELLOW + "Experience: " + WhoIsUtils.getXP(t));
							p.sendMessage(ChatColor.YELLOW + "Location: " + WhoIsUtils.getLoc(t));
							p.sendMessage(ChatColor.YELLOW + "IP Address: " + WhoIsUtils.getAddress(t.getName()));
							p.sendMessage(ChatColor.YELLOW + "Speed: " + WhoIsUtils.getSpeed(t));
							p.sendMessage(ChatColor.YELLOW + "GameMode: " + WhoIsUtils.getGameMode(t));
							p.sendMessage(ChatColor.YELLOW + "OP: " + WhoIsUtils.getOp(t));
							p.sendMessage(ChatColor.YELLOW + "God mode: " + WhoIsUtils.getGod(t));
							p.sendMessage(ChatColor.YELLOW + "Fly: " + WhoIsUtils.getFly(t));
							p.sendMessage(ChatColor.YELLOW + "Frozen: " + WhoIsUtils.getFrozen(t));
							p.sendMessage(ChatColor.YELLOW + "Tp-Toggle: " + WhoIsUtils.getTpToggle(t));
							p.sendMessage(ChatColor.YELLOW + "Vanished: " + WhoIsUtils.getVanished(t));
						}
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/whois <player>");
				}
			}
		}
		return false;
	}
}