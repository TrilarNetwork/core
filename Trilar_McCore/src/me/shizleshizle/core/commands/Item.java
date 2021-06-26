package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import me.shizleshizle.core.utils.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.ChatColor.*;

public class Item implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Item" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("item")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                    if (args.length == 1 || args.length == 2 || args.length == 3) {
                        String item = args[0];
                        if (isMaterial(item)) {
                            if (args.length == 1) {
                                ItemStack is = new ItemStack(getMaterialFromString(item), 1);
                                p.getInventory().addItem(is);
                                p.sendMessage(PREFIX + "Added 1 of " + GOLD + StringHelper.normalCase(item) + YELLOW + " to your inventory!");
                                return false;
                            } else {
                                if (Numbers.isNumber(args[1])) {
                                    int amount = Numbers.getInt(args[1]);
                                    ItemStack is = new ItemStack(getMaterialFromString(item), amount);
                                    String itemName = StringHelper.normalCase(item);
                                    if (args.length == 3) {
                                        Player target = Bukkit.getPlayerExact(args[2]);
                                        if (target != null && target.isOnline()) {
                                            target.getInventory().addItem(is);
                                            target.sendMessage(PREFIX + GOLD + p.getName() + YELLOW + " has added " + GOLD + amount + YELLOW + " of " + GOLD
                                                    + StringHelper.normalCase(item) + YELLOW + "to your inventory!");
                                            p.sendMessage(PREFIX + "You have added " + GOLD + amount + YELLOW + " of " + GOLD + itemName + YELLOW + " to the inventory of " +
                                                    GOLD + target.getName() + YELLOW + "!");
                                        } else {
                                            ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.PLAYER_OFFLINE, args[1]);
                                        }
                                    } else {
                                        p.getInventory().addItem(is);
                                        p.sendMessage(PREFIX + "You have added " + GOLD + amount + YELLOW + " of " + GOLD + itemName + YELLOW + " to your inventory!");
                                    }
                                } else {
                                    p.sendMessage(PREFIX + "You must enter a number!");
                                }
                            }
                        } else {
                            p.sendMessage(PREFIX + "You must specify an item!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/item <item> [amount] [player]");
                    }
                }
            } else {
                if (args.length == 2 || args.length == 3) {

                } else {
                    ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/item <item> [amount] [player]");
                }
            }
        }
        return false;
    }

    private Material getMaterialFromString(String mat) {
        return Material.getMaterial(mat);
    }

    private boolean isMaterial(String mat) {
        return Material.getMaterial(mat) != null;
    }
}
