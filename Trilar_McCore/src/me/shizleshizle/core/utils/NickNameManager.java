package me.shizleshizle.core.utils;

import me.shizleshizle.core.objects.User;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class NickNameManager {
    public static HashMap<String, String> nicks = new HashMap<String, String>();

    public static boolean isLoaded(User p) {
        return nicks.containsKey(p.getName());
    }

    public static void unloadAll() {
        nicks.clear();
    }

    public static String getPlayerFromNick(String nick) {
        String toreturn = null;
        nick = ChatColor.translateAlternateColorCodes('&', nick);
        nick = ChatColor.stripColor(nick);
        for (String name : nicks.keySet()) {
            String newnick = ChatColor.translateAlternateColorCodes('&', nicks.get(name));
            newnick = ChatColor.stripColor(newnick);
            if (nick.equals(newnick)) {
                toreturn = name;
            }
        }
        return toreturn;
    }
}
