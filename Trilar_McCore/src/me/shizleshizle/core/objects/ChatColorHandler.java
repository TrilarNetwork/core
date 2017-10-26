package me.shizleshizle.core.objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.permissions.PermGroup;

public class ChatColorHandler {

	public static void setup() {
		try {
			Main.sql.getReady();
			Main.sql.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS chatcolor (player varchar(32), chatcolor varchar(32))");
			Main.sql.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS gcolor (`group` varchar(50), chatcolor varchar(32))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setChatColor(String name, String cc) {
		try {
			Main.sql.getReady();
			PreparedStatement s;
			if (hasColor(name)) {
				s = Main.sql.getConnection().prepareStatement("UPDATE chatcolor SET chatcolor='" + cc + "' WHERE player='" + name + "'");
			} else {
				s = Main.sql.getConnection().prepareStatement("INSERT INTO chatcolor (player, chatcolor) VALUES ('" + name + "', '" + cc + "')");
			}
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeChatColor(String name) {
		try {
			Main.sql.getReady();
			PreparedStatement s = Main.sql.getConnection().prepareStatement("DELETE * FROM chatcolor WHERE player='" + name + "'");
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean hasColor(String name) {
		boolean b = false;
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM chatcolor WHERE player='" + name + "'");
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
	
	public static String getChatColor(String name) {
		String cc = "";
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM chatcolor WHERE player='" + name + "'");
			if (rs.next()) {
				cc = rs.getString("chatcolor");
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cc;
	}
	
	public static void setChatColor(PermGroup pg, String cc) {
		try {
			Main.sql.getReady();
			PreparedStatement s;
			if (hasColor(pg)) {
				s = Main.sql.getConnection().prepareStatement("UPDATE gcolor SET chatcolor='" + cc + "' WHERE `group`='" + pg.toString().toLowerCase() + "'");
			} else {
				s = Main.sql.getConnection().prepareStatement("INSERT INTO gcolor (`group`, chatcolor) VALUES ('" + pg.toString().toLowerCase() + "', '" + cc + "')");
			}
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeChatColor(PermGroup pg) {
		try {
			Main.sql.getReady();
			PreparedStatement s = Main.sql.getConnection().prepareStatement("DELETE * FROM gcolor WHERE `group`='" + pg.toString().toLowerCase() + "'");
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getChatColor(PermGroup pg) {
		String cc = "";
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM gcolor WHERE `group`='" + pg.toString().toLowerCase() + "'");
			if (rs.next()) {
				cc = rs.getString("chatcolor");
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cc;
	}
	
	public static boolean hasColor(PermGroup pg) {
		boolean b = false;
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM gcolor WHERE `group`='" + pg.toString().toLowerCase() + "'");
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
}
