package me.shizleshizle.core.commands.tickets;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.commands.cmdutils.TicketUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import me.shizleshizle.core.utils.Numbers;

public class TakeTicket implements CommandExecutor {
 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Tickets" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("taketicket")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 1) {
						if (Numbers.isNumber(args[0])) {
							int id = Numbers.getInt(args[0]);
							if (TicketUtils.exists(id)) {
								Location l = TicketUtils.getLocation(id);
								if (l != null) {
									p.teleport(l);
									p.sendMessage(prefix + "You have teleported to ticket " + ChatColor.GOLD + id + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(prefix + "This Ticket does not contain a valid Location!");
								}
							} else {
								p.sendMessage(prefix + "Ticket " + ChatColor.GOLD + id + ChatColor.YELLOW + " does not exist!");
							}
						} else {
							p.sendMessage(prefix + "You must use a number!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/taketicket <id>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/taketicket");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}