package me.shizleshizle.core.commands;

import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.ErrorMessages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {
 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Help" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("help")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
						p.sendMessage(ChatColor.GOLD + "----===["  + ChatColor.YELLOW + " Help " + ChatColor.GOLD + "]===----");
						p.sendMessage(ChatColor.GOLD + "Page (" + ChatColor.YELLOW + "1" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "5" + ChatColor.GOLD + ")");
						p.sendMessage(" ");
	     				p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Afk" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Mark yourself as AFK.");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Back" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Go back to your previous location.");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Balance" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Check your balance.");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Broadcast" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Send a message to all players.");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "ClearChat" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Clear the chat.");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "ClearInventory" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Clear your own inventory.");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "CommandSpy" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "View all commands that players use.");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "DelHome" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Delete your home(s).");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Enchant" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Enchant your items.");
						p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Enderchest" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Open your Enderchest.");
						p.sendMessage("");
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("1")) {
							p.sendMessage(ChatColor.GOLD + "----===["  + ChatColor.YELLOW + " Help " + ChatColor.GOLD + "]===----");
							p.sendMessage(ChatColor.GOLD + "Page (" + ChatColor.YELLOW + "1" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "5" + ChatColor.GOLD + ")");
							p.sendMessage(" ");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Afk" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Mark yourself as AFK.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Back" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Go back to your previous location.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Balance" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Check your balance.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Broadcast" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Send a message to all players.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "ClearChat" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Clear the chat.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "ClearInventory" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Clear your own inventory.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "CommandSpy" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "View all commands that players use.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "DelHome" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Delete your home(s).");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Enchant" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Enchant your items.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Enderchest" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Open your Enderchest.");
							p.sendMessage("");
						} else if (args[0].equalsIgnoreCase("2")) {
							p.sendMessage(ChatColor.GOLD + "----===["  + ChatColor.YELLOW + " Help " + ChatColor.GOLD + "]===----");
							p.sendMessage(ChatColor.GOLD + "Page (" + ChatColor.YELLOW + "2" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "5" + ChatColor.GOLD + ")");
							p.sendMessage(" ");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Feed" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Feed yourself.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Fly" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Toggle fly mode.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Freeze" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Freeze a player.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Gamemode" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change your gamemode.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Getpos" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Get a player position.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Hat" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Set an item as your hat");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Home" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Go to your home.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Heal" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Heal yourself or a player.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Help" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "View all commands.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Kill" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Kill a player.");
							p.sendMessage(" ");
						} else if (args[0].equalsIgnoreCase("3")) {
							p.sendMessage(ChatColor.GOLD + "----===["  + ChatColor.YELLOW + " Help " + ChatColor.GOLD + "]===----");
							p.sendMessage(ChatColor.GOLD + "Page (" + ChatColor.YELLOW + "3" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "5" + ChatColor.GOLD + ")");
							p.sendMessage(" ");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "List" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "View who is online.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Lockdown" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Toggle Lockdown Mode.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Me" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Talk in me.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Nickname" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change your nickname.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Pay" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Pay a player with money.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Perms" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change the permissions.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Prefix" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change your or a players prefix.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Ranks" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "View or change a rank.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Reload" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Reload the config");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Repair" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Repair all your items.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Rules" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "View all the rules.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Skulls" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Get yourself a skull.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Sethome" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Set your home.");
							p.sendMessage(" ");
						} else if (args[0].equalsIgnoreCase("4")) {
							p.sendMessage(ChatColor.GOLD + "----===["  + ChatColor.YELLOW + " Help " + ChatColor.GOLD + "]===----");
							p.sendMessage(ChatColor.GOLD + "Page (" + ChatColor.YELLOW + "4" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "5" + ChatColor.GOLD + ")");
							p.sendMessage(" ");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Speed" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change your speed.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Stafflist" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Look if a staff is online");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Suicide" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Kill yourself.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Time" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change the time.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Day" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change it to day.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Night" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change it to night.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "ptime" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change your own time.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tp" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Tp to a player.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tpa" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Tpa to a player.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tpaccept" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Tpaccept a player.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tpahere" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Tpahere a player.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tpdeny" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Tpadeny a player.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tphere" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Tphere a player.");
							p.sendMessage(" ");
						} else if (args[0].equalsIgnoreCase("5")) {
							p.sendMessage(ChatColor.GOLD + "----===["  + ChatColor.YELLOW + " Help " + ChatColor.GOLD + "]===----");
							p.sendMessage(ChatColor.GOLD + "Page (" + ChatColor.YELLOW + "5" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "5" + ChatColor.GOLD + ")");
							p.sendMessage(" ");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tpo" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Teleportp override to a player.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tpohere" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Teleport override a player to you.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tppos" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Teleport to a position.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Tptoggle" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Toggle your teleportation.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Vanish" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Hide yourself.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Weather" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Change the weather.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Whois" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "View a player and they settings.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Wild" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Teleport somewhere random.");
							p.sendMessage(ChatColor.GOLD + "/" + ChatColor.YELLOW + "Workbench" + ChatColor.GOLD + " - " + ChatColor.YELLOW + "Open a workbench.");
							p.sendMessage(" ");
						} else {
							ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/help <1|2|3|4|5>");
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/help <1|2|3|4|5>");
					}
				} else {
					ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/help");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
			}
		}
		return false;
	}
}
