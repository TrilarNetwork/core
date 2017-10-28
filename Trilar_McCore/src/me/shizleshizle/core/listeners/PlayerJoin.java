package me.shizleshizle.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.Freeze;
import me.shizleshizle.core.commands.Lockdown;
import me.shizleshizle.core.commands.homes.HomeUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermAttachments;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.permissions.PermUser;
import me.shizleshizle.core.utils.NickNameManager;
import me.shizleshizle.core.utils.Tablist;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player x = e.getPlayer();
		User p = new User(x);
		if (Lockdown.hasLockdown()) {
			if (!Perm.hasPerm(p, PermGroup.BUILDER)) {
				p.kickUser(ChatColor.GOLD + "-=[ Trilar ]=- \nServer is in Lockdown mode, so you can not join. \nPlease try again later.");
				return;
			}
		}
		HomeUtils.initPlayer(p);
		p.setBack(p.getLocation());
		if (!p.hasPlayedBefore()) {
			Perm.updateGroup(p, PermGroup.MEMBER);
			p.setNick(p.getName());
		} else {
			HomeUtils.loadHomes(p.getName());
		}
		if (Perm.getGroup(p) == null) {
			Perm.updateGroup(p, PermGroup.MEMBER);
		}
		Perm.loginPlayer(p.getName());
		PermAttachments.addPerms(p);
		PermUser.loginUser(p);
		Main.sql.updatePlayer(p);
		if (!NickNameManager.isLoaded(p)) {
			p.loadNick();
		}
		Location l = p.getLocation();
		if (l.getBlock().getType() == Material.AIR) {
			p.setFallDistance(0);
		}	
		if (p.isFrozen()) {
			p.sendMessage(Freeze.prefix + "You are still frozen!");
		}
		if (Main.c.getConfig().contains("settings.cooldowns." + p.getName())) {
			int time = Main.c.getConfig().getInt("settings.cooldowns." + p.getName());
			p.giveCooldown(time);
			Main.c.getConfig().set("settings.cooldowns." + p.getName(), null);
			Main.c.saveConfig();
		}
		e.setJoinMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.GOLD + " has joined the game.");
		Tablist.updateTablist(Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]));
	}
}
