package me.shizleshizle.core.commands.teleportation;

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

import java.util.HashMap;

public class Tpa implements CommandExecutor {
    public static HashMap<String, String> tpa = new HashMap<>();
    public static boolean toTP = false;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpa")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Tp.PREFIX + "You must be a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.MEMBER)) {
                    if (args.length == 1) {
                        User t = new User(Bukkit.getPlayer(args[0]));
                        if (!t.isOnline()) {
                            ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[0]);
                        } else {
                            if (!t.hasTpDisabled()) {
                                tpa.put(p.getName(), t.getName());
                                p.sendMessage(Tp.PREFIX + "Request sent to " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + "!");
                                t.sendMessage(Tp.PREFIX + "You have received a teleport request from " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW
                                        + " to teleport to your location! Type " + ChatColor.GOLD + "/tpaccept" + ChatColor.YELLOW + " to accept their request or type "
                                        + ChatColor.GOLD + "/tpdeny" + ChatColor.YELLOW + " to deny their request");
                                toTP = true;
                            } else {
                                p.sendMessage(Tp.PREFIX + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + " has disabled teleportation!");
                            }
                        }
                    } else if (args.length >= 2) {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpa <player>");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tpa");
                }
            }
        }
        return false;
    }
}