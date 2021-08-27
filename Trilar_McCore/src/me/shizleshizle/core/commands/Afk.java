package me.shizleshizle.core.commands;

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

public class Afk implements CommandExecutor {
    public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Afk" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("afk")) {
            if (sender instanceof Player) {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.MEMBER)) {
                    if (args.length == 0) {
                        p.setAfk(!p.isAfk());
                    } else if (args.length == 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (!(target == null) && !target.isOnline()) {
                            ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, target.getName());
                        } else {
                            User t = new User(target);
                            t.setAfk(!t.isAfk());
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/afk [player]");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/afk");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
            }
        }
        return false;
    }
}
