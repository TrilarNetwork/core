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

public class God implements CommandExecutor {
    private final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "God" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("god")) {
            if (sender instanceof Player) {
                Player pl = (Player) sender;
                User p = new User(pl);
                if (Perm.hasPerm(p, PermGroup.HELPER)) {
                    if (args.length == 0) {
                        if (p.isGod()) {
                            p.setGod(false);
                            p.sendMessage(PREFIX + "You have " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " God Mode!");
                        } else {
                            p.setGod(true);
                            p.sendMessage(PREFIX + "You have " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " God Mode!");
                        }
                    } else if (args.length == 1) {
                        if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                            Player tp = Bukkit.getPlayer(args[0]);
                            if (tp != null) {
                                User t = new User(tp);
                                if (t.isGod()) {
                                    t.setGod(false);
                                    p.sendMessage(PREFIX + "You have " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " God Mode for " + ChatColor.GOLD
                                            + t.getName() + ChatColor.YELLOW + "!");
                                    t.sendMessage(PREFIX + "Your God Mode has been " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " by " + ChatColor.GOLD
                                            + p.getName() + ChatColor.YELLOW + "!");
                                } else {
                                    t.setGod(true);
                                    p.sendMessage(PREFIX + "You have " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " God Mode for " + ChatColor.GOLD
                                            + t.getName() + ChatColor.YELLOW + "!");
                                    t.sendMessage(PREFIX + "Your God Mode has been " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " by " + ChatColor.GOLD
                                            + p.getName() + ChatColor.YELLOW + "!");
                                }
                            } else {
                                ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
                            }
                        } else {
                            ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/god <player>");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/god [player]");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/god");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
            }
        }
        return false;
    }
}