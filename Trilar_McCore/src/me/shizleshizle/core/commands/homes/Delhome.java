package me.shizleshizle.core.commands.homes;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.cmdutils.HomeUtils;
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

public class Delhome implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Homes" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("delhome")) {
			if (!Main.isLobby()) {
				if (sender instanceof Player) {
					Player pl = (Player) sender;
					User p = new User(pl);
					if (Perm.hasPerm(p, PermGroup.MEMBER)) {
						if (args.length == 0) {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/removehome <name>");
						} else if (args.length == 1) {
							if (HomeUtils.hasHome(p.getName(), args[0])) {
								HomeUtils.removeHome(p.getName(), args[0]);
								p.sendMessage(PREFIX + "You have deleted home " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
							} else {
								p.sendMessage(PREFIX + "You do not have home " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
							}
						} else if (args.length == 2) {
							if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
								if (HomeUtils.hasHome(args[1], args[0])) {
									HomeUtils.removeHome(args[1], args[0]);
									p.sendMessage(PREFIX + "You have removed home " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + " from player "
											+ ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
								} else {
									p.sendMessage(PREFIX + "Player " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " does not have home "
											+ ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
								}
							} else {
								ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/removehome <name> <player>");
							}
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/delhome");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "delhome");
			}
		}
		return false;
	}
}