package me.shizleshizle.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.shizleshizle.core.objects.User;

public class PlayerPD implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        User p = new User(e.getPlayer());
        if (p.isVanished()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        User p = new User(e.getPlayer());
        if (p.isVanished()) {
            e.setCancelled(true);
        }
    }
}