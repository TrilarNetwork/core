package me.shizleshizle.core.commands.cmdutils;

import me.shizleshizle.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.bukkit.ChatColor.*;

public class WarnUtils {

    /**
     * Add a warn to the database for a certain player.
     *
     * @param name   Name of the player to warn.
     * @param reason Reason for the warning.
     * @param warner Name of the player issueing the warning.
     */
    public static void warn(String name, String reason, @Nullable String warner) {
        if (warner == null) warner = "Anonymous";
        final int latestWarnNmbr = getLatestWarnNumber(name) + 1;
        try {
            Statement s = Main.sql.getConnection().createStatement();
            s.executeUpdate("INSERT INTO Warn (player, id, reason, warner) VALUES ('" + name + "', '" + latestWarnNmbr + "', '" + reason + "', '" + warner + "');");
            s.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> WarnUtils: Error: " + e);
        }
    }

    public static int getLatestWarnNumber(String name) {
        int returnInt = 1;
        try {
            Statement s = Main.sql.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT id FROM Warn WHERE player='" + name + "' ORDER BY id DESC;");
            if (rs.next()) {
                returnInt = rs.getInt("id");
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> WarnUtils: Error: " + e);
        }
        return returnInt;
    }

    @Nullable
    /**
     * Retrieves all warnings of a player stored in the database in the form of a String array. If the player does not have any warns, returns null.
     *
     * @param name Name of the player to get the warns from.
     */
    public static String[] getWarns(String name) {
        String[] warns = null;
        try {
            Statement s = Main.sql.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Warn WHERE player='" + name + "' ORDER BY id;");
            String[] warnings = new String[10];
            while (rs.next()) {
                final int id = rs.getInt("id");
                final String reason = rs.getString("reason");
                final String warner = rs.getString("warner");
                String warn = GOLD + "#" + RED + id + GOLD + ": " + YELLOW + ChatColor.translateAlternateColorCodes('&', reason) + GOLD + " Warned by: " + YELLOW + warner;
                warnings[id] = warn;
            }
            rs.close();
            s.close();
            warns = warnings;
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> WarnUtils: Error: " + e);
        }
        return warns;
    }

    public static boolean hasWarn(String name, int id) {
        boolean has = false;
        try {
            Statement s = Main.sql.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT id FROM Warn WHERE player='" + name + "';");
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    has = true;
                }
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> WarnUtils: Error: " + e);
        }
        return has;
    }

    public static void RemoveWarn(String name, int id) {
        try {
            Statement s = Main.sql.getConnection().createStatement();
            s.executeUpdate("DELETE FROM Warn WHERE player='" + name + "' AND id='" + id + "';");
            s.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> WarnUtils: Error: " + e);
        }
    }

    public static int getWarnAmount(String name) {
        int warns = 0;
        try {
            Statement s = Main.sql.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Warn WHERE player='" + name + "';");
            while (rs.next()) {
                warns++;
            }
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> WarnUtils: Error: " + e);
        }
        return warns;
    }
}
