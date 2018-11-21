package me.shizleshizle.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.permissions.Prefix;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class PrefixCmd implements CommandExecutor {
 	public String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Prefix" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("prefix")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MANAGER)) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase(PermGroup.LEAD_DEVELOPER.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Lead-Developer:");
							p.sendMessage(Prefix.getPrefix(PermGroup.LEAD_DEVELOPER));
						} else if (args[0].equalsIgnoreCase(PermGroup.DEVELOPER.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Developer:");
							p.sendMessage(Prefix.getPrefix(PermGroup.DEVELOPER));
						} else if (args[0].equalsIgnoreCase(PermGroup.OWNER.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Owner:");
							p.sendMessage(Prefix.getPrefix(PermGroup.OWNER));
						} else if (args[0].equalsIgnoreCase(PermGroup.MANAGER.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Server-Manager:");
							p.sendMessage(Prefix.getPrefix(PermGroup.MANAGER));
						} else if (args[0].equalsIgnoreCase(PermGroup.ADMIN.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Admin:");
							p.sendMessage(Prefix.getPrefix(PermGroup.ADMIN));
						} else if (args[0].equalsIgnoreCase(PermGroup.MODERATOR.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Moderator:");
							p.sendMessage(Prefix.getPrefix(PermGroup.MODERATOR));
						} else if (args[0].equalsIgnoreCase(PermGroup.HELPER.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Helper:");
							p.sendMessage(Prefix.getPrefix(PermGroup.HELPER));
						} else if (args[0].equalsIgnoreCase(PermGroup.BUILDER.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Builder:");
							p.sendMessage(Prefix.getPrefix(PermGroup.BUILDER));
						} else if (args[0].equalsIgnoreCase(PermGroup.MEMBER.toString().toLowerCase())) {
							p.sendMessage(prefix + "The prefix of Member:");
							p.sendMessage(Prefix.getPrefix(PermGroup.MEMBER));
						} else {
							if (Prefix.hasPrefix(args[1])) {
								p.sendMessage(prefix + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "'s prefix is: ");
								p.sendMessage(Prefix.getPrefix(args[1]));
							} else {
								p.sendMessage(prefix + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " does not have a prefix!");
							}
						}
					} else if (args.length == 3) {
						if (args[0].equalsIgnoreCase("set")) {
							if (args[1].equalsIgnoreCase(PermGroup.LEAD_DEVELOPER.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.LEAD_DEVELOPER, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Lead-Developer to: ");
								p.sendMessage(prefix);
							} else if (args[1].equalsIgnoreCase(PermGroup.DEVELOPER.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.DEVELOPER, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Developer to: ");
								p.sendMessage(prefix);
							} else if (args[1].equalsIgnoreCase(PermGroup.OWNER.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.OWNER, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Owner to: ");
								p.sendMessage(prefix);
							} else if (args[1].equalsIgnoreCase(PermGroup.MANAGER.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.MANAGER, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Server-Manager to: ");
								p.sendMessage(prefix);
							} else if (args[1].equalsIgnoreCase(PermGroup.ADMIN.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.ADMIN, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Admin to: ");
								p.sendMessage(prefix);
							} else if (args[1].equalsIgnoreCase(PermGroup.MODERATOR.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.MODERATOR, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Moderator to: ");
								p.sendMessage(prefix);
							} else if (args[1].equalsIgnoreCase(PermGroup.HELPER.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.HELPER, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Helper to: ");
								p.sendMessage(prefix);
							} else if (args[1].equalsIgnoreCase(PermGroup.BUILDER.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.BUILDER, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Builder to: ");
								p.sendMessage(prefix);
							} else if (args[1].equalsIgnoreCase(PermGroup.MEMBER.toString().toLowerCase())) {
								String prefix = args[2].trim();
								Prefix.setPrefix(PermGroup.MEMBER, prefix);
								p.sendMessage(this.prefix + "You have set the prefix for Member to: ");
								p.sendMessage(prefix);
							} else {
								String prefix = args[2].trim();
								Prefix.setPrefix(args[1], prefix);
								p.sendMessage(this.prefix + "You have set " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "'s prefix to: ");
								p.sendMessage(prefix);
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/prefix <user|group|set|remove> [user|group] [prefix]");
						}
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("remove")) {
							if (args[1].equalsIgnoreCase(PermGroup.LEAD_DEVELOPER.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.LEAD_DEVELOPER)) {
									Prefix.removePrefix(PermGroup.LEAD_DEVELOPER);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Lead-Developer! (The old one will take its place).");
							} else if (args[1].equalsIgnoreCase(PermGroup.DEVELOPER.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.DEVELOPER)) {
									Prefix.removePrefix(PermGroup.DEVELOPER);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Developer! (The old one will take its place).");
							} else if (args[1].equalsIgnoreCase(PermGroup.OWNER.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.OWNER)) {
									Prefix.removePrefix(PermGroup.OWNER);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Owner! (The old one will take its place).");
							} else if (args[1].equalsIgnoreCase(PermGroup.MANAGER.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.MANAGER)) {
									Prefix.removePrefix(PermGroup.MANAGER);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Server-Manager! (The old one will take its place).");
							} else if (args[1].equalsIgnoreCase(PermGroup.ADMIN.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.ADMIN)) {
									Prefix.removePrefix(PermGroup.ADMIN);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Admin! (The old one will take its place).");
							} else if (args[1].equalsIgnoreCase(PermGroup.MODERATOR.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.MODERATOR)) {
									Prefix.removePrefix(PermGroup.MODERATOR);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Moderator! (The old one will take its place).");
							} else if (args[1].equalsIgnoreCase(PermGroup.HELPER.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.HELPER)) {
									Prefix.removePrefix(PermGroup.HELPER);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Helper! (The old one will take its place).");
							} else if (args[1].equalsIgnoreCase(PermGroup.BUILDER.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.BUILDER)) {
									Prefix.removePrefix(PermGroup.BUILDER);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Builder! (The old one will take its place).");
							} else if (args[1].equalsIgnoreCase(PermGroup.MEMBER.toString().toLowerCase())) {
								if (Prefix.hasPrefix(PermGroup.MEMBER)) {
									Prefix.removePrefix(PermGroup.MEMBER);
								} else {
									p.sendMessage(prefix + "This rank does not have a custom prefix!");
								}
								p.sendMessage(prefix + "You have removed the custom prefix of Member! (The old one will take its place).");
							} else {
								if (Prefix.hasPrefix(args[1])) {
									Prefix.removePrefix(args[1]);
									p.sendMessage(prefix + "You have removed the prefix of " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(prefix + ChatColor.GOLD + args[0] + ChatColor.YELLOW + " does not have a prefix!");
								}
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/prefix <user|group|set|remove> [user|group] [prefix]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/prefix <user|group|set|remove> [user|group] [prefix]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/prefix");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}