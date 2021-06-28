package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import me.shizleshizle.core.utils.StringHelper;
import me.shizleshizle.core.utils.StringToMaterial;
import org.bukkit.Bukkit;
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
                        if (StringToMaterial.isMaterial(item)) {
                            if (args.length == 1) {
                                ItemStack is = new ItemStack(StringToMaterial.getMaterialFromString(item), 1);
                                p.getInventory().addItem(is);
                                p.sendMessage(PREFIX + "Added 1 of " + GOLD + StringHelper.normalCase(item) + YELLOW + " to your inventory!");
                                return false;
                            } else {
                                if (Numbers.isNumber(args[1])) {
                                    int amount = Numbers.getInt(args[1]);
                                    ItemStack is = new ItemStack(StringToMaterial.getMaterialFromString(item), amount);
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
                                            ErrorMessages.doErrorMessage(p, Messages.PLAYER_OFFLINE, args[1]);
                                        }
                                    } else {
                                        p.getInventory().addItem(is);
                                        p.sendMessage(PREFIX + "You have added " + GOLD + amount + YELLOW + " of " + GOLD + itemName + YELLOW + " to your inventory!");
                                    }
                                } else {
                                    Player target = Bukkit.getPlayerExact(args[1]);
                                    if (target != null && target.isOnline()) {
                                        ItemStack is = new ItemStack(StringToMaterial.getMaterialFromString(item), 1);
                                        String itemName = StringHelper.normalCase(item);
                                        target.getInventory().addItem(is);
                                        target.sendMessage(PREFIX + GOLD + p.getName() + YELLOW + " has added " + GOLD + "1" + YELLOW + " of " + GOLD
                                                + StringHelper.normalCase(item) + YELLOW + "to your inventory!");
                                        p.sendMessage(PREFIX + "You have added " + GOLD + "1" + YELLOW + " of " + GOLD + itemName + YELLOW + " to the inventory of " +
                                                GOLD + target.getName() + YELLOW + "!");
                                    } else {
                                        p.sendMessage(PREFIX + "You must enter a number or a valid player name!");
                                    }
                                }
                            }
                        } else {
                            p.sendMessage(PREFIX + "You must specify an item!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/item <item> [amount] [player]");
                    }
                }
            } else {
                if (args.length == 2 || args.length == 3) {
                    String item = args[0];
                    if (Numbers.isNumber(args[1])) {
                        int amount = Numbers.getInt(args[1]);
                        ItemStack is = new ItemStack(StringToMaterial.getMaterialFromString(item), amount);
                        String itemName = StringHelper.normalCase(item);
                        if (args.length == 3) {
                            Player target = Bukkit.getPlayerExact(args[2]);
                            if (target != null && target.isOnline()) {
                                target.getInventory().addItem(is);
                                target.sendMessage(PREFIX + GOLD + "Console" + YELLOW + " has added " + GOLD + amount + YELLOW + " of " + GOLD
                                        + StringHelper.normalCase(item) + YELLOW + "to your inventory!");
                                sender.sendMessage(PREFIX + "You have added " + GOLD + amount + YELLOW + " of " + GOLD + itemName + YELLOW + " to the inventory of " +
                                        GOLD + target.getName() + YELLOW + "!");
                            } else {
                                ErrorMessages.doErrorMessage(sender, Messages.PLAYER_OFFLINE, args[1]);
                            }
                        } else {
                            sender.sendMessage(PREFIX + "You must specify a player to give the item to!");
                        }
                    } else {
                        sender.sendMessage(PREFIX + "You must enter a number!");
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/item <item> [amount] [player]");
                }
            }
        }
        return false;
    }
}
