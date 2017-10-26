package me.shizleshizle.core.commands.homes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import net.md_5.bungee.api.ChatColor;

public class HomeUtils {
	private static HashMap<String, ArrayList<String>> homes = new HashMap<String, ArrayList<String>>();
	
	public static void setHome(User p, String name, double x, double y, double z, double yaw, double pitch, String wname) {
		setHome(p.getName(), name, x, y, z, yaw, pitch, wname);
	}
	
	public static void setHome(String p, String name, double x, double y, double z, double yaw, double pitch, String wname) {
		Main.c.getHomes().set("homes." + p + "." + name + ".x", x);
		Main.c.getHomes().set("homes." + p + "." + name + ".y", y);
		Main.c.getHomes().set("homes." + p + "." + name + ".z", z);
		Main.c.getHomes().set("homes." + p + "." + name + ".yaw", yaw);
		Main.c.getHomes().set("homes." + p + "." + name + ".pitch", pitch);
		Main.c.getHomes().set("homes." + p + "." + name + ".world", wname);
		Main.c.getHomes().set("homes." + p + ".hasHomes", Main.c.getHomes().getInt("homes." + p + ".hasHomes") + 1);
		Main.c.saveHomes();
		ArrayList<String> l = homes.get(p);
		l.add(name);
		homes.put(p, l);
	}
	
	public static void removeHome(User p, String name) {
		removeHome(p.getName(), name);
	}
	
	public static void removeHome(String p, String name) {
		Main.c.getHomes().set("homes." + p + "." + name + ".x", null);
		Main.c.getHomes().set("homes." + p + "." + name + ".y", null);
		Main.c.getHomes().set("homes." + p + "." + name + ".z", null);
		Main.c.getHomes().set("homes." + p + "." + name + ".yaw", null);
		Main.c.getHomes().set("homes." + p + "." + name + ".pitch", null);
		Main.c.getHomes().set("homes." + p + "." + name + ".world", null);
		Main.c.getHomes().set("homes." + p + ".hasHomes", Main.c.getHomes().getInt("homes." + p + ".hasHomes") - 1);
		Main.c.saveHomes();
		ArrayList<String> l = homes.get(p);
		if (l.contains(name)) {
			l.remove(name);
		}
		homes.put(p, l);
	}
	
	public static void toHome(User p, String name) {
		double x = 0;
		double y = 0;
		double z = 0;
		float yaw = 0;
		float pitch = 0;
		World w = null;
		if (Main.c.getHomes().get("homes." + p.getName() + "." + name + ".x") != null && Main.c.getHomes().get("homes." + p.getName() + "." + name + ".y") != null 
				&& Main.c.getHomes().get("homes." + p.getName() + "." + name + ".z") != null && Main.c.getHomes().get("homes." + p.getName() + "." + name + ".yaw") 
				!= null && Main.c.getHomes().get("homes." + p.getName() + "." + name + ".pitch") != null 
				&& Main.c.getHomes().get("homes." + p.getName() + "." + name + ".world") != null) {
			String wname = Main.c.getHomes().getString("homes." + p.getName() + "." + name + ".world");
			w = Bukkit.getWorld(wname);
			x = Main.c.getHomes().getDouble("homes." + p.getName() + "." + name + ".x");
			y = Main.c.getHomes().getDouble("homes." + p.getName() + "." + name + ".y");
			z = Main.c.getHomes().getDouble("homes." + p.getName() + "." + name + ".z");
			yaw = (float) Main.c.getHomes().getDouble("homes." + p.getName() + "." + name + ".yaw");
			pitch = (float) Main.c.getHomes().getDouble("homes." + p.getName() + "." + name + ".pitch");
			if (w != null) {
				Location l = new Location(w, x, y, z);
				l.setYaw(yaw);
				l.setPitch(pitch);
				p.teleport(l);
				p.sendMessage(Home.prefix + "You have been teleported to home " + ChatColor.GOLD + name + ChatColor.YELLOW + "!");
			} else {
				p.sendMessage(Home.prefix + "World " + ChatColor.GOLD + wname + ChatColor.YELLOW + " has not been loaded or does not exist!");
			}
		} else {
			p.sendMessage(Home.prefix + "Home " + ChatColor.GOLD + name + ChatColor.YELLOW + " hasn't been set correctly!");
		}
	}
	
