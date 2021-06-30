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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static org.bukkit.ChatColor.*;

public class Name implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Item Name" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("name")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                    if (args.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        boolean description = args[0].equalsIgnoreCase("desc") || args[0].equalsIgnoreCase("description");
                        int startCounter = description ? 1 : 0;
                        for (int i = startCounter; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        ItemStack itemInHand = p.getItemInMainHand();
                        String newName = ChatColor.translateAlternateColorCodes('&', sb.toString().trim());
                        if (itemInHand != null) {
                            ItemMeta meta = itemInHand.getItemMeta();
                            if (meta != null) {
                                if (description) {
                                    meta.setLore(Collections.singletonList(newName));
                                } else {
                                    meta.setDisplayName(newName);
                                }
                            } else {
                                p.sendMessage(PREFIX + "Something went wrong! Item does not have item meta!");
                                return false;
                            }
                            itemInHand.setItemMeta(meta);
                        } else {
                            p.sendMessage(PREFIX + "You must have an item in your hand!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/name <nameofitem>");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/name");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_ONLY_CMD, "/name");
            }
        }
        return false;
    }
}
