package me.shizleshizle.core.commands.bansystem;

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

import static org.bukkit.ChatColor.*;

public class Kick implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
         if (cmd.getName().equalsIgnoreCase("kick")) {
             if (args.length >= 1) {
                 Player target = Bukkit.getPlayerExact(args[0]);
                 String senderName;
                 if (sender instanceof Player) {
                     senderName = GOLD + sender.getName();
                     User p = new User((Player) sender);
                     if (!Perm.hasPerm(p, PermGroup.HELPER)) {
                         ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/kick");
                         return false;
                     }
                 } else {
                     senderName = GOLD + "Console";
                 }
                 if (target != null && target.isOnline()) {
                     if (args.length == 1) {
                         target.kickPlayer(RED + "You have been kicked by " + senderName + RED + "!");
                         sender.sendMessage(Ban.PREFIX + "Player " + GOLD + target.getName() + YELLOW + " has been kicked!");
                     } else {
                         StringBuilder sb = new StringBuilder();
                         for (String s : args) {
                             if (s.equals(args[0])) continue;
                             sb.append(s).append(" ");
                         }
                         String reason = sb.substring(0, sb.length() - 1);
                         reason = ChatColor.translateAlternateColorCodes('&', reason);
                         target.kickPlayer(RED + "You have been kicked by " + senderName + RED + " for: " + YELLOW + reason);
                         sender.sendMessage(Ban.PREFIX + "Player " + GOLD + target.getName() + YELLOW + " has been kicked for: " + GOLD + reason);
                     }
                 } else {
                     ErrorMessages.doErrorMessage(sender, Messages.PLAYER_OFFLINE, args[0]);
                 }
             } else {
                 ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/kick <player> [reason]");
             }
         }
        return false;
    }
}
