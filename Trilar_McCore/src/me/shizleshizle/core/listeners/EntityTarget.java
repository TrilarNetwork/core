package me.shizleshizle.core.listeners;

import me.shizleshizle.core.objects.User;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class EntityTarget implements Listener {

    @EventHandler
    public void onTarget(EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player) {
            User p = new User((Player) e.getTarget());
            if (p.isVanished()) {
                e.setCancelled(true);
            }
            if (p.isGod()) {
                if (e.getEntity() instanceof Monster || e.getEntity() instanceof Flying) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
