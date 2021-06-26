package me.shizleshizle.core.commands.bansystem;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

public class Unmute implements CommandExecutor {

     public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
         if (cmd.getName().equalsIgnoreCase("unmute")) {
             if (args.length == 1) {
                 Player targetPlayer = Bukkit.getPlayerExact(args[0]);
                 if (targetPlayer != null) {
                     User target = new User(targetPlayer);
                     String senderName = (sender instanceof Player) ? GOLD + sender.getName() + YELLOW : GOLD + "Console" + YELLOW;
                     if (sender instanceof Player) {
                         User p = new User((Player) sender);
                         if (!Perm.hasPerm(p, PermGroup.HELPER)) {
                             ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/unmute");
                             return false;
                         }
                     }
                     if (target.isMuted()) {
                         target.unmute();
                         target.sendMessage(Ban.PREFIX + "You have been unmuted by " + senderName + "!");
                         sender.sendMessage(Ban.PREFIX + "You have unmuted " + GOLD + target.getName() + YELLOW + "!");
                     } else {
                         sender.sendMessage(Ban.PREFIX + "Player " + GOLD + target.getName() + YELLOW + " has not been muted!");
                     }
                 } else {
                     ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.PLAYER_OFFLINE, args[0]);
                 }
             } else {
                 ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/unban <player>");
             }
         }
         return false;
     }
}
