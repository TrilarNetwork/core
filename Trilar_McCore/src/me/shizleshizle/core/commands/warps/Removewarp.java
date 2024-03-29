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

public class Removewarp implements CommandExecutor {
    public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Warps" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("removewarp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(PREFIX + "You must be a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                    if (args.length != 1) {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/removewarp <name>");
                    } else {
                        WarpUtils.removeWarp(args[0]);
                        p.sendMessage(PREFIX + "Warp " + ChatColor.GOLD + args[0] + ChatColor.YELLOW + " has been removed!");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/removewarp");
                }
            }
        }
        return false;
    }
}
