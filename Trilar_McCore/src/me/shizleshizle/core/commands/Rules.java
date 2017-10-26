package me.shizleshizle.core.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class Rules implements CommandExecutor {
 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rules" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("regels")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						p.sendMessage(ChatColor.GOLD + "========"  + ChatColor.YELLOW + " Regels " + ChatColor.GOLD + "========");
						p.sendMessage(ChatColor.RED + "Regels overteden kan tot een ban leiden!");
						p.sendMessage(" ");
						p.sendMessage(ChatColor.GOLD + "Regel 1:" + ChatColor.YELLOW + " Niet schelden.");
						p.sendMessage(ChatColor.GOLD + "Regel 2:" + ChatColor.YELLOW + " Respecteer staff en medespelers.");
						p.sendMessage(ChatColor.GOLD + "Regel 3:" + ChatColor.YELLOW + " Niet dreigen!");
						p.sendMessage(ChatColor.GOLD + "Regel 4:" + ChatColor.YELLOW + " Luisteren naar staff.");
						p.sendMessage(ChatColor.GOLD + "Regel 5:" + ChatColor.YELLOW + " Geen ban of mute omzeilen dit kan leiden tot een IP-Ban!");
					} else if (args[0].equalsIgnoreCase("2")) {
						p.sendMessage(ChatColor.GOLD + "========"  + ChatColor.YELLOW + " Regels 2 " + ChatColor.GOLD + "========");
						p.sendMessage(ChatColor.RED + "Regels overteden kan tot een ban leiden!");
						p.sendMessage(" ");
						p.sendMessage(ChatColor.GOLD + "Regel 6:" + ChatColor.YELLOW + " Niet Hacken of verboden mods gebruiken.");
						p.sendMessage(ChatColor.GOLD + "Regel 7:" + ChatColor.YELLOW + " Niet spammen");
						p.sendMessage(ChatColor.GOLD + "Regel 8:" + ChatColor.YELLOW + " Geen 18+ taalgebruik in de chat!");
						p.sendMessage(ChatColor.GOLD + "Regel 9:" + ChatColor.YELLOW + " Niet zeuren");
						p.sendMessage(ChatColor.GOLD + "Regel 10:" + ChatColor.YELLOW + " Geen staff beledigen");
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/regels");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/regels");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}