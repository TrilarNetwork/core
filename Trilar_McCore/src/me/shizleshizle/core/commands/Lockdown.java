package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.AutoB;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class Lockdown implements CommandExecutor {
 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "lockdown" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
 	private static BukkitScheduler bs = Bukkit.getScheduler();
 	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("lockdown")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("on")) {
							if (!hasLockdown()) {
								Main.lockdown = true;
								Bukkit.broadcastMessage(ChatColor.RED + "Server is going in Lockdown mode in one minute!");
								bs.scheduleSyncDelayedTask(Main.p, () -> {
									if (hasLockdown()) {
										Bukkit.broadcastMessage(ChatColor.RED.toString() + ChatColor.BOLD + "Server is going in Lock mode!");
										initiateLockdown();
									}
								}, 60*20);
							} else {
								p.sendMessage(prefix + "Lockdown has already been enabled!");
								
							}
						} else if (args[0].equalsIgnoreCase("off")) {
							if (hasLockdown()) {
								disableLockdown();
								Bukkit.broadcastMessage(ChatColor.RED + "Lockdown Mode is no longer active or has been canceled.");
							} else {
								p.sendMessage(prefix + "Lockdown is already disabled!");
							}
						} else if (args[0].equalsIgnoreCase("cancel")) {
							disableLockdown();
							Bukkit.broadcastMessage(prefix + "Lockdown has been cancelled!");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/lockdown <on|off>");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/lockdown <on|off>");
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
	
	public static void initiateLockdown() {
		if (hasLockdown()) {
			for (Player ap : Bukkit.getOnlinePlayers()) {
				if (!Perm.hasPerm(ap.getName(), PermGroup.BUILDER)) {
					ap.kickPlayer(ChatColor.GOLD + "-=[ Trilar ]=- \nYou have been kicked due to the server going in Lockdown mode.");
					Bukkit.broadcastMessage(Main.prefix + "Lockdown mode has been activated!");
				}
			}
		}
	}
	
	private void disableLockdown() {
		Main.lockdown = false;
	}
	
	public static boolean hasLockdown() {
		return Main.lockdown;
	}
	
	public static void cancel() {
		boolean b;
		b = AutoB.isBroadcasting();
		bs.cancelTasks(Main.p);
		if (b) AutoB.enable();
	}
}