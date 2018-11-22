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
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						p.sendMessage(ChatColor.GOLD + "========"  + ChatColor.YELLOW + " Mods " + ChatColor.GOLD + "========");
						p.sendMessage(ChatColor.RED + "Using unallowed mods will result in a ban!");
						p.sendMessage(" ");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " ArmorStatusHUD");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " StatusEffectHud");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " DirectionHud");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " 5Zig");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " bspkrsCore");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " Batty's Coordinates");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " ToggleSneak");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " PlayerApi");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " BetterSprint");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " Minimap without Entity/Player Radar");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " TabbyChat");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " Kohi TCPNoDelay");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " Text Mod");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " Saturation Display");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " Optifine");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " KeyStrokes mod");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " CPS mod");
						p.sendMessage(ChatColor.GOLD + "Allowed:" + ChatColor.YELLOW + " Labymod");
						p.sendMessage(" ");
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
