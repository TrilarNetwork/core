package me.shizleshizle.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.AutoB;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

// AB = AutoBroadcaster
public class AB implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Auto-Broadcaster" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("autobroadcaster")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length == 0) {
						p.sendMessage(ChatColor.GOLD + "-=[ Auto-Broadcaster ]=-");
						String on;
						if (AutoB.isBroadcasting()) {
							on = ChatColor.GREEN + "true";
						} else {
							on = ChatColor.RED + "false";
						}
						p.sendMessage(ChatColor.GOLD + "Enabled: " + on);
						p.sendMessage(ChatColor.GOLD + "Delay: " + ChatColor.YELLOW + Main.abdelay);
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("on")) {
							if (!AutoB.isBroadcasting()) {
								AutoB.enable();
								p.sendMessage(prefix + "You have enabled the Auto-Broadcaster!");
							} else {
								p.sendMessage(prefix + "Auto-Broadcaster is already enabled!");
							}
						} else if (args[0].equalsIgnoreCase("off")) {
							if (AutoB.isBroadcasting()) {
								AutoB.disable();
								p.sendMessage(prefix + "You have disabled the Auto-Broadcaster!");
							} else {
								p.sendMessage(prefix + "Auto-Broadcaster is already disabled!");
							}
						} else if (args[0].equalsIgnoreCase("list")) {
							p.sendMessage(AutoB.getStrings());
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/autobroadcaster <on|off|list>");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/autobroadcaster <on|off|list>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/autobroadcaster");
				}
			} else {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
