package me.shizleshizle.core.commands.messaging;

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

public class SocialSpy implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Social Spy" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("socialspy")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                    if (args.length != 0) {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/socialspy");
                        return false;
                    }
                    if (p.hasSocialSpyEnabled()) {
                        p.setSocialSpyActive(false);
                        p.sendMessage(PREFIX + "You have " + GOLD + "disabled" + YELLOW + " social spy!");
                    } else {
                        p.setSocialSpyActive(true);
                        p.sendMessage(PREFIX + "You have " + GOLD + "enabled" + YELLOW + " social spy!");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/socialspy");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/socialspy");
            }
        }
        return false;
    }
}
