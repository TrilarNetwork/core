package me.shizleshizle.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.shizleshizle.core.commands.Commandspy;

public class PlayerPreProcess implements Listener {

	@EventHandler
	public void doStuff(PlayerCommandPreprocessEvent e) {
		Player t = e.getPlayer();

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (Commandspy.spy.contains(p.getName())) {
				p.sendMessage(t.getName() + " >> " + e.getMessage());
			}
		}
	}
}
