package me.shizleshizle.core.commands.messaging;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Msg implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Message" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("msg")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (args.length >= 2) {
                    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                    if (targetPlayer != null && targetPlayer.isOnline()) {
                        User target = new User(targetPlayer);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String msg = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
                        p.message(target, msg);
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/msg <player> <message>");
                }
            } else {
                if (args.length >= 2) {
                    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                    if (targetPlayer != null && targetPlayer.isOnline()) {
                        User target = new User(targetPlayer);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String msg = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
                        target.sendMessage(GOLD + "Console" + YELLOW + " > " + GOLD + "You" + YELLOW + " >> " + msg);
                        target.sendMessage(PREFIX + "You cannot respond to the Console.");
                    } else {
                        ErrorMessages.doErrorMessage(sender, Messages.PLAYER_OFFLINE, args[0]);
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/msg <player> <message>");
                }
            }
        }
        return false;
    }
}
