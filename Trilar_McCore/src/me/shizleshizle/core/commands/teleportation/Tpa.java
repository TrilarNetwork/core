package me.shizleshizle.core.commands.teleportation;

import java.util.HashMap;

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

public class Tpa implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Teleportation" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
    public static HashMap<String, String> tpa = new HashMap<>();
    public static boolean toTP = false;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpa")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 1) {
						User t = new User(Bukkit.getPlayer(args[0]));
						if (!t.isOnline()) {
							ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
						} else {
							if (!t.hasTpDisabled()) {
								tpa.put(p.getName(), t.getName());
								p.sendMessage(PREFIX + "Request sent to " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
								t.sendMessage(PREFIX + "You have received a teleport request from " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW
										+ " to teleport to your location! Type " + ChatColor.GOLD + "/tpaccept" + ChatColor.YELLOW + " to accept their request or type " 
										+ ChatColor.GOLD + "/tpdeny" + ChatColor.YELLOW + " to deny their request");
								toTP = true;
							} else {
								p.sendMessage(PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has disabled teleportation!");
							}
						}
					} else if (args.length >= 2) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpa <player>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tpa");
				}
			}
		}
		return false;
	}
}