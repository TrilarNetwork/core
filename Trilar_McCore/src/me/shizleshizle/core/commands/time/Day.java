package me.shizleshizle.core.commands.time;

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

public class Day implements CommandExecutor {
    public final String PREFIX = Time.PREFIX;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("day")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(PREFIX + "You must be a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                    if (args.length != 0) {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/day");
                    } else {
                        p.setDay(true);
                        p.sendMessage(PREFIX + "You have set the time in all worlds to " + ChatColor.GOLD + "0" + ChatColor.YELLOW + " ticks!");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/day");
                }
            }
        }
        return false;
    }
}
