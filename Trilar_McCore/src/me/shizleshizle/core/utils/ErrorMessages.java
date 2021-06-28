package me.shizleshizle.core.utils;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import org.bukkit.command.CommandSender;

import static org.bukkit.ChatColor.*;

public class ErrorMessages {
	public static String prefix = RED.toString() + BOLD + "Error" + GOLD + BOLD + " >> " + YELLOW;
	
	public static void doErrorMessage(CommandSender p, Messages message, String Entity){
		if (message.equals(Messages.PLAYER_OFFLINE)){
			p.sendMessage(prefix + "Player " + GOLD + Entity.toLowerCase() + YELLOW + " has not been found!");
		} else if (message.equals(Messages.INVALID_USAGE)){
			p.sendMessage(prefix + "Invalid usage! You should try: " + GOLD + Entity.toLowerCase() + YELLOW + "!");
		} else if (message.equals(Messages.NOPERM)){
			p.sendMessage(prefix + "You do not have permission to perform: " + GOLD + Entity.toLowerCase() + YELLOW + "!");
		} else if (message.equals(Messages.PLAYERS_OFFLINE)) {
			p.sendMessage(prefix + "The players " + GOLD + Entity.toLowerCase().trim() + YELLOW + " have not been found!");
		} else if (message.equals(Messages.NOPERM_WEARITEM)) {
			p.sendMessage(prefix + "You do not have permission to wear: " + GOLD + Entity.toLowerCase() + YELLOW + "!");
		} else if (message.equals(Messages.LOBBY)) {
			p.sendMessage(prefix + "You cannot use " + GOLD + Entity.toLowerCase() + YELLOW + " in the lobby!");
		} else if (message.equals(Messages.PLAYER_ONLY_CMD)) {
			p.sendMessage(prefix + "You cannot use " + GOLD + Entity.toLowerCase() + YELLOW + " as console!");
		}
	}
	
	public static void doErrorMessage(User p, Messages message, String Entity){
		if (message.equals(Messages.PLAYER_OFFLINE)){
			p.sendMessage(prefix + "Player " + GOLD + Entity.toLowerCase() + YELLOW + " has not been found!");
		} else if (message.equals(Messages.INVALID_USAGE)){
			p.sendMessage(prefix + "Invalid usage! You should try: " + GOLD + Entity.toLowerCase() + YELLOW + "!");
		} else if (message.equals(Messages.NOPERM)){
			p.sendMessage(prefix + "You do not have permission to perform: " + GOLD + Entity.toLowerCase() + YELLOW + "!");
		} else if (message.equals(Messages.PLAYERS_OFFLINE)) {
			p.sendMessage(prefix + "The players " + GOLD + Entity.toLowerCase().trim() + YELLOW + " have not been found!");
		} else if (message.equals(Messages.NOPERM_WEARITEM)) {
			p.sendMessage(prefix + "You do not have permission to wear: " + GOLD + Entity.toLowerCase() + YELLOW + "!");
		} else if (message.equals(Messages.LOBBY)) {
			p.sendMessage(prefix + "You cannot use " + GOLD + Entity.toLowerCase() + YELLOW + " in the lobby!");
		} 
	}
}
