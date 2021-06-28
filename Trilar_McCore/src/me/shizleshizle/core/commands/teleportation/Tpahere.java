package me.shizleshizle.core.commands.teleportation;

import me.shizleshizle.core.Main;
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

public class Tpahere implements CommandExecutor {
    public static boolean toTP = false;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpahere")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Tp.PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length != 1) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpahere <player>");
					} else {
						User t = new User(Bukkit.getPlayer(args[0]));
						if (!t.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						} else {
							if (t.hasTpDisabled()) {
								p.sendMessage(Tp.PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has teleportation disabled!");
							} else {
								p.sendMessage(Tp.PREFIX + "Request sent to " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
								t.sendMessage(Tp.PREFIX + "You have received a teleport request from " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW
										+ " to teleport to their location! Type " + ChatColor.GOLD + "/tpaccept" + ChatColor.YELLOW + " to accept their request or type " 
										+ ChatColor.GOLD + "/tpdeny" + ChatColor.YELLOW + " to deny their request");
								Main.tpahere.put(p.getName(), t.getName());
								toTP = true;
							}
						}
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tpahere");
				}
			}
		}
		return false;
	}
}