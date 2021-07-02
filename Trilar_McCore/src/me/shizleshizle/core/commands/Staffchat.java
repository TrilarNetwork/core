package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Staffchat implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Staff-Chat" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("staffchat")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.TRIAL_HELPER)) {
                    if (args.length != 0) {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/staffchat");
                        return false;
                    }
                    if (p.isTalkingInStaffChat()) {
                        p.setStaffChat(false);
                        p.sendMessage(PREFIX + "You have " + GOLD + "disabled" + YELLOW + " staff-chat!");
                    } else {
                        p.setStaffChat(true);
                        p.sendMessage(PREFIX + "You have " + GOLD + "enabled" + YELLOW + " staff-chat!");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/staffchat");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/staffchat");
            }
        }
        return false;
    }
}
