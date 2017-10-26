package me.shizleshizle.core.commands.spawn;

import org.bukkit.Bukkit;
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

public class Spawn implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Spawn" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + "You must a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.MEMBER)) {
                    if (args.length == 0) {
                        p.toSpawn();
                    } else if (args.length == 1) {
                        if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                            User t = new User(Bukkit.getPlayer(args[0]));
                            if (!t.isOnline()) {
                                ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
                            } else {
                                t.toSpawn();
                                t.sendMessage(prefix + "You have been teleported to the spawn!");
                                p.sendMessage(prefix + "You have teleported " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " to the spawn!");
                            }
                        } else {
                            ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/spawn <player");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/spawn");
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/spawn");
                }
            }
        }
        return false;
    }
}
