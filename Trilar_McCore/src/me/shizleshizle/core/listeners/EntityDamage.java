package me.shizleshizle.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import me.shizleshizle.core.objects.User;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntDmg(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        User p = new User((Player) e.getEntity());
        if (p.isGod()) {
            e.setCancelled(true);
        }
        if (p.isVanished()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDmg(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            User p = new User((Player) e.getEntity());
            if (!(e.getDamager() instanceof Player)) {
                if (p.isAfk()) {
                    e.setCancelled(true);
                }
            }
            if (p.isVanished()) {
                e.setCancelled(true);
            }
        } else {
            if (e.getDamager() instanceof Player) {
                User d = new User((Player) e.getDamager());
                if (d.isAfk()) {
                    d.setAfk(false);
                }
                if (d.isVanished()) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDmg(EntityDamageByBlockEvent e) {
        if (e.getEntity() instanceof Player) {
            User p = new User((Player) e.getEntity());
            if (!(e.getDamager() instanceof Player)) {
                if (p.isAfk()) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
