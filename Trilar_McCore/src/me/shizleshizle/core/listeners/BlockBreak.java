package me.shizleshizle.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.shizleshizle.core.commands.Freeze;
import me.shizleshizle.core.objects.User;

public class BlockBreak implements Listener {	
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player x = e.getPlayer();
        User p = new User(x);
		if (p.isFrozen()) {
			e.setCancelled(true);
			if (!Freeze.hasSent2) {
				p.sendMessage(Freeze.prefix + "You have been frozen, you can't break blocks!");
			}
		}
		if (p.isVanished()) {
			e.setCancelled(true);
		}
	}
}