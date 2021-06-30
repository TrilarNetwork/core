package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.AutoB;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class Maintenance implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Maintenance" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
 	private static final BukkitScheduler bs = Bukkit.getScheduler();
 	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("maintenance")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("on")) {
							if (!hasMaintenance()) {
								Main.maintenance = true;
								Bukkit.broadcastMessage(ChatColor.RED + "Server is going in maintenance mode in one minute!");
								bs.scheduleSyncDelayedTask(Main.p, () -> {
									if (hasMaintenance()) {
										Bukkit.broadcastMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Server is going in maintenance mode!");
										initiateMaintenance();
									}
								}, 60*20);
							} else {
								p.sendMessage(PREFIX + "Maintenance mode has already been enabled!");
								
							}
						} else if (args[0].equalsIgnoreCase("off")) {
							if (hasMaintenance()) {
								disableMaintenance();
								Bukkit.broadcastMessage(ChatColor.RED + "Maintenance Mode is no longer active or has been canceled.");
							} else {
								p.sendMessage(PREFIX + "Maintenance is already disabled!");
							}
						} else if (args[0].equalsIgnoreCase("cancel")) {
							disableMaintenance();
							Bukkit.broadcastMessage(PREFIX + "Maintenance has been cancelled!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/maintenance <on|off>");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/maintenance <on|off>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/maintenance");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
	
	public static void initiateMaintenance() {
		if (hasMaintenance()) {
			for (Player ap : Bukkit.getOnlinePlayers()) {
				if (!Perm.hasPerm(ap.getName(), PermGroup.TRIAL_HELPER)) {
					ap.kickPlayer(ChatColor.GOLD + "-=[ Eliarant ]=- \nYou have been kicked due to the server going in maintenance mode.");
					Bukkit.broadcastMessage(Main.PREFIX + "Maintenance mode has been activated!");
				}
			}
		}
	}
	
	private void disableMaintenance() {
		Main.maintenance = false;
	}
	
	public static boolean hasMaintenance() {
		return Main.maintenance;
	}
	
	public static void cancel() {
		boolean b = AutoB.isBroadcasting();
		bs.cancelTasks(Main.p);
		if (b) AutoB.enable();
	}
}