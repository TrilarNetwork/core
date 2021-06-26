package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Enderchest implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Enderchest" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("enderchest")) {
			if (!Main.isLobby()) {
				if (sender instanceof Player) {
					Player x = (Player) sender;
					User p = new User(x);
					if (Perm.hasPerm(p, PermGroup.VIP)) {
						if (args.length == 0) {
							p.openInventory(p.getEnderchest());
						} else if (args.length == 1) {
							User t = new User(Bukkit.getPlayerExact(args[0]));
							p.openInventory(t.getEnderchest());
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/enderchest");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "enderchest");
			}
		}
		return false;
	}
}
