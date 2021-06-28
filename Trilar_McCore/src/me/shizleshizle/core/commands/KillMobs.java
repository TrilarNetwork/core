package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import java.util.Collection;

import static org.bukkit.ChatColor.*;

public class KillMobs implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Kill-Mobs" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("killmobs")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.BUILDER)) {
                    if (args.length == 1) {
                        if (Numbers.isNumber(args[0])) {
                            double radius = Numbers.getInt((args[0]));
                            Location playerLoc = p.getLocation();
                            double upperX = playerLoc.getX() + radius;
                            double lowerX = playerLoc.getX() - radius;
                            double upperZ = playerLoc.getZ() + radius;
                            double lowerZ = playerLoc.getZ() - radius;
                            double upperY = playerLoc.getY() + radius;
                            double lowerY = playerLoc.getY() - radius;
                            Collection<Entity> mobs = p.getWorld().getEntities();
                            for (Entity mob : mobs) {
                                Location mobLoc = mob.getLocation();
                                boolean withinXRange = mobLoc.getX() > lowerX && mobLoc.getX() < upperX;
                                boolean withinZRange = mobLoc.getZ() > lowerZ && mobLoc.getZ() < upperZ;
                                boolean withinYRange = mobLoc.getY() > lowerY && mobLoc.getY() < upperY;
                                if (withinYRange || withinZRange || withinXRange) {
                                    if (mob instanceof Monster || mob instanceof Flying) {
                                        int damageToKill = 1000000;
                                        if (mob instanceof Monster) {
                                            Monster toKill = (Monster) mob;
                                            toKill.damage(damageToKill);
                                        } else {
                                            Flying toKill = (Flying) mob;
                                            toKill.damage(damageToKill);
                                        }
                                    }
                                }
                            }
                            p.sendMessage(PREFIX + "Killed all monsters and flying monsters within " + GOLD + radius + YELLOW + " blocks!");
                        } else {
                            p.sendMessage(PREFIX + "You must enter a number as radius!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/killmobs <radius>");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/killmobs");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/killmobs");
            }
        }
        return false;
    }
}
