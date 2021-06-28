package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.CI;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Skulls implements CommandExecutor {
	private final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Skulls" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("skull")) {
			if (Perm.hasPerm(sender.getName(), PermGroup.MEMBER)) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(PREFIX + "You must a player to perform this command!");
				} else {
					Player x = (Player) sender;
					User p = new User(x);
					if (args.length == 0 || args.length >= 3) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/skulls <owner> [amount]");
					} else if (args.length == 1) {
						p.getInventory().addItem(getSkull(args[0], 1, args[0]));
						p.sendMessage(PREFIX + "You have received the skull of " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
					} else {
						int amount = 0;
						try {
							amount = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							p.sendMessage(PREFIX + "You must enter a number!");
						}
						p.getInventory().addItem(getSkull(args[0], amount, args[0]));
						p.sendMessage(PREFIX + "You have received the skull of " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + "!");
					}
				} 
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/skulls");
			}
		}
		return false;
	}

	public static ItemStack getSkull(String owner, int amount, String name, String... lore) {
		ItemStack skull = CI.createItem(Material.PLAYER_HEAD, amount, -1, name, lore);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		assert sm != null;
		sm.setOwningPlayer(Bukkit.getPlayerExact(owner));
		skull.setItemMeta(sm);
		return skull;
	}
}
