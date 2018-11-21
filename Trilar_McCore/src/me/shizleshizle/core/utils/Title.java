package me.shizleshizle.core.utils;

import java.lang.reflect.Field;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.shizleshizle.core.objects.User;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Title {

	public static void senditle(User p, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
		PlayerConnection connection = ((CraftPlayer) p.getUser()).getHandle().playerConnection;

		PacketPlayOutTitle packet = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
		connection.sendPacket(packet);
		if (subtitle != null) {
			IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', subtitle) + "\"}");
			PacketPlayOutTitle packetSub = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
			connection.sendPacket(packetSub);
		}
		if (title != null) {
			IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', title) + "\"}");
			PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
			connection.sendPacket(packetTitle);
		}
	}	
	
	public static void sendHeaderAndFooter(Player p, String head, String foot) {
	    CraftPlayer craftplayer = (CraftPlayer) p;
	    PlayerConnection connection = craftplayer.getHandle().playerConnection;
	    IChatBaseComponent header = ChatSerializer.a("{'color': '', 'text': '" + ChatColor.translateAlternateColorCodes('&', head) + "'}");
	    IChatBaseComponent footer = ChatSerializer.a("{'color': '', 'text': '" + ChatColor.translateAlternateColorCodes('&', foot) + "'}");
	    PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
	    try{
	    	Field headerField = packet.getClass().getDeclaredField("a");
	    	headerField.setAccessible(true);
	    	headerField.set(packet, header);
	    	headerField.setAccessible(!headerField.isAccessible());

	    	Field footerField = packet.getClass().getDeclaredField("b");
	    	footerField.setAccessible(true);
	    	footerField.set(packet, footer);
	    	footerField.setAccessible(!footerField.isAccessible());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    connection.sendPacket(packet);
	}
}
