package me.shizleshizle.core.listeners;

import me.shizleshizle.core.commands.bansystem.Ban;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.shizleshizle.core.commands.Lockdown;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;

public class PlayerChat implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		User p = new User(e.getPlayer());
		e.setCancelled(true);
		String msg = e.getMessage();
		if (Perm.hasPerm(p, PermGroup.ADMIN)) {
			if (e.getMessage().equalsIgnoreCase("!panic") || e.getMessage().contains("!panic")) {
				Lockdown.initiateLockdown();
				e.setMessage("");
			}
		}
		if (Perm.hasPerm(p, PermGroup.HELPER)) {
			msg = ChatColor.translateAlternateColorCodes('&', msg);
		}
		if (Perm.getGroup(p) == null) {
			Perm.updateGroup(p, PermGroup.MEMBER);
		}
		if (p.isMuted()) {
			p.sendMessage(Ban.PREFIX + "You have been muted!");
		} else {
			Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.GOLD + " >> " + ChatColor.translateAlternateColorCodes('&', p.getChatColor()) + msg);
		}
	}
}
