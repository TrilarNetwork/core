package me.shizleshizle.core.commands.teleportation;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tppos implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tppos")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Tp.PREFIX + "You must be a player to perform this command!");
            } else {
                Player px = (Player) sender;
                User p = new User(px);
                if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                    if (args.length == 3) {
                        int x = 0;
                        int y = 0;
                        int z = 0;
                        try {
                            x = Integer.parseInt(args[0]);
                            y = Integer.parseInt(args[1]);
                            z = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            p.sendMessage(Tp.PREFIX + "You must enter a number!");
                        }
                        p.teleport(new Location(p.getWorld(), x, y, z));
                        p.sendMessage(Tp.PREFIX + "You have been teleported to " + ChatColor.GOLD + x + ChatColor.YELLOW + ", " + ChatColor.GOLD + y + ChatColor.YELLOW + ", " + ChatColor.GOLD + z + ChatColor.YELLOW + "!");
                    } else if (args.length == 4) {
                        int x = 0;
                        int y = 0;
                        int z = 0;
                        try {
                            x = Integer.parseInt(args[0]);
                            y = Integer.parseInt(args[1]);
                            z = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            p.sendMessage(Tp.PREFIX + "You must enter a number!");
                        }
                        World w = Bukkit.getServer().getWorld(args[3]);
                        if (w == null) {
                            p.sendMessage(Tp.PREFIX + "This world does not exist or has not been loaded!");
                        } else {
                            p.teleport(new Location(w, x, y, z));
                            p.sendMessage(Tp.PREFIX + "You have been teleported to " + ChatColor.GOLD + x + ChatColor.YELLOW + ", " + ChatColor.GOLD + y + ChatColor.YELLOW + ", "
                                    + ChatColor.GOLD + z + ChatColor.YELLOW + " in world " + ChatColor.GOLD + w.getName() + ChatColor.YELLOW + "!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tppos <x> <y> <z> [world]");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tppos");
                }
            }
        }
        return false;
    }
}