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

public class Mods implements CommandExecutor {
 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Mods" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mods")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.HELPER)) {
					if (args.length == 0) {
						p.sendMessage(ChatColor.GOLD + "========"  + ChatColor.YELLOW + " Mods " + ChatColor.GOLD + "========");
						p.sendMessage(ChatColor.RED + "Mods overteden kan tot een ban leiden!");
						p.sendMessage(" ");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " ArmorStatusHUD");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " StatusEffectHud");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " DirectionHud");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " 5Zig");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " bspkrsCore");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " Batty's Coordinates");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " ToggleSneak");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " PlayerApi");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " BetterSprint");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " Minimap without Entity/Player Radar");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " TabbyChat");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " Kohi TCPNoDelay");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " Text Mod");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " Saturation Display");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " Optifine");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " KeyStrokes mod");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " CPS mod");
						p.sendMessage(ChatColor.GOLD + "Toegestaan:" + ChatColor.YELLOW + " Labymod");
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/Mods");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/Mods");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
