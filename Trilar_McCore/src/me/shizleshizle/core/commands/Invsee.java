package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Invsee implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Invsee" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("invsee")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 1) {
						if (args[0].equalsIgnoreCase("e")) {
							User t = new User(Bukkit.getPlayerExact(args[0]));
							if (t.isOnline()) {
								Inventory inv = Bukkit.createInventory(null, 9, "Invsee");
								inv.addItem(t.getInventory().getArmorContents());
								p.openInventory(inv);
							} else {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, t.getName());
							}
						} else {
							User t = new User(Bukkit.getPlayerExact(args[0]));
							if (t.isOnline()) {
								p.openInventory(t.getInventory());
							} else {
								ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, t.getName());
							}
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/invsee <player>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/invsee");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
