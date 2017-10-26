package me.shizleshizle.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Feed implements CommandExecutor {
	private String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Feed" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("feed")) {
			if (sender instanceof Player) {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.setFoodLevel(20);
						p.sendMessage(prefix + "You have been fed!");
					} else if (args.length == 1) {
						if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
							Player t2 = Bukkit.getPlayer(args[0]);
							User t = new User(t2);
							t.setFoodLevel(20);
							t.sendMessage(prefix + "You have been fed!");
							p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has been fed");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/feed");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/feed");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			}
		}
		return false;
	}
}