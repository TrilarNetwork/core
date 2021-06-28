package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import me.shizleshizle.core.utils.StringHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Spawnmob implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Spawn-Mobs" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawnmob")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                    if (args.length == 1) {
                        String entity = args[0].trim();
                        if (isEntity(entity)) {
                            p.getWorld().spawnEntity(p.getLocation(), getEntityFromString(entity));
                            p.sendMessage(PREFIX + "Spawned one mob of " + GOLD + StringHelper.normalCase(entity) + YELLOW + "!");
                        } else {
                            p.sendMessage(PREFIX + "You must enter a valid entity type!");
                        }
                    } else if (args.length == 2) {
                        String entity = args[0].trim();
                        if (isEntity(entity)) {
                            if (Numbers.isNumber(args[1])) {
                                int amount = Numbers.getInt(args[1]);
                                for (int i = 0; i < amount; i++) {
                                    p.getWorld().spawnEntity(p.getLocation(), getEntityFromString(entity));
                                }
                            } else {
                                p.sendMessage(PREFIX + "You must enter a number!");
                            }
                            p.getWorld().spawnEntity(p.getLocation(), getEntityFromString(entity));
                            p.sendMessage(PREFIX + "Spawned one mob of " + GOLD + StringHelper.normalCase(entity) + YELLOW + "!");
                        } else {
                            p.sendMessage(PREFIX + "You must enter a valid entity type!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/spawnmob <name> [amount]");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/spawnmob");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/spawnmob");
            }
        }
        return false;
    }

    private EntityType getEntityFromString(String entity) {
        return EntityType.valueOf(entity.toUpperCase());
    }

    private boolean isEntity(String entity) {
        try {
            EntityType.valueOf(entity.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
