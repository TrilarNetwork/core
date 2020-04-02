package me.shizleshizle.core.commands.spawn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.ConfigManager;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;

public class SetSpawn implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Spawn" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	private ConfigManager c = ConfigManager.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setspawn")) {
			if (Perm.hasPerm(sender.getName(), PermGroup.LEAD_DEVELOPER)) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(PREFIX + "You must a player to perform this command!");
				} else {
					User p = new User((Player) sender);
					if (args.length != 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/setspawn");
					} else {
						double x = p.getLocation().getX();
						double y = p.getLocation().getY();
						double z = p.getLocation().getZ();
						double yaw = p.getLocation().getYaw();
						double pitch = p.getLocation().getPitch();
						c.getSpawn().set("spawn.x", x);
						c.getSpawn().set("spawn.y", y);
						c.getSpawn().set("spawn.z", z);
						c.getSpawn().set("spawn.yaw", yaw);
						c.getSpawn().set("spawn.pitch", pitch);
						c.getSpawn().set("spawn.world", p.getWorld().getName());
						c.saveSpawn();
						p.sendMessage(PREFIX + "Spawn has been set to your location!");
					}
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/setspawn");
			}
		}
		return false;
	}
}