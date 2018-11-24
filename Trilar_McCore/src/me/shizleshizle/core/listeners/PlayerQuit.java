package me.shizleshizle.core.listeners;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.homes.HomeUtils;
import me.shizleshizle.core.commands.teleportation.Tpa;
import me.shizleshizle.core.commands.teleportation.Tpahere;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermAttachments;
import me.shizleshizle.core.permissions.PermUser;
import me.shizleshizle.core.utils.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class PlayerQuit implements Listener {
	public static HashMap<String, Double> health = new HashMap<>();
    public static HashMap<String, Integer> hunger = new HashMap<>();
    public static HashMap<String, Integer> xp = new HashMap<>();
    public static HashMap<String, Integer> xplevel = new HashMap<>();
    public static HashMap<String, GameMode> gm = new HashMap<>();
    public static HashMap<String, Boolean> op = new HashMap<>();
    public static HashMap<String, Boolean> god = new HashMap<>();
    public static HashMap<String, Boolean> frozen = new HashMap<>();
    public static HashMap<String, Boolean> t = new HashMap<>();
    public static HashMap<String, Boolean> vanished = new HashMap<>();

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		User p = new User(e.getPlayer());
		e.setQuitMessage(ChatColor.DARK_AQUA + p.getName() + ChatColor.GOLD + " has left the game.");
		health.put(p.getName(), p.getHealth());
        hunger.put(p.getName(), p.getFoodLevel());
        xp.put(p.getName(), p.getTotalExperience());
        xplevel.put(p.getName(), p.getLevel());
        gm.put(p.getName(), p.getGameMode());
        god.put(p.getName(), p.isGod());
        frozen.put(p.getName(), p.isFrozen());
        t.put(p.getName(), p.hasTpDisabled());
        vanished.put(p.getName(), p.isVanished());
        HomeUtils.saveHomeNames(p.getName());
		if (p.hasCooldown()) {
			int time = p.getRemainingCooldownTime();
			Main.c.getConfig().set("settings.cooldowns." + p.getName(), time);
			Main.c.saveConfig();
			p.removeCooldown();
		}
		 if (Main.remove) {
	            p.removeNick();
	            p.setGod(false);
	            p.setAfk(false);
	            p.setVanished(false);
	        }
		Perm.logoutPlayer(p.getName());
		PermAttachments.removePerms(p);
		PermUser.logoutUser(p);
		if (Tpa.tpa.containsKey(p.getName())) {
            Tpa.toTP = false;
            Player t = Bukkit.getPlayer(Tpa.tpa.get(p.getName()));
            t.sendMessage(Tpa.prefix + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has left the server! Teleportation has been cancelled.");
            Tpa.tpa.remove(p.getName());
        } else if (Tpa.tpa.containsValue(p.getName())) {
            Tpa.toTP = false;
            String n = "";
            for (String s : Tpa.tpa.keySet()) {
                if (Tpa.tpa.get(s).equals(p.getName())) {
                    n = s;
                }
            }
            Player t = Bukkit.getPlayer(n);
            t.sendMessage(Tpa.prefix + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has left the server! Teleporation has been cancelled!");
            Tpa.tpa.remove(t.getName());
        } else if (Main.tpahere.containsKey(p.getName())) {
            Tpahere.toTP = false;
            Player t = Bukkit.getPlayer(Main.tpahere.get(p.getName()));
            t.sendMessage(Tpahere.prefix + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has left the server! Teleportation has been cancelled.");
            Main.tpahere.remove(p.getName());
        } else if (Main.tpahere.containsValue(p.getName())) {
            Tpahere.toTP = false;
            String n = "";
            for (String s : Main.tpahere.keySet()) {
                if (Main.tpahere.get(s).equals(p.getName())) {
                    n = s;
                }
            }
            Player t = Bukkit.getPlayer(n);
            t.sendMessage(Tpahere.prefix + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has left the server! Teleporation cancelled!");
            Main.tpahere.remove(t.getName());
        }
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.p, () -> {
			int size = Bukkit.getOnlinePlayers().size();
			Tablist.updateTablist(Bukkit.getOnlinePlayers().toArray(new Player[size]));
		}, 20L);
	}
}
