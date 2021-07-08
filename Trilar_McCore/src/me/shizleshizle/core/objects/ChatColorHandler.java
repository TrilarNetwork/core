package me.shizleshizle.core.objects;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.permissions.PermGroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatColorHandler {

    public static void setChatColor(String name, String cc) {
        try {
            Main.sql.getReady();
            PreparedStatement s;
            if (hasColor(name)) {
                s = Main.sql.getConnection().prepareStatement("UPDATE Chatcolor SET chatcolor='" + cc + "' WHERE player='" + name + "'");
            } else {
                s = Main.sql.getConnection().prepareStatement("INSERT INTO Chatcolor (player, chatcolor) VALUES ('" + name + "', '" + cc + "')");
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
            PreparedStatement s = Main.sql.getConnection().prepareStatement("DELETE * FROM Chatcolor WHERE player='" + name + "'");
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
            ResultSet rs = s.executeQuery("SELECT * FROM Chatcolor WHERE player='" + name + "'");
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
            ResultSet rs = s.executeQuery("SELECT * FROM Chatcolor WHERE player='" + name + "'");
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
                s = Main.sql.getConnection().prepareStatement("UPDATE Groupcolor SET chatcolor='" + cc + "' WHERE `group`='" + pg.toString().toLowerCase() + "'");
            } else {
                s = Main.sql.getConnection().prepareStatement("INSERT INTO Groupcolor (`group`, chatcolor) VALUES ('" + pg.toString().toLowerCase() + "', '" + cc + "')");
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
            PreparedStatement s = Main.sql.getConnection().prepareStatement("DELETE * FROM Groupcolor WHERE `group`='" + pg.toString().toLowerCase() + "'");
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
            ResultSet rs = s.executeQuery("SELECT * FROM Groupcolor WHERE `group`='" + pg.toString().toLowerCase() + "'");
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
            ResultSet rs = s.executeQuery("SELECT * FROM Groupcolor WHERE `group`='" + pg.toString().toLowerCase() + "'");
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
