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

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

public class Repair implements CommandExecutor {
    public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Repair" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("repair")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(PREFIX + "You must be a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.BUILDER)) {
                    if (args.length == 0) {
                        p.repairInHand();
                        p.sendMessage(PREFIX + "You have repaired the item in your hand!");
                    } else if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("all")) {
                            p.repairAll();
                            p.sendMessage(PREFIX + "You have repaired all your items!");
                        } else if (args[0].equalsIgnoreCase("armor")) {
                            p.repairArmor();
                            p.sendMessage(PREFIX + "You have repaired all your armor!");
                        } else {
                            ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/repair <all|armor> [player]");
                        }
                    } else if (args.length == 2) {
                        if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                            Player targetPlayer = Bukkit.getPlayerExact(args[1]);
                            if (targetPlayer != null && targetPlayer.isOnline()) {
                                User target = new User(targetPlayer);
                                if (args[0].equalsIgnoreCase("all")) {
                                    target.repairAll();
                                    target.sendMessage(PREFIX + "All items in your inventory have been repaired by " + GOLD + p.getName() + YELLOW + "!");
                                    p.sendMessage(PREFIX + "You have repaired all items for " + GOLD + target.getName() + YELLOW + "!");
                                } else if (args[0].equalsIgnoreCase("armor")) {
                                    target.repairArmor();
                                    target.sendMessage(PREFIX + "Your armor has been repaired by " + GOLD + p.getName() + YELLOW + "!");
                                    p.sendMessage(PREFIX + "You have repaired armor for " + GOLD + target.getName() + YELLOW + "!");
                                } else {
                                    ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/repair <all|armor> [player]");
                                }
                            } else {
                                ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
                            }
                        } else {
                            ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/repair [player]");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/repair <all|armor> [player]");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/repair");
                }
            }
        }
        return false;
    }
}