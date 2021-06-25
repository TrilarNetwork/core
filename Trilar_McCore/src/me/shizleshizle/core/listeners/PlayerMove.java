package me.shizleshizle.core.listeners;

import me.shizleshizle.core.commands.teleportation.Tp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.Freeze;
import me.shizleshizle.core.commands.Wild;
import me.shizleshizle.core.commands.teleportation.Tpa;
import me.shizleshizle.core.commands.teleportation.Tpahere;
import me.shizleshizle.core.objects.User;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
    	Player x = e.getPlayer();
        User p = new User(x);
        if (p.isFrozen()) {
        	if (e.getTo() != e.getFrom()) {
        		e.setTo(e.getFrom());
        	}
            if (!Freeze.hasSent) {
                p.sendMessage(Freeze.PREFIX + "You have been frozen, you can't move!");
                Freeze.hasSent = true;
            }
        }
        if (Tpa.tpa.containsKey(p.getName())) {
            if (e.getTo() != e.getFrom()) {
                p.sendMessage(Tp.PREFIX + "Teleportation cancelled! You moved!");
                Player t = Bukkit.getPlayer(Tpa.tpa.get(p.getName()));
                if (t == null) {
                    p.sendMessage(Tp.PREFIX + "Player is not online!");
                } else {
                    t.sendMessage(Tp.PREFIX + "Teleportation cancelled! " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " moved!");
                    Tpa.toTP = false;
                    Tpa.tpa.remove(p.getName());
                }
            }
        }
        if (Main.tpahere.containsValue(p.getName())) {
            if (e.getTo() != e.getFrom()) {
                p.sendMessage(Tp.PREFIX + "Teleportation cancelled! You moved!");
                String n = "";
                for (String s : Main.tpahere.keySet()) {
                    if (Main.tpahere.get(s).equals(p.getName())) {
                        n = s;
                    }
                }
                Player t = Bukkit.getPlayer(Main.tpahere.get(n));
                if (t == null) {
                    p.sendMessage(Tp.PREFIX + "Player is not online!");
                } else {
                    t.sendMessage(Tp.PREFIX + "Teleportation cancelled! " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " moved!");
                    Tpahere.toTP = false;
                    Main.tpahere.remove(t.getName());
                }
            }
        }
        if (p.isAfk()) {
            p.setAfk(false);
        }
        if (Wild.l.contains(p.getName())) {
            e.getFrom().getBlock().breakNaturally();
            Wild.l.remove(p.getName());
        }
    }
}
