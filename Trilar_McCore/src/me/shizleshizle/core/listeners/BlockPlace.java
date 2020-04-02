package me.shizleshizle.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.shizleshizle.core.commands.Freeze;
import me.shizleshizle.core.objects.User;

public class BlockPlace implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player x = e.getPlayer();
        User p = new User(x);
		if (p.isFrozen()) {
			e.setCancelled(true);
			if (!Freeze.hasSent3) {
				p.sendMessage(Freeze.PREFIX + "You have been frozen, you can't place blocks!");
			}
		}
		if (p.isVanished()) {
			e.setCancelled(true);
		}
	}
}