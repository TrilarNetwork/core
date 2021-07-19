package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.StringHelper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.*;

public class Nameanimal implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "NameAnimal" + GOLD + " >> " + YELLOW;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("nameanimal")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (!p.hasPermission(PermGroup.VIP)) {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/nameanimal");
                    return false;
                }
                if (args.length != 1) {
                    ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/nameanimal <name|remove>");
                    return false;
                }
                Location loc = p.getEyeLocation();
                boolean setVisible = !args[0].equalsIgnoreCase("remove");
                String coloredName = ChatColor.translateAlternateColorCodes('&', args[0]);
                boolean hasSomething = false;
                for (Entity ent : p.getNearbyEntities(10, 10, 10)) {
                    if (ent instanceof Player) continue;
                    if (isLookingAt(p, ent)) {
                        if (setVisible) {
                            ent.setCustomNameVisible(true);
                            ent.setCustomName(coloredName);
                            p.sendMessage(PREFIX + "Named " + GOLD + StringHelper.normalCase(ent.getType().toString()) + YELLOW + " to " + RESET + coloredName);
                            hasSomething = true;
                        } else {
                            ent.setCustomNameVisible(false);
                            ent.setCustomName("");
                            p.sendMessage(PREFIX + "Removed the name of " + GOLD + StringHelper.normalCase(ent.getType().toString()) + YELLOW + "!");
                            hasSomething = true;
                        }
                    }
                }
                if (!hasSomething) {
                    p.sendMessage(PREFIX + "You must look at an entity to name or get closer!");
                }
            } else {
                sender.sendMessage(PREFIX + "You must be a player to perform this command!");
            }
        }
        return false;
    }

    private boolean isLookingAt(User p, Entity ent) {
        Location eye = p.getEyeLocation();
        Vector toEntity = ent.getLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());
        return dot > 0.99D;
    }
}
