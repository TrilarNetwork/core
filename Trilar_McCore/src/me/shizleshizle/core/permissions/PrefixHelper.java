package me.shizleshizle.core.permissions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.ChatColor;

import me.shizleshizle.core.Main;

public class PrefixHelper {
	
	public static void setup() {
		try {
			Main.sql.getReady();
			Main.sql.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS userprefix (player varchar(32), prefix varchar(128))");
			Main.sql.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS groupprefix (`group` varchar(50), prefix varchar(128))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setPrefix(String p, String prefix) {
		try {
			Main.sql.getReady();
			PreparedStatement s;
			if (hasPrefix(p)) {
				s = Main.sql.getConnection().prepareStatement("UPDATE userprefix SET prefix='" + prefix + "' WHERE player='" + p + "'");
			} else {
				s = Main.sql.getConnection().prepareStatement("INSERT INTO userprefix (player, prefix) VALUES ('" + p + "', '" + prefix + "')");
			}
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasPrefix(String p) {
		boolean b = false;
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM userprefix WHERE player='" + p + "'");
			if (rs.next()) {
				b = true;
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static void removePrefix(String p) {
		try {
			Main.sql.getReady();
			PreparedStatement s = Main.sql.getConnection().prepareStatement("DELETE FROM userprefix WHERE player='" + p + "'");
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getPrefix(String p) {
		String prefix = "";
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM userprefix WHERE player='" + p + "'");
			if (rs.next()) {
				prefix = rs.getString("prefix");
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ChatColor.translateAlternateColorCodes('&', prefix);
	}
	
	public static String getRawPrefix(String p) {
		String prefix = "";
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM userprefix WHERE player='" + p + "'");
			if (rs.next()) {
				prefix = rs.getString("prefix");
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prefix;
	}

	public static void setPrefix(PermGroup pg, String prefix) {
		try {
			Main.sql.getReady();
			PreparedStatement s;
			if (hasPrefix(pg)) {
				s = Main.sql.getConnection().prepareStatement("UPDATE groupprefix SET prefix='" + prefix + "' WHERE `group`='" + pg.toString().toLowerCase() + "'");
			} else {
				s = Main.sql.getConnection().prepareStatement("INSERT INTO groupprefix (`group`, prefix) VALUES ('" + pg.toString().toLowerCase() + "', '" + prefix + "')");
			}
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasPrefix(PermGroup pg) {
		boolean b = false;
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM groupprefix WHERE `group`='" + pg.toString().toLowerCase() + "'");
			if (rs.next()) {
				b = true;
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public static void removePrefix(PermGroup pg) {
		try {
			Main.sql.getReady();
			PreparedStatement s = Main.sql.getConnection().prepareStatement("DELETE * FROM groupprefix WHERE `group`='" + pg.toString().toLowerCase() + "'");
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getPrefix(PermGroup pg) {
		String prefix = "";
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM groupprefix WHERE `group`='" + pg.toString().toLowerCase() + "'");
			if (rs.next()) {
				prefix = rs.getString("prefix");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ChatColor.translateAlternateColorCodes('&', prefix);
	}
	
	public static String getRawPrefix(PermGroup p) {
		String prefix = "";
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM groupprefix WHERE `group`='" + p.toString().toLowerCase() + "'");
			if (rs.next()) {
				prefix = rs.getString("prefix");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prefix;
	}
}
