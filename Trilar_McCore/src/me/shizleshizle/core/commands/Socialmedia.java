package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Socialmedia implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "SocialMedia" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("socialmedia")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						p.sendMessage(ChatColor.GOLD + "========"  + ChatColor.YELLOW + " SocialMedia " + ChatColor.GOLD + "========");
						p.sendMessage(ChatColor.RED + "Follow us on social media to stay up to date!");
						p.sendMessage(" ");
						p.sendMessage(ChatColor.GOLD + "Snapchat:" + ChatColor.YELLOW + " /");
						p.sendMessage(ChatColor.GOLD + "Instagram:" + ChatColor.YELLOW + " /");
						p.sendMessage(ChatColor.GOLD + "Youtube:" + ChatColor.YELLOW + " /");
						p.sendMessage(ChatColor.GOLD + "Twitter:" + ChatColor.YELLOW + " /");
						p.sendMessage(" ");
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/SocialMedia");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/SocialMedia");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
