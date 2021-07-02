package me.shizleshizle.core.commands;

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

public class List implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "List" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("list")) {
			if (!(sender instanceof Player)) {
				String s;
				int amount = 0;
				StringBuilder sb = new StringBuilder();
				for (Player ap : Bukkit.getOnlinePlayers()) {
					User u = new User(ap);
					if (!u.isVanished()) {
						sb.append(u.getDisplayName()).append(ChatColor.YELLOW).append(", ");
						amount++;
					}
				}
				s = sb.substring(0, sb.length() - 2);
				if (amount == 1) {
					sender.sendMessage(PREFIX + "There is " + ChatColor.GOLD + amount + ChatColor.YELLOW + " player online.");
					sender.sendMessage(s + ChatColor.YELLOW + ".");
				} else if (amount > 1) {
					sender.sendMessage(PREFIX + "There are a total of " + ChatColor.GOLD + amount + ChatColor.YELLOW + " players online.");
					sender.sendMessage(s + ChatColor.YELLOW + ".");
				} else {
					sender.sendMessage(PREFIX + "There are no players online.");
				}
			} else {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					String s;
					int amount = 0;
					StringBuilder sb = new StringBuilder();
					for (Player ap : Bukkit.getOnlinePlayers()) {
						User u = new User(ap);
						if (!u.isVanished()) {
							sb.append(u.getDisplayName()).append(ChatColor.YELLOW).append(", ");
							amount++;
						}
					}
					s = sb.substring(0, sb.length() - 2);
					if (amount == 1) {
						sender.sendMessage(PREFIX + "There is " + ChatColor.GOLD + amount + ChatColor.YELLOW + " player online.");
						sender.sendMessage(s + ChatColor.YELLOW + ".");
					} else if (amount > 1) {
						sender.sendMessage(PREFIX + "There are a total of " + ChatColor.GOLD + amount + ChatColor.YELLOW
								+ " players online.");
						sender.sendMessage(s + ChatColor.YELLOW + ".");
					} else {
						sender.sendMessage(PREFIX + "There are no players online.");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/list");
				}
			}
		}
		return false;
	}
}