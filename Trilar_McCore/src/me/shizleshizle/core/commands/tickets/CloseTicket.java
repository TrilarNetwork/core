package me.shizleshizle.core.commands.tickets;

import me.shizleshizle.core.commands.Broadcast;
import me.shizleshizle.core.commands.cmdutils.TicketUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import me.shizleshizle.core.utils.Numbers;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CloseTicket implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Tickets" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("closeticket")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
					if (args.length == 1) {
						if (Numbers.isNumber(args[0])) {
							int id = Numbers.getInt(args[0]);
							if (TicketUtils.exists(id)) {
								TicketUtils.closeTicket(id);
								Broadcast.broadcastToStaff(PREFIX + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has closed ticket: " + ChatColor.GOLD + id
										+ ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "Ticket " + ChatColor.GOLD + id + ChatColor.YELLOW + " does not exist!");
							}
						} else {
							p.sendMessage(PREFIX + "You must enter a number!");
						}
					} else if (args.length == 2) {
						if (Numbers.isNumber(args[0])) {
							if (args[1].equalsIgnoreCase("-r")) {
								int id = Numbers.getInt(args[0]);
								if (TicketUtils.exists(id)) {
									TicketUtils.openTicket(id);
									Broadcast.broadcastToStaff(PREFIX + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has reopened ticket: " + ChatColor.GOLD + id
											+ ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(PREFIX + "Ticket " + ChatColor.GOLD + id + ChatColor.YELLOW + " does not exist!");
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/closeticket <id> [-r]");
							}
						} else {
							p.sendMessage(PREFIX + "You must enter a number!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/closeticket <id> [-r]");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/closeticket");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}