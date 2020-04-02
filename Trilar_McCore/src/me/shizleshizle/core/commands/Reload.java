	package me.shizleshizle.core.commands;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.ChatColorHandler;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.permissions.PermUser;
import me.shizleshizle.core.permissions.PermissionGroup;
import me.shizleshizle.core.permissions.Prefix;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Reload implements CommandExecutor {
 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Trilar Core" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mccore")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("reload")) {
							try {
								if (Main.sql.hasConnection()) {
									Main.sql.closeConnection();
									Main.sql.openConnection();
								} else {
									Main.sql.openConnection();
								}
								Main.sql.setup();
								PermissionGroup.setup();
								PermUser.setup();
								Prefix.setup();
								ChatColorHandler.setup();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							Main.c.saveAll();
							Main.c.reloadAll();
							Main.setupUtils();
							p.sendMessage(prefix + "You have successfully reloaded the databases and all yml files!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/mccore reload");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/mccore");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
