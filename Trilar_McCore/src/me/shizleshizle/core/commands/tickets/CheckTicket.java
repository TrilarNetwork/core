package me.shizleshizle.core.commands.tickets;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.cmdutils.TicketUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import me.shizleshizle.core.utils.Numbers;

public class CheckTicket implements CommandExecutor {
 	public static final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Tickets" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("checkticket")) {
			if (sender instanceof Player) {
				Player pl = (Player) sender;
				User p = new User(pl);
				if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
					if (args.length == 1) {
						if (Numbers.isNumber(args[0])) {
							int id = Numbers.getInt(args[0]);
							if (TicketUtils.exists(id)) {
								try {
									Statement s = Main.sql.getConnection().createStatement();
									ResultSet rs = s.executeQuery("SELECT * FROM tickets WHERE id='" + id + "'");
									if (rs.next()) {
										String owner = rs.getString("owner");
										String st = rs.getString("status");
										if (st.equals("OPEN")) {
											st = ChatColor.GREEN + "Open";
										} else {
											st = ChatColor.RED + "Closed";
										}
										String desc = rs.getString("description");
										double x = rs.getDouble("x");
										double y = rs.getDouble("y");
										double z = rs.getDouble("z");
										String world = rs.getString("world");
										p.sendMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "Ticket: " + id + ChatColor.GOLD + "]");
										p.sendMessage(ChatColor.GOLD + "Owner: " + ChatColor.YELLOW + owner);
										p.sendMessage(ChatColor.GOLD + "X: " + ChatColor.YELLOW + x);
										p.sendMessage(ChatColor.GOLD + "Y: " + ChatColor.YELLOW + y);
										p.sendMessage(ChatColor.GOLD + "Z: " + ChatColor.YELLOW + z);
										p.sendMessage(ChatColor.GOLD + "World: " + ChatColor.YELLOW + world);
										p.sendMessage(ChatColor.GOLD + "Status: " + st);
										p.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.RESET + desc);
									}
									rs.close();
									s.close();
								} catch (SQLException e) {
									Bukkit.getLogger().log(Level.WARNING, "Could not connect to database!");
                                    Bukkit.getLogger().log(Level.WARNING, "Error: " + e);
								}
							} else {
								p.sendMessage(PREFIX + "Ticket " + ChatColor.GOLD + id + ChatColor.YELLOW + " does not exist!");
							}
						} else {
							p.sendMessage(PREFIX + "You must use a number!");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/checkticket <id>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}