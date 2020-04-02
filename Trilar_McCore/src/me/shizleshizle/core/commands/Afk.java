package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Afk implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Afk" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("afk")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						if (p.isAfk()) {
							p.setAfk(false);
						} else {
							p.setAfk(true);
						}
					} else if (args.length == 1) {
						Player x2 = Bukkit.getPlayerExact(args[0]);
						if (!x2.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, x2.getName());
						} else {
							User t = new User(x2);
							if(t.isAfk()){
								t.setAfk(false);
							} else {
								t.setAfk(true);
							} 
						} 
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/afk [player]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/afk");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
