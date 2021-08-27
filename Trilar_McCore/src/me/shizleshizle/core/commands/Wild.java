package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Wild implements CommandExecutor {
    public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Workbench" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
    public static ArrayList<String> l = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("wild")) {
            if (!Main.isLobby()) {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.MEMBER)) {
                    if (p.getWorld().getEnvironment() == World.Environment.THE_END || p.getWorld().getEnvironment() == World.Environment.NETHER) {
                        p.sendMessage(PREFIX + "You are not allowed to do this in The End or the Nether!");
                        return true;
                    } else {
                        p.wild();
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/wild");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "wild");
            }
        }
        return false;
    }
}