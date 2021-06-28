package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Workbench implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Workbench" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("workbench")) {
			if (!Main.isLobby()) {
				if (sender instanceof Player) {
					Player x = (Player) sender;
					User p = new User(x);
					if (Perm.hasPerm(p, PermGroup.MEMBER)) {
						if (args.length == 0) {
							p.openWorkbench(null, true);
						} else {
							ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/workbench");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
					}
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "workbench");
			}
		}
		return false;
	}
}
