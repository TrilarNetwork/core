package me.shizleshizle.core.commands.teleportation;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpo implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpo")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Tp.PREFIX + "You must be a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                    if (args.length == 0) {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpo <player>");
                    } else if (args.length == 1) {
                        User t = new User(Bukkit.getPlayer(args[0]));
                        if (!t.isOnline()) {
                            ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
                        } else {
                            p.teleport(t.getLocation());
                            p.sendMessage(Tp.PREFIX + "You have been teleported to " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpo <player> [player]");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tpo");
                }
            }
        }
        return false;
    }
}