package me.shizleshizle.core.commands.teleportation;

import me.shizleshizle.core.commands.Broadcast;
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

public class Tpall implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Tpall" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpall")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
					if (args.length == 0) {
						Bukkit.broadcastMessage(Broadcast.PREFIX + "Teleporting to all players to " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
						for (Player pl : Bukkit.getOnlinePlayers()) {
							User pla = new User(pl);
							pla.teleport(p.getLocation());
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpall");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tpall");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}