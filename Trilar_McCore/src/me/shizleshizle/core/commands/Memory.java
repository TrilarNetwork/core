package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Memory implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Memory" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("memory")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (!Perm.hasPerm(p, PermGroup.MODERATOR)) {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/memory");
                    return false;
                }
            }
            if (args.length == 0) {
                Runtime r = Runtime.getRuntime();
                int divider = 100000000;
                sender.sendMessage(YELLOW + "Free memory: " + GOLD + r.freeMemory() / divider + "G");
                sender.sendMessage(YELLOW + "Max Memory: " + GOLD + r.maxMemory() / divider + "G");
                sender.sendMessage(YELLOW + "Total Memory: " + GOLD + r.totalMemory() / divider + "G");
                sender.sendMessage(YELLOW + "Available Processors: " + GOLD + r.availableProcessors());
            } else if (args.length == 1 && args[0].equalsIgnoreCase("gc")) {
                Runtime r = Runtime.getRuntime();
                r.gc();
                sender.sendMessage(PREFIX + "Garbage collector ran!");
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/memory [gc]");
            }
        }
        return false;
    }
}
