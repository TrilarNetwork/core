package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.StringHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;

import static org.bukkit.ChatColor.*;

public class Commands implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Commands" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("commands")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (!Perm.hasPerm(p, PermGroup.MANAGER)) {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/commands");
                }
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {
                    String[] commands = Main.cman.getCommandStatuses();
                    if (sender instanceof Player) {
                        User p = new User((Player) sender);
                        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                        BookMeta bm = (BookMeta) book.getItemMeta();
                        assert bm != null;
                        bm.setAuthor(DARK_AQUA + "shizleshizle");
                        bm.setTitle(AQUA + "Commands");
                        final int amountOfCmdOnPage = 13;
                        final int amountOfPages = Math.round((float)commands.length / (float)amountOfCmdOnPage);
                        StringBuilder sb = new StringBuilder();
                        int counter = 0;
                        for (String command : commands) {
                            sb.append(command).append("\n");
                            counter++;
                            if (counter == 13) {
                                counter = 0;
                                bm.addPage(sb.toString().trim());
                                sb.delete(0, sb.length());
                            }
                        }
                        bm.addPage(sb.toString().trim());
                        book.setItemMeta(bm);
                        p.getInventory().addItem(book);
                    } else {
                        for (String commd : commands) {
                            sender.sendMessage(commd);
                        }
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/commands <command|list> [enable|disable]");
                }
            } else if (args.length == 2) {
                String command = args[0].toLowerCase();
                if (Main.cman.exists(command)) {
                    String newSetting = args[1];
                    boolean enabled = Main.cman.isEnabled(command);
                    if (newSetting.equalsIgnoreCase("enable")) {
                        if (!enabled) {
                            Main.cman.enableCommand(command);
                            sender.sendMessage(PREFIX + "You have " + GOLD + "enabled" + YELLOW + " the command: " + GOLD + StringHelper.normalCase(command) + YELLOW + "!");
                        } else {
                            sender.sendMessage(PREFIX + "Command " + GOLD + command + YELLOW + " has already been enabled!");
                        }
                    } else if (newSetting.equalsIgnoreCase("disable")) {
                        if (enabled) {
                            Main.cman.unregisterCommand(command);
                            sender.sendMessage(PREFIX + "You have " + GOLD + "disabled" + YELLOW + " the command: " + GOLD + StringHelper.normalCase(command) + YELLOW + "!");
                        } else {
                            sender.sendMessage(PREFIX + "Command " + GOLD + command + YELLOW + " has already been disabled!");
                        }
                    } else {
                        sender.sendMessage(PREFIX + "Command " + GOLD + command + YELLOW + " does not exist!");
                    }
                } else {
                    sender.sendMessage(PREFIX + "Command " + GOLD + command + YELLOW + " does not exist!");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/commands <command|list> [enable|disable]");
            }
        }
        return false;
    }
}
