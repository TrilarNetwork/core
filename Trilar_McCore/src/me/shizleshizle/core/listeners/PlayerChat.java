package me.shizleshizle.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;

public class PlayerChat implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		User p = new User(e.getPlayer());
		e.setCancelled(true);
		String msg = e.getMessage();
		if (Perm.hasPerm(p, PermGroup.HELPER)) {
			msg = ChatColor.translateAlternateColorCodes('&', msg);
		}
		if (Perm.getGroup(p) == null) {
			Perm.updateGroup(p, PermGroup.MEMBER);
		}
		Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.GOLD + " >> " + ChatColor.translateAlternateColorCodes('&', p.getChatColor()) + msg);
	}
}
