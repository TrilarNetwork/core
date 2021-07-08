package me.shizleshizle.core.permissions;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.User;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Perm {
	private static Map<String, PermGroup> pPerms = new HashMap<>();
	
	public static boolean hasPerm(User p, PermGroup g) {
		return hasPerm(p.getName(), g);
	}

	public static boolean hasPerm(String name, PermGroup g) {
		return (g.getId() <= getGroup(name).getId());
	}
	
	public static PermGroup getGroup(User p) {
		return getGroup(p.getName());
	}
	
	public static PermGroup getGroup(String name) {
		if (pPerms.containsKey(name)) {
			return pPerms.get(name);
		} else {
			return getGroupFromDatabase(name);
		}
	}
	
	public static PermGroup getGroupFromDatabase(Player p) {
		return getGroupFromDatabase(p.getName());
	}
	
	public static void updateGroup(User p, PermGroup g) {
		updateGroup(p.getName(), g);
	}
	
	public static void updateGroup(String name, PermGroup g) {
		if (pPerms.containsKey(name)) {
			pPerms.put(name, g);
		}
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			PreparedStatement pS;
			ResultSet rs = s.executeQuery("SELECT mcrank FROM Player WHERE player='" + name + "'");
			if (!rs.next()) {
				pS = Main.sql.getConnection()
						.prepareStatement("INSERT INTO Player (player, mcrank) VALUES ('" + name + "', '" + g.toString().toLowerCase() + "')");
			} else {
				pS = Main.sql.getConnection().prepareStatement("UPDATE Player SET mcrank='" + (g.toString().toLowerCase()) + "' WHERE player='" + name + "'");
			}
			pS.executeUpdate();
			rs.close();
			pS.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static PermGroup getGroupFromDatabase(String name) {
		String str = PermGroup.MEMBER.toString();
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT mcrank FROM Player WHERE player='" + name + "'");
			while (rs.next()) {
				str = rs.getString("mcrank").toUpperCase();
			}
			rs.close();
			s.close();
			return PermGroup.get(str); 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void loginPlayer(String name) {
		pPerms.put(name, getGroup(name));
	}
	
	public static void logoutPlayer(String name)  {
		pPerms.remove(name);
	}
}
