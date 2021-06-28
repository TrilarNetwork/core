package me.shizleshizle.core.commands.tickets;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import static org.bukkit.ChatColor.*;

public class Tickets implements CommandExecutor {
 	public static final String PREFIX = YELLOW.toString() + BOLD + "Tickets" + GOLD + " >> " + YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tickets")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
					if (args.length == 0) {
						p.sendMessage(GOLD + "[" + YELLOW + "Open Tickets" + GOLD + "]");
                        try {
                            Main.sql.getReady();
                            Statement s = Main.sql.getConnection().createStatement();
                            ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE status='OPEN' ORDER BY id ASC");
                            int its = 0;
                            while (rs.next()) {
                                its++;
                                String desc = rs.getString("description");
                                if (desc.length() > 42) {
                                    desc = desc.substring(0, 42) + "...";
                                }
                                p.sendMessage(GOLD + "(" + GREEN + "#" + YELLOW + rs.getInt("id") + GOLD + ") " + YELLOW + rs.getString("owner") + GOLD + ": "
                                        + YELLOW + desc);
                            }
                            if (its == 0) {
                                p.sendMessage(PREFIX + "There are no open tickets!");
                            }
                            rs.close();
                            s.close();
                            return true;
                        } catch (SQLException e) {
                            Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
                            Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
                        }
					} else if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
						p.sendMessage(PREFIX + "Help");
                        p.sendMessage(YELLOW + "/ticket <info> " + GOLD + "- creates a ticket");
                        p.sendMessage(YELLOW + "/checkticket <id> " + GOLD + "- sends you the info of the ticket");
                        p.sendMessage(YELLOW + "/closeticket <id> " + GOLD + "- closes a ticket");
                        p.sendMessage(YELLOW + "/closeticket <id> -r " + GOLD + "- reopens a closed ticket");					
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/tickets");
					}
				} else { 
					p.sendMessage(GOLD + "[" + YELLOW + "Open Tickets" + GOLD + "]");
                    try {
                        Statement s;
                        Main.sql.getReady();
                        s = Main.sql.getConnection().createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE owner='" + p.getName() + "' ORDER BY id ASC");
                        int its = 0;
                        while (rs.next()) {
                            its++;
                            String desc = rs.getString("description");
                            if (desc.length() > 42) {
                                desc = desc.substring(0, 42) + "...";
                            }
                            p.sendMessage(GOLD + "(" + YELLOW + rs.getInt("id") + GOLD + ") " + YELLOW + rs.getString("owner") + GOLD + ": "
                                    + YELLOW + desc);
                        }
                        if (its == 0) {
                            p.sendMessage(PREFIX + "There are no open tickets!");
                            rs.close();
                            s.close();
                            return true;
                        }
                        rs.close();
                        s.close();
                        return true;
                    } catch (SQLException e) {
                        Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
                        Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
                    }
				}
			} else {
				sender.sendMessage(RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}