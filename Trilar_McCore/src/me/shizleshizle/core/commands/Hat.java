package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat implements CommandExecutor {
    public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Hat" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hat")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(PREFIX + "You must be a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.MEMBER)) {
                    if (args.length == 0) {
                        if (p.getItemInMainHand() != null) {
                            ItemStack i = p.getItemInMainHand();
                            if (p.getInventory().getHelmet() != null) {
                                p.setItemInHand(p.getInventory().getHelmet());
                                p.sendMessage(PREFIX + "Enjoy your hat!");
                            } else {
                                p.setItemInHand(new ItemStack(Material.AIR));
                                p.sendMessage(PREFIX + "Enjoy your hat!");
                            }
                            p.getInventory().setHelmet(i);
                        } else {
                            p.sendMessage(PREFIX + "You have nothing in your hand!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/hat");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/hat");
                }
            }
        }
        return false;
    }
}