package me.shizleshizle.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.ChatColorHandler;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class ChatColorCmd implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chatcolor")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MANAGER)) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase(PermGroup.LEAD_DEVELOPER.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.LEAD_DEVELOPER)) {
								p.sendMessage(PREFIX + "Lead-Developer's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.LEAD_DEVELOPER)
								+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Lead-Developer's ChatColor is: " + ChatColor.GOLD + PermGroup.LEAD_DEVELOPER.getChatColor() + ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.DEVELOPER.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.DEVELOPER)) {
								p.sendMessage(PREFIX + "Developer's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.DEVELOPER)
								+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Developer's ChatColor is: " + ChatColor.GOLD + PermGroup.DEVELOPER.getChatColor() + ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.OWNER.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.OWNER)) {
								p.sendMessage(PREFIX + "Owner's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.OWNER) + ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Owner's ChatColor is: " + ChatColor.GOLD + PermGroup.OWNER.getChatColor() + ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.MANAGER.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.MANAGER)) {
								p.sendMessage(PREFIX + "Manager's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.MANAGER)
								+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Manager's ChatColor is: " + ChatColor.GOLD + PermGroup.MANAGER.getChatColor() + ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.ADMIN.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.ADMIN)) {
								p.sendMessage(PREFIX + "Admin's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.ADMIN)
								+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Admin's ChatColor is: " + ChatColor.GOLD + PermGroup.ADMIN.getChatColor() + ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.MODERATOR.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.MODERATOR)) {
								p.sendMessage(PREFIX + "Moderator's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.MODERATOR)
								+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Moderator's ChatColor is: " + ChatColor.GOLD + PermGroup.MODERATOR.getChatColor() + ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.HELPER.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.HELPER)) {
								p.sendMessage(PREFIX + "Helper's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.HELPER) + ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Helper's ChatColor is: " + ChatColor.GOLD + PermGroup.HELPER.getChatColor() + ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.HEAD_BUILDER.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.HEAD_BUILDER)) {
								p.sendMessage(PREFIX + "Head-Builder's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.HEAD_BUILDER)
								+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Head-Builder's ChatColor is: " + ChatColor.GOLD + PermGroup.HEAD_BUILDER.getChatColor()
								+ ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.BUILDER.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.BUILDER)) {
								p.sendMessage(PREFIX + "Builder's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.BUILDER)
								+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Builder's ChatColor is: " + ChatColor.GOLD + PermGroup.BUILDER.getChatColor()
								+ ChatColor.YELLOW + "!");
							}
						} else if (args[0].equalsIgnoreCase(PermGroup.MEMBER.getName())) {
							if (ChatColorHandler.hasColor(PermGroup.MEMBER)) {
								p.sendMessage(PREFIX + "Member's ChatColor is: " + ChatColor.GOLD + ChatColorHandler.getChatColor(PermGroup.MEMBER)
								+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Member's ChatColor is: " + ChatColor.GOLD + PermGroup.MEMBER.getChatColor()
								+ ChatColor.YELLOW + "!");
							}
						} else {
							if (ChatColorHandler.hasColor(args[0])) {
								p.sendMessage(PREFIX + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "'s PREFIX is " + ChatColor.GOLD +
										ChatColorHandler.getChatColor(p.getName()) + ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "'s PREFIX is " + ChatColor.GOLD + p.getGroup().getChatColor()
										+ ChatColor.YELLOW + "!");
							}
						}
					} else if (args.length == 3) {
						if (args[0].equalsIgnoreCase("set")) {
							if (args[1].equalsIgnoreCase(PermGroup.LEAD_DEVELOPER.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.LEAD_DEVELOPER, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Lead-Developer to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.DEVELOPER.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.DEVELOPER, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Developer to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.OWNER.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.OWNER, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Owner to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.MANAGER.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.MANAGER, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Manager to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.ADMIN.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.ADMIN, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Admin to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.MODERATOR.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.MODERATOR, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Moderator to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.HELPER.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.HELPER, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Helper to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.HEAD_BUILDER.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.HEAD_BUILDER, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Head-Developer to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.BUILDER.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.BUILDER, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Builder to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.MEMBER.getName())) {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(PermGroup.MEMBER, cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for Member to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							} else {
								String cc = args[2];
								if (!cc.startsWith("&")) {
									p.sendMessage(PREFIX + "ChatColor must start with &!");
								} else {
									ChatColorHandler.setChatColor(args[1], cc);
									p.sendMessage(PREFIX + "You have set the ChatColor for " + args[1] + " to " + ChatColor.GOLD + cc + ChatColor.YELLOW + "!");
								}
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/chatcolor <group|user|set|remove> [group|user] [chatcolor]");
						}
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("remove")) {
							if (args[1].equalsIgnoreCase(PermGroup.LEAD_DEVELOPER.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.LEAD_DEVELOPER)) {
									ChatColorHandler.removeChatColor(PermGroup.LEAD_DEVELOPER);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Lead-Developer!");
								} else {
									p.sendMessage(PREFIX + "Lead-Developer does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.DEVELOPER.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.DEVELOPER)) {
									ChatColorHandler.removeChatColor(PermGroup.DEVELOPER);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Developer!");
								} else {
									p.sendMessage(PREFIX + "Developer does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.OWNER.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.OWNER)) {
									ChatColorHandler.removeChatColor(PermGroup.OWNER);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Owner!");
								} else {
									p.sendMessage(PREFIX + "Owner does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.MANAGER.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.MANAGER)) {
									ChatColorHandler.removeChatColor(PermGroup.MANAGER);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Manager!");
								} else {
									p.sendMessage(PREFIX + "Server-Manager does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.ADMIN.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.ADMIN)) {
									ChatColorHandler.removeChatColor(PermGroup.ADMIN);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Admin!");
								} else {
									p.sendMessage(PREFIX + "Admin does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.MODERATOR.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.MODERATOR)) {
									ChatColorHandler.removeChatColor(PermGroup.MODERATOR);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Moderator!");
								} else {
									p.sendMessage(PREFIX + "Moderator does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.HELPER.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.HELPER)) {
									ChatColorHandler.removeChatColor(PermGroup.HELPER);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Helper!");
								} else {
									p.sendMessage(PREFIX + "Helper does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.HEAD_BUILDER.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.HEAD_BUILDER)) {
									ChatColorHandler.removeChatColor(PermGroup.HEAD_BUILDER);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Head-Builder!");
								} else {
									p.sendMessage(PREFIX + "Head-Builder does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.BUILDER.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.BUILDER)) {
									ChatColorHandler.removeChatColor(PermGroup.BUILDER);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Builder!");
								} else {
									p.sendMessage(PREFIX + "Builder does not have a ChatColor!");
								}
							} else if (args[1].equalsIgnoreCase(PermGroup.MEMBER.getName())) {
								if (ChatColorHandler.hasColor(PermGroup.MEMBER)) {
									ChatColorHandler.removeChatColor(PermGroup.MEMBER);
									p.sendMessage(PREFIX + "You have removed the ChatColor for Member!");
								} else {
									p.sendMessage(PREFIX + "Member does not have a ChatColor!");
								}
							} else {
								if (ChatColorHandler.hasColor(args[1])) {
									ChatColorHandler.removeChatColor(args[1]);
									p.sendMessage(PREFIX + "You have removed the ChatColor for " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(PREFIX + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " does not have a ChatColor!");
								}
							}
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/chatcolor <group|user|set|remove> [group|user] [chatcolor]");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/chatcolor <group|user|set|remove> [group|user] [chatcolor]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/chatcolor");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}