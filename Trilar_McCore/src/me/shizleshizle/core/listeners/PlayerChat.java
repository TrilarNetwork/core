package me.shizleshizle.core.listeners;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.bansystem.Ban;
import me.shizleshizle.core.commands.cmdutils.HomeUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        User p = new User(e.getPlayer());
        e.setCancelled(true);
        String msg = e.getMessage();
        if (Perm.getGroup(p) == null) {
            Perm.updateGroup(p, PermGroup.MEMBER);
        }
        if (Perm.hasPerm(p, PermGroup.HELPER)) {
            msg = ChatColor.translateAlternateColorCodes('&', msg);
        }
        if (Main.giveReasonForPunishment.contains(p.getName())) {
            Main.staffguiReason.put(p.getName(), ChatColor.translateAlternateColorCodes('&', msg));
            Main.giveReasonForPunishment.remove(p.getName());
            Bukkit.getScheduler().runTask(Main.p, () -> {
                GUI.openPlayerList(p);
            });
            return;
        }
        if (Main.setHome.contains(p.getName())) {
            if (msg.toLowerCase().contains("!overwrite")) {
                HomeUtils.setHome(p, "home", p.getLocation());
            }
            Main.setHome.remove(p.getName());
        }
        if (p.isMuted()) {
            p.sendMessage(Ban.PREFIX + "You have been muted!");
            return;
        }
        if (p.isTalkingInStaffChat() || (Perm.hasPerm(p, PermGroup.TRIAL_HELPER) && msg.startsWith("!") && !msg.equals("!"))) {
            p.sendStaffMessage(ChatColor.translateAlternateColorCodes('&', msg.substring(1)));
            return;
        }
        Bukkit.broadcastMessage(p.getDisplayName() + ChatColor.GOLD + " >> " + ChatColor.translateAlternateColorCodes('&', p.getChatColorString()) + msg);
    }
}
