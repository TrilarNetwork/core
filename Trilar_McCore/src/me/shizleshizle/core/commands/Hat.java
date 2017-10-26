package me.shizleshizle.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Hat implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Hat" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hat")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						if (p.getItemInHand() != null) {
							ItemStack i = p.getItemInHand();
							if (p.getInventory().getHelmet() != null) {
								p.setItemInHand(p.getInventory().getHelmet());
								p.sendMessage(prefix + "Enjoy your hat!");
							} else {
								p.setItemInHand(new ItemStack(Material.AIR));
								p.sendMessage(prefix + "Enjoy your hat!");
							}
							p.getInventory().setHelmet(i);
						} else {
							p.sendMessage(prefix + "You have nothing in your hand!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/hat");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/hat");
				}
			}
		}
		return false;
	}
}