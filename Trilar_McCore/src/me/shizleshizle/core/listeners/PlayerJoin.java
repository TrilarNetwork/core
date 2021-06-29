package me.shizleshizle.core.listeners;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.Freeze;
import me.shizleshizle.core.commands.Lockdown;
import me.shizleshizle.core.commands.cmdutils.HomeUtils;
import me.shizleshizle.core.commands.cmdutils.TicketUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermAttachments;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.permissions.PermUser;
import me.shizleshizle.core.utils.NickNameManager;
import me.shizleshizle.core.utils.Tablist;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player x = e.getPlayer();
		User p = new User(x);
		if (Lockdown.hasLockdown()) {
			if (!Perm.hasPerm(p, PermGroup.BUILDER)) {
				p.kickUser(ChatColor.GOLD + "-=[ Trilar ]=- \nServer is in Lockdown mode, Staff will look into the server(s). \nPlease try again later.");
			}
		}
		HomeUtils.initPlayer(p);
		p.setBack(p.getLocation());
		if (!p.hasPlayedBefore() || Perm.getGroup(p) == null) {
			Perm.updateGroup(p, PermGroup.MEMBER);
			p.setNick(p.getName());
		} else {
			HomeUtils.loadHomes(p.getName());
		}
		Perm.loginPlayer(p.getName());
		PermAttachments.addPerms(p);
		PermUser.loginUser(p);
		Main.sql.updatePlayer(p);
		if (!NickNameManager.isLoaded(p)) {
			p.loadNick();
		}
		if (p.getLocation().getBlock().getType() == Material.AIR) {
			p.setFallDistance(0);
		}	
		if (p.isFrozen()) {
			p.sendMessage(Freeze.PREFIX + "You are still frozen!");
		}
		if (Main.c.getConfig().contains("settings.cooldowns." + p.getName())) {
			int time = Main.c.getConfig().getInt("settings.cooldowns." + p.getName());
			p.giveCooldown(time);
			Main.c.getConfig().set("settings.cooldowns." + p.getName(), null);
			Main.c.saveConfig();
		}
		if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
            int tickets = TicketUtils.getOpenTickets();
            if (tickets == 1) {
                p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "There is " + ChatColor.GOLD + tickets + ChatColor.YELLOW + " open ticket!" + ChatColor.GOLD + "]");
            } else {
                p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "There are " + ChatColor.GOLD + tickets + ChatColor.YELLOW + " open tickets!" + ChatColor.GOLD + "]");
            }
        } else {
            int t = TicketUtils.getOpenTickets(p.getName());
            if (t == 1) {
                p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "You have " + ChatColor.GOLD + t + ChatColor.YELLOW + " open ticket!" + ChatColor.GOLD + "]");
            } else {
                p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "You have " + ChatColor.GOLD + t + ChatColor.YELLOW + " open tickets!" + ChatColor.GOLD + "]");
            }
        }
		e.setJoinMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.GOLD + " has joined the game.");
		Tablist.updateTablist(Bukkit.getOnlinePlayers().toArray(new Player[0]));
	}
}
