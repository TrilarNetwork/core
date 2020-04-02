package me.shizleshizle.core.commands.warps;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.commands.cmdutils.WarpUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Warp implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Warps" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("warp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
					if (args.length == 0) {
						p.sendMessage(PREFIX + "These are the current warps: ");
						p.sendMessage(WarpUtils.listWarps());
					} else if (args.length == 1) {
						p.warp(args[0]);
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/warp <name> (leave empty for list)");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/removewarp");
				}
			}
		}
		return false;
	}
}
