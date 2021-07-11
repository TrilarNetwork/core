package me.shizleshizle.core.commands.bansystem;

import me.shizleshizle.core.commands.cmdutils.WarnUtils;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

public class Warn implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("warn")) {
            String senderName = GOLD + "Console";
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (!Perm.hasPerm(p, PermGroup.HELPER)) {
                    int warnAmnt = p.getWarnAmount();
                    if (warnAmnt == 0) {
                        p.sendMessage(Ban.PREFIX + "You do not have any warnings!");
                    } else {
                        p.sendMessage(Ban.PREFIX + "You have " + GOLD + warnAmnt + YELLOW + " warnings!");
                        for (String warning : p.getWarns()) {
                            p.sendMessage(warning);
                        }
                    }
                    return false;
                }
                senderName = GOLD + p.getName();
            } // /warn <player|lookup|remove> [player|reason] [id]
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("lookup")) {
                    String[] warns = WarnUtils.getWarns(args[1]);
                    if (warns != null) {
                        sender.sendMessage(Ban.PREFIX + "Player " + GOLD + args[1] + YELLOW + " has these warnings:");
                        for (String warning : warns) {
                            sender.sendMessage(warning);
                        }
                    } else {
                        sender.sendMessage(Ban.PREFIX + "Player " + GOLD + args[1] + YELLOW + " does not have any warnings!");
                    }
                } else {
                    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                    if (targetPlayer == null) {
                        ErrorMessages.doErrorMessage(sender, Messages.PLAYER_OFFLINE, args[0]);
                    } else {
                        User target = new User(targetPlayer);
                        target.warn(ChatColor.translateAlternateColorCodes('&', args[1].trim()), senderName);
                    }
                }
            } else if (args.length > 2) {
                if (args.length == 3 && args[0].equalsIgnoreCase("remove")) {
                    Player targetPlayer = Bukkit.getPlayerExact(args[1]);
                    if (targetPlayer == null) {
                        String target = args[1];
                        if (Numbers.isNumber(args[2])) {
                            int warnId = Numbers.getInt(args[2]);
                            if (WarnUtils.hasWarn(target, warnId)) {
                                WarnUtils.RemoveWarn(target, warnId);
                            } else {
                                sender.sendMessage(Ban.PREFIX + "Player " + GOLD + target + YELLOW + " does not have warning #" + GOLD + warnId + YELLOW + "!");
                            }
                        } else {
                            sender.sendMessage(Ban.PREFIX + "You must enter a number!");
                        }
                    } else {
                        User target = new User(targetPlayer);
                        if (Numbers.isNumber(args[2])) {
                            int warnId = Numbers.getInt(args[2]);
                            if (target.hasWarn(warnId)) {
                                target.removeWarn(warnId);
                            } else {
                                sender.sendMessage(Ban.PREFIX + "Player " + GOLD + target.getName() + YELLOW + " does not have warning #" + GOLD + warnId + YELLOW + "!");
                            }
                        } else {
                            sender.sendMessage(Ban.PREFIX + "You must enter a number!");
                        }
                    }
                } else {
                    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                    if (targetPlayer == null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        WarnUtils.warn(args[0], ChatColor.translateAlternateColorCodes('&', sb.toString().trim()), senderName);
                    } else {
                        User target = new User(targetPlayer);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        target.warn(ChatColor.translateAlternateColorCodes('&', sb.toString().trim()), senderName);
                    }
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/warn <player|lookup|remove> [player|reason]");
            }
        }
        return false;
    }
}
