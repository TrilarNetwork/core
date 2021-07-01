package me.shizleshizle.core.commands.bansystem;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Staffmode implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("staffmode")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                    GUI.openStaffGUI(p);
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/staffmode");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/staffmode");
            }
        }
        return false;
    }
}
