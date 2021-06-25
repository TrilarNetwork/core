package me.shizleshizle.core.utils;

public class Title {

    // CHECK HOW TO DO IN 1.17
    /*public static void sendTitle(User p, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
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

    static void sendHeaderAndFooter(Player p, String foot) {
        CraftPlayer craftplayer = (CraftPlayer) p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{'color': '', 'text': '" + ChatColor.translateAlternateColorCodes('&', me.shizleshizle.core.Main.PREFIX) + "'}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{'color': '', 'text': '" + ChatColor.translateAlternateColorCodes('&', foot) + "'}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());
            // Fix for isAccessible() is Java 9+
            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.sendPacket(packet);
    }*/
}
