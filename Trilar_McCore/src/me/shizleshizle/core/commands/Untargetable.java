package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Untargetable implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Untargetable" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("untargetable")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (p.hasPermission(PermGroup.MODERATOR)) {
                    if (p.isTargetable()) {
                        p.setTargetable(false);
                        p.sendMessage(PREFIX + "You are now " + GOLD + "untargetable" + YELLOW + "!");
                    } else {
                        p.setTargetable(true);
                        p.sendMessage(PREFIX + "You are now " + GOLD + "targetable" + YELLOW + "!");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/untargetable");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/untargetable");
            }
        }
        return false;
    }
}
