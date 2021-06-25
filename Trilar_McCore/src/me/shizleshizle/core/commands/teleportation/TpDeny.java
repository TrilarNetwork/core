package me.shizleshizle.core.commands.teleportation;

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

public class TpDeny implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpdeny")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Tp.PREFIX + "You must be a player to perform this command!");
			} else {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						if (Tpa.tpa.containsValue(p.getName())) {
							String n = "";
							for (String s : Tpa.tpa.keySet()) {
								if (Tpa.tpa.get(s).equals(p.getName())) {
									n = s;
								}
							}
							Player t = Bukkit.getPlayer(n);
							if (t == null) {
								p.sendMessage(Tp.PREFIX + "Player is not online!");
							} else {
								p.sendMessage(Tp.PREFIX + "You have denied " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "'s teleport request!");
								t.sendMessage(Tp.PREFIX + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has denied your request!");
								Tpa.tpa.remove(t.getName());
							}
						} else if (Main.tpahere.containsValue(p.getName())) {
							String n = "";
							for (String s : Main.tpahere.keySet()) {
								if (Main.tpahere.get(s).equals(p.getName())) {
									n = s;
								}
							}
							Player t = Bukkit.getPlayer(n);
							if (t == null) {
								p.sendMessage(Tp.PREFIX + "Player is not online!");
							} else {
								p.sendMessage(Tp.PREFIX + "You have denied " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "'s teleport request!");
								t.sendMessage(Tp.PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has denied your request!");
								Main.tpahere.remove(t.getName());
							}
						} else {
							p.sendMessage(Tp.PREFIX + "You do not have a pending request!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpdeny");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tpdeny");
				}
			}
		}
		return false;
	}
}