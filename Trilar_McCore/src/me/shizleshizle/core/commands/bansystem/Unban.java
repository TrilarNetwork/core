package me.shizleshizle.core.commands.bansystem;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

public class Unban implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("unban")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                    if (args.length == 1) {
                        if (args[0].contains(".")) {
                            if (Bukkit.getBanList(BanList.Type.IP).isBanned(args[0])) {
                                Bukkit.getBanList(BanList.Type.IP).pardon(args[0]);
                                p.sendMessage(Ban.PREFIX + "Player with IP " + GOLD + args[0] + YELLOW + " has been unbanned!");
                            } else {
                                p.sendMessage(Ban.PREFIX + "Player with IP " + GOLD + args[0] + YELLOW + " has not been banned!");
                            }
                        } else {
                            if (Bukkit.getBanList(BanList.Type.NAME).isBanned(args[0])) {
                                Bukkit.getBanList(BanList.Type.NAME).pardon(args[0]);
                                p.sendMessage(Ban.PREFIX + "Player " + GOLD + args[0] + YELLOW + " has been unbanned!");
                            } else {
                                p.sendMessage(Ban.PREFIX + "Player " + GOLD + args[0] + YELLOW + " has not been banned!");
                            }
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/unban <player|ip>");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/unban");
                }
            } else {
                if (args.length == 1) {
                    if (args[0].contains(".")) {
                        if (Bukkit.getBanList(BanList.Type.IP).isBanned(args[0])) {
                            Bukkit.getBanList(BanList.Type.IP).pardon(args[0]);
                            sender.sendMessage(Ban.PREFIX + "Player with IP " + GOLD + args[0] + YELLOW + " has been unbanned!");
                        } else {
                            sender.sendMessage(Ban.PREFIX + "Player with IP " + GOLD + args[0] + YELLOW + " has not been banned!");
                        }
                    } else {
                        if (Bukkit.getBanList(BanList.Type.NAME).isBanned(args[0])) {
                            Bukkit.getBanList(BanList.Type.NAME).pardon(args[0]);
                            sender.sendMessage(Ban.PREFIX + "Player " + GOLD + args[0] + YELLOW + " has been unbanned!");
                        } else {
                            sender.sendMessage(Ban.PREFIX + "Player " + GOLD + args[0] + YELLOW + " has not been banned!");
                        }
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/unban <player|ip>");
                }
            }
        }
        return false;
    }
}
