package me.shizleshizle.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import me.shizleshizle.core.objects.User;

public class FoodChange implements Listener {

    @EventHandler
    public void onFCL(FoodLevelChangeEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        User p = new User((Player) e.getEntity());
        if (p.isGod()) {
            e.setCancelled(true);
        }
    }
}
