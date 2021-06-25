package me.shizleshizle.core.commands;

import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Broadcast" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("broadcast")) {
			if (Perm.hasPerm(sender.getName(), PermGroup.ADMIN)) {
				if (args.length != 0) {
					if (args[0].equalsIgnoreCase("nopref")) {
						StringBuilder sb = new StringBuilder();
						for (int i = 1; i < args.length; i++) {
							sb.append(args[i]).append(" ");
						}
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', sb.toString().trim()));
					} else {
						StringBuilder sb = new StringBuilder();
						for (String arg : args) {
							sb.append(arg).append(" ");
						}
						Bukkit.broadcastMessage(PREFIX + ChatColor.translateAlternateColorCodes('&', sb.toString().trim()));
					}
				} else {
					ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/broadcast [nopref] <message>");
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/broadcast");
			}
		}
		return false;
	}
	
	public static void broadcastToStaff(String msg) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (Perm.hasPerm(p.getName(), PermGroup.HELPER)) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
			}
		}
	}
}