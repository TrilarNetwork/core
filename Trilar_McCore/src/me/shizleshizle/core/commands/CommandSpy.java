package me.shizleshizle.core.commands;

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

import java.util.ArrayList;

public class CommandSpy implements CommandExecutor {
	public final String PREFIX = ChatColor.YELLOW + "CommandSpy" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	public static ArrayList<String>
	spy = new ArrayList<>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("commandspy")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x); 
				if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
					if (args.length == 0) {
						if (spy.contains(p.getName())) {
							spy.remove(p.getName());
							p.sendMessage(PREFIX + "You have " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " CommandSpy!");
						} else {
							spy.add(p.getName());
							p.sendMessage(PREFIX + "You have " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " CommandSpy!");
						}
					} else if (args.length == 1) {
						if (Perm.hasPerm(p, PermGroup.ADMIN)) {
							Player t = Bukkit.getPlayerExact(args[0]);
							if (t != null) {
								if (spy.contains(t.getName())) {
									spy.remove(t.getName());
									p.sendMessage(PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "'s CommandSpy has been " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + "!");
								} else {
									spy.add(t.getName());
									p.sendMessage(PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "'s CommandSpy has been " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + "!");
								}
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/commandspy <player>");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/commandspy");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/commandspy");
				}
			} 
		}
		return false;
	}
}