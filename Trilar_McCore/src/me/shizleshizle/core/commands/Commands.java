package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.StringHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                    for (String comm : commands) {
                        sender.sendMessage(comm);
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/commands <command|list> [enable|disable]");
                }
            } else if (args.length == 2) {
                String command = args[0];
                if (Main.cman.exists(command)) {
                    String newSetting = args[1];
                    boolean enabled = Main.cman.isEnabled(command);
                    if (newSetting.equalsIgnoreCase("enable")) {
                        if (!enabled) {
                            Main.cman.enableCommand(command);
                            Main.disabledCommands.remove(command);
                            sender.sendMessage(PREFIX + "You have " + GOLD + "enabled" + YELLOW + " the command: " + GOLD + StringHelper.normalCase(command) + YELLOW + "!");
                        } else {
                            sender.sendMessage(PREFIX + "Command " + GOLD + command + YELLOW + " has already been enabled!");
                        }
                    } else if (newSetting.equalsIgnoreCase("disable")) {
                        if (enabled) {
                            Main.cman.unregisterCommand(newSetting);
                            Main.disabledCommands.add(command);
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
