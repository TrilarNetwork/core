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

public class Me implements CommandExecutor {
    public final String PREFIX = ChatColor.YELLOW + "Me" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("me")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(PREFIX + "You must be a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                    if (args.length < 1) {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/me <message>");
                    } else {
                        String msg;
                        StringBuilder sb = new StringBuilder();
                        for (String i : args) {
                            sb.append(i).append(" ");
                        }
                        if (Perm.hasPerm(p, PermGroup.BUILDER)) {
                            msg = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
                        } else {
                            msg = sb.toString().trim();
                        }
                        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "* " + p.getName() + " " + msg);
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/me");
                }
            }
        }
        return false;
    }
}