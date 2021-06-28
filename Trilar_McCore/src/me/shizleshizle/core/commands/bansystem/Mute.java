package me.shizleshizle.core.commands.bansystem;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

public class Mute implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mute")) {
            if (args.length >= 1 && args.length < 3) {
                Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                if (targetPlayer != null) {
                    User target = new User(targetPlayer);
                    if (target.isMuted()) {
                        sender.sendMessage(Ban.PREFIX + "Player " + GOLD + target + YELLOW + " has already been muted, this mute will overwrite current mute.");
                    }
                    String senderName = (sender instanceof Player) ? GOLD + sender.getName() + YELLOW : GOLD + "Console" + YELLOW;
                    if (sender instanceof Player) {
                        User p = new User((Player) sender);
                        if (!Perm.hasPerm(p, PermGroup.HELPER)) {
                            ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/mute");
                            return false;
                        }
                    }
                    if (args.length == 2) {
                        String time = args[1];
                        String lastLetter = time.substring(time.length() - 1);
                        String parseToNumber = time.substring(0, time.length() - 1);
                        int number = 0;
                        if (Numbers.isNumber((parseToNumber))) {
                            number = Numbers.getInt(time.substring(0, time.length() - 1));
                        } else {
                            sender.sendMessage(Ban.PREFIX + "Time must contain a number!");
                            return false;
                        }
                        Instant now = Instant.now();
                        Duration toAdd = Duration.ZERO;
                        switch (lastLetter) {
                            case "d":
                                toAdd = Duration.ofDays(number);
                                break;
                            case "h":
                                toAdd = Duration.ofHours(number);
                                break;
                            case "m":
                                toAdd = Duration.ofMinutes(number);
                                break;
                            case "s":
                                toAdd = Duration.ofSeconds(number);
                            default:
                                sender.sendMessage("Time must end in 'd' (day), 'h' (hour), 'm' (minutes), 's' (seconds)!");
                                break;
                        }
                        Instant muteDate = now.plus(toAdd);
                        Clock muteTime = Clock.fixed(muteDate, ZoneId.systemDefault());
                        target.mute(muteTime);
                        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.LONG).withLocale(Locale.GERMAN).withZone(ZoneId.systemDefault());
                        String displayTime = dtf.format(muteTime.instant());
                        displayTime = displayTime.substring(0, displayTime.length() - 5);
                        sender.sendMessage(Ban.PREFIX + "Player " + GOLD + target.getName() + YELLOW + " has been muted until " + GOLD + displayTime + YELLOW + "!");
                        target.sendMessage(Ban.PREFIX + "You have been muted by " + senderName + " until " + GOLD + displayTime + YELLOW + "!");
                    } else {
                        target.mute();
                        sender.sendMessage(Ban.PREFIX + "Player " + GOLD + target.getName() + YELLOW + " has been muted!");
                        target.sendMessage(Ban.PREFIX + "You have been muted by " + senderName);
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.PLAYER_OFFLINE, args[0]);
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/mute <player> <time>");
            }
        }
        return false;
    }
}
