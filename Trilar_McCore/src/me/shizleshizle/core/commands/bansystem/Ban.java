package me.shizleshizle.core.commands.bansystem;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Objects;

import static org.bukkit.ChatColor.*;

public class Ban implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Ban-System" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ban")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                    if (args.length >= 2) {
                        String type = args[0];
                        String targetName = args[1];
                        StringBuilder sb = new StringBuilder();
                        if (type.equalsIgnoreCase("perm") || type.equalsIgnoreCase("permanent")) {
                            for (int i = 2; i < args.length; i++) {
                                sb.append(args[i]).append(" ");
                            }
                            String reason = sb.substring(0, sb.length() - 1);
                            reason = ChatColor.translateAlternateColorCodes('&', reason);
                            Bukkit.getBanList(BanList.Type.NAME).addBan(targetName, reason, null, p.getName());
                            p.sendMessage(PREFIX + "Player " + GOLD + targetName + YELLOW + " has been permanently banned!");
                            Player target = Bukkit.getPlayerExact(targetName);
                            if (target != null && target.isOnline()) {
                                target.kickPlayer(RED + "You have been banned: " + YELLOW + reason);
                            }
                        } else if (type.equalsIgnoreCase("temp") || type.equalsIgnoreCase("temporarily")) {
                            String date = args[2];
                            Calendar time = Calendar.getInstance();
                            int timeToAdd = Numbers.getInt(args[2].substring(0, args[2].length() - 1));
                            if (date.endsWith("d")) {
                                time.add(Calendar.DATE, timeToAdd);
                            } else if (date.endsWith("h")) {
                                time.add(Calendar.HOUR, timeToAdd);
                            } else if (date.endsWith("m")) {
                                time.add(Calendar.MINUTE, timeToAdd);
                            } else if (date.endsWith("s")) {
                                time.add(Calendar.SECOND, timeToAdd);
                            } else if (date.endsWith("month") || date.endsWith("months")) {
                                time.add(Calendar.MONTH, timeToAdd);
                            } else {
                                ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/ban <perm|temp|ip|lookup> <player> [time]");
                                return false;
                            }
                            for (int i = 3; i < args.length; i++) {
                                sb.append(args[i]).append(" ");
                            }
                            String reason = sb.substring(0, sb.length() - 1);
                            reason = ChatColor.translateAlternateColorCodes('&', reason);
                            Bukkit.getBanList(BanList.Type.NAME).addBan(targetName, reason, time.getTime(), p.getName());
                            p.sendMessage(PREFIX + "Player " + GOLD + targetName + YELLOW + " has been banned until " + GOLD + time.getTime() + YELLOW + "!");
                            Player target = Bukkit.getPlayerExact(targetName);
                            if (target != null && target.isOnline()) {
                                target.kickPlayer(RED + "You have been banned: " + YELLOW + reason);
                            }
                        } else if (type.equalsIgnoreCase("ip")) {
                            String toBan = "";
                            if (targetName.contains(".")) {
                                toBan = targetName;
                            } else {
                                Player target = Bukkit.getPlayerExact(targetName);
                                if (target != null) {
                                    toBan = Objects.requireNonNull(target.getAddress()).getAddress().toString();
                                }
                            }
                            for (int i = 3; i < args.length; i++) {
                                sb.append(args[i]).append(" ");
                            }
                            String reason = sb.substring(0, sb.length() - 1);
                            reason = ChatColor.translateAlternateColorCodes('&', reason);
                            Bukkit.getBanList(BanList.Type.IP).addBan(toBan, reason, null, p.getName());
                            if (toBan.contains(".")) {
                                p.sendMessage(PREFIX + "IP " + GOLD + targetName + YELLOW + " has been banned!");
                            } else {
                                p.sendMessage(PREFIX + "Player " + GOLD + targetName + YELLOW + "'s has been banned!");
                            }
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                if (Objects.requireNonNull(player.getAddress()).getAddress().toString().equals(toBan)) {
                                    player.kickPlayer(RED + "Your IP has been banned: " + YELLOW + reason);
                                }
                            }
                        } else if (type.equalsIgnoreCase("lookup")) {
                            p.sendMessage(GOLD + "-=[ " + YELLOW + BOLD + "Ban-System" + GOLD + " ]=-");
                            if (targetName.contains(".")) {
                                p.sendMessage(GOLD + "IP Address: " + YELLOW + targetName);
                                boolean isBanned = Bukkit.getBanList(BanList.Type.IP).isBanned(targetName);
                                String banned = isBanned ? GREEN + "true" : RED + "false";
                                p.sendMessage(GOLD + "Banned: " + banned);
                                if (isBanned) {
                                    BanEntry ban = Bukkit.getBanList(BanList.Type.IP).getBanEntry(targetName);
                                    assert ban != null;
                                    String banReason = ban.getReason();
                                    String reason = banReason == null ? "" : ChatColor.translateAlternateColorCodes('&', banReason);
                                    p.sendMessage(GOLD + "Created: " + YELLOW + ban.getCreated());
                                    p.sendMessage(GOLD + "Expires: " + YELLOW + ban.getExpiration());
                                    p.sendMessage(GOLD + "Reason: " + YELLOW + reason);
                                    p.sendMessage(GOLD + "Banned by: " + YELLOW + ban.getSource());
                                }
                            } else {
                                p.sendMessage(GOLD + "Player: " + YELLOW + targetName);
                                boolean isBanned = Bukkit.getBanList(BanList.Type.NAME).isBanned(targetName);
                                String banned = isBanned ? GREEN + "true" : RED + "false";
                                p.sendMessage(GOLD + "Banned: " + banned);
                                if (isBanned) {
                                    BanEntry ban = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(targetName);
                                    assert ban != null;
                                    String banReason = ban.getReason();
                                    String reason = banReason == null ? "" : ChatColor.translateAlternateColorCodes('&', banReason);
                                    p.sendMessage(GOLD + "Created: " + YELLOW + ban.getCreated());
                                    p.sendMessage(GOLD + "Expires: " + YELLOW + ban.getExpiration());
                                    p.sendMessage(GOLD + "Reason: " + YELLOW + reason);
                                    p.sendMessage(GOLD + "Banned by: " + YELLOW + ban.getSource());
                                }
                            }
                            p.sendMessage(GOLD + "==================");
                        } else {
                            ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/ban <perm|temp|ip|lookup> <player> [time]");
                            return false;
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/ban <perm|temp|ip|lookup> <player> [time]");
                        return false;
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/ban");
                }
            } else {
                if (args.length >= 2) {
                    String senderName = "Console";
                    String type = args[0];
                    String targetName = args[1];
                    StringBuilder sb = new StringBuilder();
                    if (type.equalsIgnoreCase("perm") || type.equalsIgnoreCase("permanent")) {
                        for (int i = 2; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String reason = sb.substring(0, sb.length() - 1);
                        reason = ChatColor.translateAlternateColorCodes('&', reason);
                        Bukkit.getBanList(BanList.Type.NAME).addBan(targetName, reason, null, senderName);
                        sender.sendMessage(PREFIX + "Player " + GOLD + targetName + YELLOW + " has been permanently banned!");
                        Player target = Bukkit.getPlayerExact(targetName);
                        if (target != null && target.isOnline()) {
                            target.kickPlayer(RED + "You have been banned: " + YELLOW + reason);
                        }
                    } else if (type.equalsIgnoreCase("temp") || type.equalsIgnoreCase("temporarily")) {
                        String date = args[2];
                        Calendar time = Calendar.getInstance();
                        int timeToAdd = Numbers.getInt(args[2].substring(0, args[2].length() - 1));
                        if (date.endsWith("d")) {
                            time.add(Calendar.DATE, timeToAdd);
                        } else if (date.endsWith("h")) {
                            time.add(Calendar.HOUR, timeToAdd);
                        } else if (date.endsWith("m")) {
                            time.add(Calendar.MINUTE, timeToAdd);
                        } else if (date.endsWith("s")) {
                            time.add(Calendar.SECOND, timeToAdd);
                        } else if (date.endsWith("month") || date.endsWith("months")) {
                            time.add(Calendar.MONTH, timeToAdd);
                        } else {
                            ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/ban <perm|temp|ip|lookup> <player> [time]");
                            return false;
                        }
                        for (int i = 3; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String reason = sb.substring(0, sb.length() - 1);
                        reason = ChatColor.translateAlternateColorCodes('&', reason);
                        Bukkit.getBanList(BanList.Type.NAME).addBan(targetName, reason, time.getTime(), senderName);
                        sender.sendMessage(PREFIX + "Player " + GOLD + targetName + YELLOW + " has been banned until " + GOLD + time.getTime() + YELLOW + "!");
                        Player target = Bukkit.getPlayerExact(targetName);
                        if (target != null && target.isOnline()) {
                            target.kickPlayer(RED + "You have been banned: " + YELLOW + reason);
                        }
                    } else if (type.equalsIgnoreCase("ip")) {
                        String toBan = "";
                        if (targetName.contains(".")) {
                            toBan = targetName;
                        } else {
                            Player target = Bukkit.getPlayerExact(targetName);
                            if (target != null) {
                                toBan = Objects.requireNonNull(target.getAddress()).getAddress().toString();
                            }
                        }
                        for (int i = 3; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String reason = sb.substring(0, sb.length() - 1);
                        reason = ChatColor.translateAlternateColorCodes('&', reason);
                        Bukkit.getBanList(BanList.Type.IP).addBan(toBan, reason, null, senderName);
                        if (toBan.contains(".")) {
                            sender.sendMessage(PREFIX + "IP " + GOLD + targetName + YELLOW + " has been banned!");
                        } else {
                            sender.sendMessage(PREFIX + "Player " + GOLD + targetName + YELLOW + "'s has been banned!");
                        }
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (Objects.requireNonNull(player.getAddress()).getAddress().toString().equals(toBan)) {
                                player.kickPlayer(RED + "Your IP has been banned: " + YELLOW + reason);
                            }
                        }
                    } else if (type.equalsIgnoreCase("lookup")) {
                        sender.sendMessage(GOLD + "-=[ " + YELLOW + BOLD + "Ban-System" + GOLD + " ]=-");
                        if (targetName.contains(".")) {
                            sender.sendMessage(GOLD + "IP Address: " + YELLOW + targetName);
                            boolean isBanned = Bukkit.getBanList(BanList.Type.IP).isBanned(targetName);
                            String banned = isBanned ? GREEN + "true" : RED + "false";
                            sender.sendMessage(GOLD + "Banned: " + banned);
                            if (isBanned) {
                                BanEntry ban = Bukkit.getBanList(BanList.Type.IP).getBanEntry(targetName);
                                assert ban != null;
                                String banReason = ban.getReason();
                                String reason = banReason == null ? "" : ChatColor.translateAlternateColorCodes('&', banReason);
                                sender.sendMessage(GOLD + "Created: " + YELLOW + ban.getCreated());
                                sender.sendMessage(GOLD + "Expires: " + YELLOW + ban.getExpiration());
                                sender.sendMessage(GOLD + "Reason: " + YELLOW + reason);
                                sender.sendMessage(GOLD + "Banned by: " + YELLOW + ban.getSource());
                            }
                        } else {
                            sender.sendMessage(GOLD + "Player: " + YELLOW + targetName);
                            boolean isBanned = Bukkit.getBanList(BanList.Type.NAME).isBanned(targetName);
                            String banned = isBanned ? GREEN + "true" : RED + "false";
                            sender.sendMessage(GOLD + "Banned: " + banned);
                            if (isBanned) {
                                BanEntry ban = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(targetName);
                                assert ban != null;
                                String banReason = ban.getReason();
                                String reason = banReason == null ? "" : ChatColor.translateAlternateColorCodes('&', banReason);
                                sender.sendMessage(GOLD + "Created: " + YELLOW + ban.getCreated());
                                sender.sendMessage(GOLD + "Expires: " + YELLOW + ban.getExpiration());
                                sender.sendMessage(GOLD + "Reason: " + YELLOW + reason);
                                sender.sendMessage(GOLD + "Banned by: " + YELLOW + ban.getSource());
                            }
                        }
                        sender.sendMessage(GOLD + "==================");
                    } else {
                        ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/ban <perm|temp|ip|lookup> <player> [time]");
                        return false;
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/ban <perm|temp|ip|lookup> <player> [time]");
                    return false;
                }
            }
        }
        return false;
    }
}
