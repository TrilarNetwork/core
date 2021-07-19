package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

public class Reloader implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reloader")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (!p.hasPermission(PermGroup.ADMIN)) {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/reloader");
                }
            }
            PluginManager pm = Bukkit.getPluginManager();
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("Trilar_Core")) {
                    sender.sendMessage(Main.PREFIX + "Plugin cannot reload itself!");
                    return false;
                }
                Plugin p = Bukkit.getPluginManager().getPlugin(args[0]);
                if (p != null) {
                    pm.disablePlugin(p);
                    pm.enablePlugin(p);
                    sender.sendMessage(Main.PREFIX + "Plugin " + GOLD + p.getName() + YELLOW + " has been reloaded!");
                } else {
                    sender.sendMessage(Main.PREFIX + "Plugin " + GOLD + args[0] + YELLOW + " has not been found! Please check your spelling.");
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("Trilar_Core")) {
                    sender.sendMessage(Main.PREFIX + "Plugin cannot reload itself!");
                    return false;
                }
                if (args[1].equalsIgnoreCase("disable")) {
                    Plugin p = Bukkit.getPluginManager().getPlugin(args[0]);
                    if (p != null) {
                        pm.disablePlugin(p);
                        sender.sendMessage(Main.PREFIX + "Plugin " + GOLD + p.getName() + YELLOW + " has been disabled!");
                    } else {
                        sender.sendMessage(Main.PREFIX + "Plugin " + GOLD + args[0] + YELLOW + " has not been found! Please check your spelling.");
                    }
                } else if (args[1].equalsIgnoreCase("enable")) {
                    Plugin p = Bukkit.getPluginManager().getPlugin(args[0]);
                    if (p != null) {
                        pm.enablePlugin(p);
                        sender.sendMessage(Main.PREFIX + "Plugin " + GOLD + p.getName() + YELLOW + " has been enabled!");
                    } else {
                        sender.sendMessage(Main.PREFIX + "Plugin " + GOLD + args[0] + YELLOW + " has not been found! Please check your spelling.");
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/reloader <plugin> [disable|enable]");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/reloader <plugin> [disable|enable]");
            }
        }
        return false;
    }
}
