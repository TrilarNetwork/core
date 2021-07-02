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

public class Clearchat implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "ClearChat" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearchat")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						for (int i = 0; i < 200; i++) {
							p.sendMessage(" ");
						}
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("public")) {
							for (int i = 0; i < 200; i++) {
								Bukkit.broadcastMessage(" ");
							}
							Bukkit.broadcastMessage(PREFIX + "Chat has been cleared by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
						} else {
							Player x2 = Bukkit.getPlayerExact(args[0]);
							User t = new User(x2);
							if (t.isOnline()) {
								for (int i = 0; i < 200; i++) {
									t.sendMessage(" ");
								}
								t.sendMessage(PREFIX + "Your chat has been cleared by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
							} else {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, t.getName());
							}
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE,  "/clearchat [player|public]");
					} 
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/clearchat");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
