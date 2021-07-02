package me.shizleshizle.core.commands.spawn;

import me.shizleshizle.core.ConfigManager;
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

public class Removespawn implements CommandExecutor {
	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Spawn" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
	private ConfigManager c = ConfigManager.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("removespawn")) {
			if (Perm.hasPerm(sender.getName(), PermGroup.LEAD_DEVELOPER)) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(PREFIX + "You must a player to perform this command!");
				} else {
					User p = new User((Player) sender);
					if (args.length != 0) {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/removespawn");
					} else {
						c.getSpawn().set("spawn.x", null);
						c.getSpawn().set("spawn.y", null);
						c.getSpawn().set("spawn.z", null);
						c.getSpawn().set("spawn.yaw", null);
						c.getSpawn().set("spawn.pitch", null);
						c.getSpawn().set("spawn.world", null);
						c.saveSpawn();
						p.sendMessage(PREFIX + "Spawn has been deleted!");
					}
				}
			} else {
				ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/removespawn");
			}
		}
		return false;
	}
}