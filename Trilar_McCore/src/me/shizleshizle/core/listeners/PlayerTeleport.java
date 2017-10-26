package me.shizleshizle.core.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.shizleshizle.core.objects.User;

public class PlayerTeleport implements Listener {

	@EventHandler
	public void onTP(PlayerTeleportEvent e) {
		User u = new User(e.getPlayer());
		u.setBack(e.getFrom());
	}
}