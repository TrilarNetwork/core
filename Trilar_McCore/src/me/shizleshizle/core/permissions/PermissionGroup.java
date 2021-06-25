package me.shizleshizle.core.permissions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import me.shizleshizle.core.Main;

public class PermissionGroup {

	public static void setup() {
		PreparedStatement s;
		try {
			s = Main.sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS group_permissions (`group` varchar(50), permission varchar(128))");
										  s.executeUpdate("CREATE TABLE IF NOT EXISTS player_ranks (player varchar(32), mcrank varchar(50))");
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addPermission(PermGroup pg, String perm) {
		ArrayList<String> p = new ArrayList<>();
		p.add(perm);
		addPermissions(pg, p);
	}

	private static void addPermissions(PermGroup pg, ArrayList<String> perms) {
		for (String pe : perms) {
			try {
				PreparedStatement s = Main.sql.getConnection().prepareStatement("INSERT INTO group_permissions (`group`, permission) VALUES ('"
						+ pg.getName().toLowerCase() + "', '" + pe + "')");
				s.executeUpdate();
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void removePermission(PermGroup pg, String perm) {
		ArrayList<String> perms = new ArrayList<>();
		perms.add(perm);
		removePermissions(pg, perms);
	}

	private static void removePermissions(PermGroup pg, ArrayList<String> perms) {
		try {
			for (String pe : perms) {
				PreparedStatement s = Main.sql.getConnection().prepareStatement("DELETE FROM group_permissions WHERE `group`='" + pg.getName().toLowerCase()
						+ "' AND permission='" + pe + "'");
				s.executeUpdate();
				s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getPermissions(PermGroup pg) {
		ArrayList<String> perms = new ArrayList<>();
		Statement s;
		ResultSet rs;
		try {
			s = Main.sql.getConnection().createStatement();
			rs = s.executeQuery("SELECT * FROM group_permissions WHERE `group`='" + pg.getName().toLowerCase() + "'");
			while (rs.next()) {
				perms.add(rs.getString("permission"));
			}
			rs.close();
			s.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perms;
	}
}