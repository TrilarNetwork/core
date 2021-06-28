package me.shizleshizle.core.listeners;

import me.shizleshizle.core.objects.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        User p = new User(e.getEntity());
        p.setBack(e.getEntity().getLocation());
    }
}
