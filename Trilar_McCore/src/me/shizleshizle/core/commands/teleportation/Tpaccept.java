package me.shizleshizle.core.commands.teleportation;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpaccept implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Tp.PREFIX + "You must be a player to perform this command!");
            } else {
                Player x = (Player) sender;
                User p = new User(x);
                if (Perm.hasPerm(p, PermGroup.MEMBER)) {
                    if (args.length == 0) {
                        String n = "";
                        if (Tpa.tpa.containsValue(p.getName())) {
                            for (String s : Tpa.tpa.keySet()) {
                                if (Tpa.tpa.get(s).equals(p.getName())) {
                                    n = s;
                                }
                            }
                            Player t = Bukkit.getPlayer(n);
                            if (t == null) {
                                p.sendMessage(Tp.PREFIX + "Player is not online!");
                            } else {
                                p.sendMessage(Tp.PREFIX + "You have accepted the teleport request of " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + ".");
                                t.sendMessage(Tp.PREFIX + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has accepted your request, teleporting in " + ChatColor.GOLD + Main.tpTime
                                        + ChatColor.YELLOW + " seconds!");
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.p, () -> {
                                    if (Tpa.toTP) {
                                        t.teleport(p.getLocation());
                                        t.sendMessage(Tp.PREFIX + "You have been teleported to " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                                        Tpa.tpa.remove(t.getName());
                                    }
                                }, Main.tpTime * 20L);
                            }
                        } else if (Main.tpahere.containsValue(p.getName())) {
                            for (String s : Main.tpahere.keySet()) {
                                if (Main.tpahere.get(s).equals(p.getName())) {
                                    n = s;
                                }
                            }
                            Player t = Bukkit.getPlayer(n);
                            if (t == null) {
                                p.sendMessage(Tp.PREFIX + "Player is not online!");
                            } else {
                                p.sendMessage(Tp.PREFIX + "You have accepted the teleport request of " + ChatColor.GOLD + t.getName() + ChatColor.YELLOW + ", teleporting in " + ChatColor.GOLD
                                        + Main.tpTime + ChatColor.YELLOW + " seconds!");
                                t.sendMessage(Tp.PREFIX + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has accepted your request!");
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.p, () -> {
                                    if (Tpahere.toTP) {
                                        p.teleport(t.getLocation());
                                        p.sendMessage(Tp.PREFIX + "You have been teleported to " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                                        Main.tpahere.remove(t.getName());
                                    }
                                }, Main.tpTime * 20L);
                            }
                        } else {
                            p.sendMessage(Tp.PREFIX + "You do not have a pending request!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tpaccept");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/tpaccept");
                }
            }
        }
        return false;
    }
}