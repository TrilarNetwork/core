package me.shizleshizle.core.listeners;

import me.shizleshizle.core.objects.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerPD implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        User p = new User(e.getPlayer());
        if (p.isVanished()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (!(e instanceof Player)) return;
        User p = new User((Player) e.getEntity());
        if (p.isVanished()) {
            e.setCancelled(true);
        }
    }
}