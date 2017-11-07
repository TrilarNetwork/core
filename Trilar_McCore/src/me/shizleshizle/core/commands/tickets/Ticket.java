package me.shizleshizle.core.commands.tickets;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.commands.cmdutils.TicketUtils;
import me.shizleshizle.core.commands.cmdutils.TicketUtils.Status;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Ticket implements CommandExecutor {
 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Tickets" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ticket")) {
			String owner;
			String desc;
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "ticket <text>");
						p.sendMessage(prefix + "");
					} else {
						StringBuilder sb = new StringBuilder();
						for (String word : args) {
							sb.append(word).append(" ");
						}
						desc = sb.toString().trim();
						owner = p.getName();
						TicketUtils.createTicket(owner, Status.OPEN, desc, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getWorld().getName());
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/ticket <text>");
				}
			} else {
				if (args.length == 0) {
					ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "ticket <text>");
				} else {
					StringBuilder sb = new StringBuilder();
					for (String word : args) {
						sb.append(word).append(" ");
					}
					desc = sb.toString().trim();
					owner = "CONSOLE";
					TicketUtils.createTicket(owner, Status.OPEN, desc, 0, 0, 0, "world");
				}
			}
		}
		return false;
	}
}