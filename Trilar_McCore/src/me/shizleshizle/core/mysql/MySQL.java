package me.shizleshizle.core.mysql;

import me.shizleshizle.core.Main;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects to and uses a MySQL database
 *
 * @author -_Husky_-
 * @author tips48
 */
public class MySQL extends Database {
    private final String user;
    private final String database;
    private final String password;
    private final int port;
    private final String hostname;

    private Connection conn;
    /*
     * Creates a new MySQL instance
     *
     * @param hostname
     *            Name of the host
     * @param port
     *            Port number
     * @param username
     *            Username
     * @param password
     *            Password
     */
	/*@Deprecated
	public MySQL(String hostname, String port, String username,
			String password) {
		this(hostname, port, null, username, password);
	}

	 * Creates a new MySQL instance for a specific database
	 *
	 * @param hostname
	 *            Name of the host
	 * @param port
	 *            Port number
	 * @param database
	 *            Database name
	 * @param username
	 *            Username
	 * @param password
	 *            Password
	 *
	@Deprecated
	public MySQL(String hostname, String port, String database,
			String username, String password) {
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = username;
		this.password = password;
	} */

    /*
     * Creates a new MySQL instance for a specific database
     *
     * @param hostname
     *            Name of the host
     * @param port
     *            Port number
     * @param database
     *            Database name
     * @param username
     *            Username
     * @param password
     *            Password
     */
    MySQL() {
        this.hostname = Main.host;
        this.port = Main.port;
        this.database = Main.db;
        this.user = Main.user;
        this.password = Main.pw;
        this.conn = openConnection();
    }

    public Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database
                    + "?autoReconnect=true&useSSL=false", this.user, this.password);
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection getConnection() {
        try {
            if (!hasConnection()) openConnection();
        } catch (SQLException e) {
            Bukkit.getLogger().info("MMO > SQL Error: " + e);
        }
        return conn;
    }

    public boolean hasConnection() throws SQLException {
        return (conn != null && !conn.isClosed());
    }
}
