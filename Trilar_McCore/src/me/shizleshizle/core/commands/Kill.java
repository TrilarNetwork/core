package me.shizleshizle.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import net.md_5.bungee.api.ChatColor;

public class Kill implements CommandExecutor {
	private String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Kill" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kill")) {
			if (sender instanceof Player) {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
					if (args.length == 1) {
						Player t = Bukkit.getPlayer(args[0]);
						if (t.isOnline()) {
							t.setHealth(0);
							t.sendMessage(prefix + "You have been killed!");
							p.sendMessage(prefix + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has been killed!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/kill <player>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/kill");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
