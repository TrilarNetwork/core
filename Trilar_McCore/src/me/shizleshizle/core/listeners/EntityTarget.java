package me.shizleshizle.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import me.shizleshizle.core.objects.User;

public class EntityTarget implements Listener {

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player) {
            User p = new User((Player) e.getTarget());
            if (p.isVanished()) {
                e.setCancelled(true);
            }
        }
    }
}
