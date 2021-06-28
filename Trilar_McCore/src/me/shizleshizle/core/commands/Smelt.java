package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.StringToMaterial;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

import static org.bukkit.ChatColor.*;

public class Smelt implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Smelt" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("smelt")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.MEMBER)) {
                    if (args.length != 0) {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/smelt");
                        return false;
                    }
                    ItemStack item = p.getItemInMainHand();
                    ItemStack smelted = smeltOre(item);
                    if (smelted != null) {
                        p.setItemInHand(smelted);
                        p.sendMessage(PREFIX + "Smelted!");
                    } else {
                        p.sendMessage(PREFIX + "You must have an ore in your hand!");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/smelt");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/smelt");
            }
        }
        return false;
    }

    @Nullable
    private ItemStack smeltOre(ItemStack ore) {
        String type = ore.getType().toString();
        final int oreSuffix = 4;
        final int deepslatePrefix = 10;
        final int netherPrefix = 7;
        if (type.endsWith("ORE")) {
            type = type.substring(0, type.length() - oreSuffix);
        }
        if (type.startsWith("DEEPSLATE_")) {
            type = type.substring(deepslatePrefix);
        }
        if (type.equalsIgnoreCase("LAPIS")) {
            type = type + "_LAZULI";
        }
        if (type.startsWith("NETHER_")) {
            type = type.substring(netherPrefix);
        }
        if (type.contains("COPPER") || type.contains("GOLD") || type.contains("IRON") || type.contains("NETHERITE")) {
            type = type + "_INGOT";
        }
        ItemStack smelted = null;
        if (StringToMaterial.isMaterial(type)) {
            smelted = new ItemStack(StringToMaterial.getMaterialFromString(type), ore.getAmount());
        }
        return smelted;
    }
}
