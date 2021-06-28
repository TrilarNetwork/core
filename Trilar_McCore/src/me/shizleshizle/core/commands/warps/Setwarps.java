package me.shizleshizle.core.commands.warps;

import me.shizleshizle.core.commands.cmdutils.WarpUtils;
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

public class Setwarps implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Warps" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setwarp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(PREFIX + "You must be a player to perform this command!");
			} else {
				Player px = (Player) sender;
				User p = new User(px);
				if (Perm.hasPerm(p, PermGroup.ADMIN)) {
					if (args.length != 1) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/setwarp <name>");
					} else {
						double x = p.getLocation().getX();
						double y = p.getLocation().getY();
						double z = p.getLocation().getZ();
						double yaw = p.getLocation().getYaw();
						double pitch = p.getLocation().getPitch();
						String wname = p.getWorld().getName();
						WarpUtils.setWarp(args[0], x, y, z, yaw, pitch, wname);
						p.sendMessage(PREFIX + "Warp " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + " has been set!");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/removewarp");
				}
			}
		}
		return false;
	}
}
