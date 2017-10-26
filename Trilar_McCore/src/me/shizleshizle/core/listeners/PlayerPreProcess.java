package me.shizleshizle.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.shizleshizle.core.commands.CommandSpy;
import me.shizleshizle.core.objects.User;

public class PlayerPreProcess implements Listener {

    @EventHandler
    public void onPreprocess(PlayerCommandPreprocessEvent e) {
        for (Player x : Bukkit.getOnlinePlayers()) {
            User p = new User(x);
            if (CommandSpy.spy.contains(p.getName())) {
                p.sendMessage(ChatColor.GOLD + "SPY: " + p.getDisplayName() + ChatColor.GOLD + " >> " + ChatColor.WHITE + e.getMessage().trim());
            }
        }
    }
}