	public static void adminToPlayerHome(User admin, String p, String homename) {
		double x = 0;
		double y = 0;
		double z = 0;
		float yaw = 0;
		float pitch = 0;
		World w = null;
		if (Main.c.getHomes().get("homes." + p + "." + homename + ".x") != null && Main.c.getHomes().get("homes." + p + "." + homename + ".y") != null &&
				Main.c.getHomes().get("homes." + p + "." + homename + ".z") != null && Main.c.getHomes().get("homes." + p + "." + homename + ".yaw") != null &&
				Main.c.getHomes().get("homes." + p + "." + homename + ".pitch") != null && Main.c.getHomes().get("homes." + p + "." + homename + ".world") != null) {
			String wname = Main.c.getHomes().getString("homes." + p + "." + homename + ".world");
			w = Bukkit.getWorld(wname);
			x = Main.c.getHomes().getDouble("homes." + p + "." + homename + ".x");
			y = Main.c.getHomes().getDouble("homes." + p + "." + homename + ".y");
			z = Main.c.getHomes().getDouble("homes." + p + "." + homename + ".z");
			yaw = (float) Main.c.getHomes().getDouble("homes." + p + "." + homename + ".yaw");
			pitch = (float) Main.c.getHomes().getDouble("homes." + p + "." + homename + ".pitch");
			if (w != null) {
				Location l = new Location(w, x, y, z);
				l.setYaw(yaw);
				l.setPitch(pitch);
				admin.teleport(l);
				admin.sendMessage(Home.prefix + "You have been teleported to home " + ChatColor.GOLD + homename + ChatColor.YELLOW + " of player " + ChatColor.GOLD + p + ChatColor.YELLOW + "!");
			} else {
				admin.sendMessage(Home.prefix + "World " + ChatColor.GOLD + wname + ChatColor.YELLOW + " has not been loaded or does not exist!");
			}
		} else {
			admin.sendMessage(Home.prefix + "Home " + ChatColor.GOLD + homename + ChatColor.YELLOW + " hasn't been set correctly!");
		}
	}
	
	public static void initPlayer(User p) {
		if (Main.c.getHomes().get("homes." + p.getName() + ".maxhomes") == null) {
			int homes = 1;
			if (Perm.getGroup(p).equals(PermGroup.MEMBER)) {
				homes = Main.maxhomes;
			} /*else if (Perm.getGroup(p).equals(PermGroup.DONATOR)) {
				homes = 3;
			} else if (Perm.getGroup(p).equals(PermGroup.PREMIUM)) {
				homes = 5;
			} else if (Perm.getGroup(p).equals(PermGroup.PLATINUM)) {
				homes = 10;
			}*/
			Main.c.getHomes().set("homes." + p.getName() + ".maxhomes", homes);
			Main.c.getHomes().set("homes." + p.getName() + ".hasHomes", 0);
			Main.c.saveHomes();
		}
	}
	
	public static int getMaxHomes(User p) {
		return getMaxHomes(p.getName());
	}
	
	public static int getMaxHomes(String playername) {
		return Main.c.getHomes().getInt("homes." + playername + ".maxhomes");
	}
	
	public static void setMaxHomes(String pname, int homes) {
		Main.c.getHomes().set("homes." + pname + ".maxhomes", homes);
		Main.c.saveHomes();
	}
	
	public static int getHomes(String player) {
		return Main.c.getHomes().getInt("homes." + player + ".hasHomes");
	}
	
	public static boolean canSetHome(User p) {
		int has = Main.c.getHomes().getInt("homes." + p.getName() + ".hasHomes");
		int max = Main.c.getHomes().getInt("homes." + p.getName() + ".maxHomes");
		if (has >= max) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean hasHome(String player, String name) {
		if (homes.get(player).contains(name)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String listHomes(String p) {
		String home = "";
		ArrayList<String> l = homes.get(p);
		if (homes.isEmpty() || l.isEmpty()) {
			home = ChatColor.YELLOW + "You do not have a home!";
			return home;
		} else {
			for (String s : l) {
				home = ChatColor.GOLD + s + ChatColor.YELLOW + ", ";
			}
			home = home.substring(0, home.length() - 2);
			return home;
		}
	}
	
	public static void saveHomeNames(String p) {
		ArrayList<String> l = homes.get(p);
		Main.c.getHomes().set("homes." + p + ".homeNames", l);
		Main.c.saveHomes();
	}
	
	public static void loadHomes(String p) {
		List<String> l = Main.c.getHomes().getStringList("homes." + p + ".homeNames");
		ArrayList<String> h = new ArrayList<String>(l);
		homes.put(p, h);
	}
}
