package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.permissions.PermissionGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Rank implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Ranks" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("rank")) {
            String senderName = GOLD + "Console";
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (!p.hasPermission(PermGroup.MANAGER) || (!p.getName().equals("shizleshizle") || !p.getName().equals("iMelvin"))) {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/rank");
                    return false;
                }
                senderName = GOLD + p.getName() + YELLOW;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    sender.sendMessage(PREFIX + "There are a total of " + GOLD + PermGroup.getTotal() + YELLOW + " ranks! Here they are:");
                    for (PermGroup s : PermGroup.values()) {
                        sender.sendMessage(s.getPrefix());
                    }
                    sender.sendMessage(YELLOW + "Default doesn't have a PREFIX, but default is the rank people get when they join for the first time.");
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/rank <list|set|get|list|add|remove> [user|rank|group] [rank|permission]");
                }
                return true;
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("get")) {
                    PermGroup pg = Perm.getGroup(args[1]);
                    if (pg != null) {
                        sender.sendMessage(PREFIX + "Player " + GOLD + args[1] + YELLOW + " is in group "
                                + GOLD + pg.getName() + YELLOW + "!");
                    } else {
                        sender.sendMessage(PREFIX + "Database could not find player " + GOLD + args[1] + YELLOW + "!");
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("list")) {
                    PermGroup pg = Perm.getGroup(args[1]);
                    sender.sendMessage(PREFIX + GOLD + pg.getName() + YELLOW + " has these permissions:");
                    for (String pe : PermissionGroup.getPermissions(pg)) {
                        sender.sendMessage(GRAY + pe);
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/rank <list|set|get> [user] [rank]");
                    return true;
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    String user = args[1];
                    PermGroup g = null;
                    for (PermGroup s : PermGroup.values()) {
                        if (s.getName().equalsIgnoreCase(args[2])) {
                            g = s;
                        }
                    }
                    if (g == null) {
                        sender.sendMessage(PREFIX + "Invalid rank!");
                    } else {
                        Main.sql.getReady();
                        if (g.equals(PermGroup.OWNER) && (sender.getName().equals("shizleshizle") || sender.getName().equals("iMelvin") || Perm.hasPerm(sender.getName(), PermGroup.OWNER))) {
                            Perm.updateGroup(user, g);
                        } else if (g.equals(PermGroup.LEAD_DEVELOPER) && sender.getName().equals("shizleshizle")) {
                            Perm.updateGroup(user, g);
                        } else if (g.equals(PermGroup.DEVELOPER) && (sender.getName().equals("shizleshizle") || sender.getName().equals("iMelvin")
                                || Perm.hasPerm(sender.getName(), PermGroup.DEVELOPER))) {
                            Perm.updateGroup(user, g);
                        } else if (g.equals(PermGroup.MANAGER) && ((sender.getName().equals("shizleshizle") || sender.getName().equals("iMelvin")))
                                || Perm.hasPerm(sender.getName(), PermGroup.MANAGER)) {
                            Perm.updateGroup(user, g);
                        } else if (g.getId() < PermGroup.MANAGER.getId() && Perm.hasPerm(sender.getName(), PermGroup.MANAGER)) {
                            Perm.updateGroup(user, g);
                        } else {
                            sender.sendMessage(PREFIX + "You can not set this rank!");
                            return true;
                        }
                        if (user.equals(sender.getName())) {
                            sender.sendMessage(PREFIX + "Your rank has been updated to " + GOLD + g.getName() + YELLOW + "!");
                        } else {
                            sender.sendMessage(PREFIX + "You have updated " + GOLD + args[1] + YELLOW + "'s rank to "
                                    + GOLD + g.getName() + YELLOW + "!");
                            Player t = Bukkit.getPlayer(user);
                            assert t != null;
                            if (Bukkit.getOnlinePlayers().contains(t)) {
                                t.sendMessage(PREFIX + "Your rank has been updated to " + GOLD + g.getName() + YELLOW + " by " + GOLD
                                        + senderName + YELLOW + "!");
                            }
                        }
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("add")) {
                    PermGroup pg = Perm.getGroup(args[1]);
                    if (pg == null) {
                        sender.sendMessage(PREFIX + "Invalid group!");
                    } else {
                        String perm = args[2].trim();
                        if (!PermissionGroup.getPermissions(pg).contains(perm)) {
                            PermissionGroup.addPermission(pg, perm);
                            sender.sendMessage(PREFIX + "You have added '" + GOLD + perm + YELLOW + "' to " + GOLD + pg.getName()
                                    + YELLOW + "!");
                        } else {
                            sender.sendMessage(PREFIX + "Group " + GOLD + pg.getName() + YELLOW + " already has permission '"
                                    + GOLD + perm + YELLOW + "'!");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("remove")) {
                    PermGroup pg = Perm.getGroup(args[1]);
                    if (pg == null) {
                        sender.sendMessage(PREFIX + "Invalid group!");
                    } else {
                        String perm = args[2].trim();
                        if (PermissionGroup.getPermissions(pg).contains(perm)) {
                            PermissionGroup.removePermission(pg, perm);
                            sender.sendMessage(PREFIX + "You have removed '" + GOLD + perm + YELLOW + "' from " + GOLD
                                    + pg.getName() + YELLOW + "!");
                        } else {
                            sender.sendMessage(PREFIX + "Group " + GOLD + pg.getName() + YELLOW + " does not have permission '"
                                    + GOLD + perm + YELLOW + "'!");
                        }
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/rank <list|set|get|add|remove> [user|rank] [rank|permission]");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/rank <list|set|get> [user] [rank]");
            }
        } else {
            ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/rank");
        }
        return false;
    }
}