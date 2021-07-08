package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.*;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Mccore implements CommandExecutor {
    public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Eliarant Core" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mccore")) {
            if (sender instanceof Player) {
                Player x = (Player) sender;
                User p = new User(x);
                if (!Perm.hasPerm(p, PermGroup.ADMIN)) {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/mccore");
                    return false;
                }
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    try {
                        if (Main.sql.hasConnection()) {
                            Main.sql.closeConnection();
                        }
                        Main.sql.openConnection();
                        Main.sql.setup();
                        PermissionGroup.setup();
                        PermUser.setup();
                        PrefixHelper.setup();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Main.c.saveAll();
                    Main.c.reloadAll();
                    Main.setupUtils();
                    sender.sendMessage(PREFIX + "You have successfully reloaded the databases and all yml files!");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/mccore reload");
            }
        }
        return false;
    }
}
