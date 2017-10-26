package me.shizleshizle.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Lockdown implements CommandExecutor {
 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "lockdown" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
 	public static boolean lockdown = false;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("lockdown")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 0) {
						if (lockdown) {
							lockdown = false;
							Bukkit.broadcastMessage(ChatColor.RED + "Lockdown Mode is no longer active or has been canceled.");
						} else {
							lockdown = true;
							Bukkit.broadcastMessage(ChatColor.RED + "Server is going in Lockdown mode in one minute!");
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.p, () -> {
								Bukkit.broadcastMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Server is going in Lock mode!");
								for (Player ap : Bukkit.getOnlinePlayers()) {
									if (!Perm.hasPerm(ap.getName(), PermGroup.BUILDER)) {
										p.kickUser(ChatColor.GOLD + "-=[ Trilar ]=- \nYou have been kicked due to the server going in Lockdown mode.");
									}
								}
							}, 60*20);
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/lockdown");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/lockdown");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}