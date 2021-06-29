package me.shizleshizle.core.commands.cmdutils;

import me.shizleshizle.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class TicketUtils {
	public enum Status {
		OPEN, CLOSED;
	}

	public static void createTicket(String owner, Status s, String desc, double x, double y, double z, String world) {
		try {
			Main.sql.getReady();
			String status;
			if (s.equals(Status.OPEN)) {
				status = "OPEN";
			} else {
				status = "CLOSED";
			}
			PreparedStatement ps = Main.sql.getConnection().prepareStatement("INSERT INTO tickets (owner, status, description, x, y, z, world) VALUES "
					+ "('" + owner + "', '" + status + "', '" + desc + "', '" + x + "', '"+ y + "', '" + z +"', '" + world + "')");
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
		}
	}
	
	public static void closeTicket(int id) {
		try {
			PreparedStatement s = Main.sql.getConnection().prepareStatement("UPDATE tickets SET status='CLOSED' WHERE id='" + id + "'");
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
		}
	}
	
	public static boolean isClosed(int id) {
		boolean b = false;
		try {
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE id='" + id + "'");
			if (rs.next()) {
				if (rs.getString("status").equals("CLOSED")) {
					b = true;
				}
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
		}
		return b;
	}
	
	public static void openTicket(int id) {
		try {
			PreparedStatement s = Main.sql.getConnection().prepareStatement("UPDATE tickets SET status='OPEN' WHERE id='" + id + "'");
			s.executeUpdate();
			s.close();
		} catch (SQLException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
		}
	}
	
	public static Location getLocation(int id) {
		double x;
		double y;
		double z;
		String w;
		Location l = null;
		try {
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE id='" + id + "'");
			if (rs.next()) {
				x = rs.getDouble("x");
				y = rs.getDouble("y");
				z = rs.getDouble("z");
				w = rs.getString("world");
				l = new Location(Bukkit.getWorld(w), x, y, z);
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
		}
		return l;
	}
	
	public static String getDescription(int id) {
		String desc = "";
		try {
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE id='" + id + "'");
			if (rs.next()) {
				desc = rs.getString("description");
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
		}
		return desc;
	}
	
	public static boolean exists(int id) {
		boolean ex = false;
		try {
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE id='" + id + "'");
			if (rs.next()) {
				ex = true;
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
		}
		return ex;
	}

	public static int getOpenTickets() {
		int tickets = 0;
		try {
			Statement s;
			Main.sql.getReady();
			s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE status='OPEN' ORDER BY id ASC");
			int its = 0;
			while (rs.next()) {
				its++;
			}
			rs.close();
			s.close();
			tickets = its;
		} catch (SQLException sql) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
			Bukkit.getLogger().log(Level.WARNING, "Error: " + sql);
		}
		return tickets;
	}

	public static int getOpenTickets(String owner) {
		int t = 0;
		try {
			Main.sql.getReady();
			Statement s = Main.sql.getConnection().createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE owner='" + owner + "' ORDER BY id ASC");
			int its = 0;
			while (rs.next()) {
				its++;
			}
			rs.close();
			s.close();
			t = its;
		} catch (SQLException sql) {
			Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
			Bukkit.getLogger().log(Level.WARNING, "Error: " + sql);
		}
		return t;
	}
}
