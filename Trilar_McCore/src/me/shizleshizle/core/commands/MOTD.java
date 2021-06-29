package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class MOTD implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Spawn-Mobs" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("motd")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (args.length == 0) {
                    p.showMOTD();
                } else {
                    if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                        StringBuilder sb = new StringBuilder();
                        int startCount = 0;
                        if (args[0].equals("set")) {
                            startCount = 1;
                        }
                        for (int i = startCount; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        String motd = sb.toString().trim();
                        p.setMOTD(motd);
                        p.sendMessage(PREFIX + "MOTD set to: " + ChatColor.translateAlternateColorCodes('&', motd));
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/motd <newMOTD>");
                    }
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/motd");
            }
        }
        return false;
    }
}
