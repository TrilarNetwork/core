package me.shizleshizle.core.commands.teleportation;

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

public class Tpaccept implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Teleportation" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpaccept")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						String n = "";
						if (Tpa.tpa.containsValue(p.getName())) {
							for (String s : Tpa.tpa.keySet()) {
								if (Tpa.tpa.get(s).equals(p.getName())) {
									n = s;
								}
							}
							Player t = Bukkit.getPlayer(n);
							p.sendMessage(prefix + "You have accepted the teleport request of " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + ".");
							t.sendMessage(prefix + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has accepted your request, teleporting in " + ChatColor.GOLD + Main.tpTime 
									+ ChatColor.YELLOW + " seconds!");
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.p, new Runnable() {
								@Override
								public void run() {
									if (Tpa.toTP) {
										t.teleport(p.getLocation());
										t.sendMessage(prefix + "You have been teleported to " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
										Tpa.tpa.remove(t.getName());
									}
								}
							}, Main.tpTime*20);
						} else if (Main.tpahere.containsValue(p.getName())) {
							for (String s : Main.tpahere.keySet()) {
								if (Main.tpahere.get(s).equals(p.getName())) {
									n = s;
								}
							}
							Player t = Bukkit.getPlayer(n);
							p.sendMessage(prefix + "You have accepted the teleport request of " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + ", teleporting in " + ChatColor.GOLD 
									+ Main.tpTime + ChatColor.YELLOW + " seconds!");
							t.sendMessage(prefix + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has accepted your request!");
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.p, new Runnable() {
								@Override
								public void run() {
									if (Tpahere.toTP) {
										p.teleport(t.getLocation());
										p.sendMessage(prefix + "You have been teleported to " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
										Main.tpahere.remove(t.getName());
									}
								}
							}, Main.tpTime*20);
						} else {
							p.sendMessage(prefix + "You do not have a pending request!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpaccept");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tpaccept");
				}
			}
		}
		return false;
	}
}