package me.shizleshizle.core.listeners;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.bansystem.Ban;
import me.shizleshizle.core.commands.bansystem.GUIFunction;
import me.shizleshizle.core.commands.cmdutils.VanishUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.utils.GUI;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.bukkit.ChatColor.*;

public class InventoryClick implements Listener {

    @EventHandler
    public void onIC(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        User p = new User((Player) e.getWhoClicked());
        if (p.isVanished()) {
            if (VanishUtils.containsInvs(p)) {
                if (e.getInventory().equals(VanishUtils.getInvs(p))) {
                    e.setCancelled(true);
                }
            }
        }
        ItemStack i = e.getCurrentItem();
        String title = ChatColor.stripColor(p.getOpenInventory().getTitle());
        if (i == null) return;
        if (title.equals("Player List")) {
            e.setCancelled(true);
            if (i.getType() == Material.PLAYER_HEAD) {
                if (i.hasItemMeta()) {
                    Player t = Bukkit.getPlayer(ChatColor.stripColor(Objects.requireNonNull(i.getItemMeta()).getDisplayName()));
                    if (t == null) {
                        p.sendMessage(Main.PREFIX + "This player does not exist!");
                        return;
                    }
                    if (!Main.staffgui.containsKey(p.getName())) {
                        GUIFunction pFunction = Main.staffgui.get(p.getName());
                        if (pFunction == GUIFunction.TELEPORT) {
                            p.teleport(t.getLocation());
                        } else if (pFunction == GUIFunction.KICK) {
                            if (Main.staffguiReason.containsKey(p.getName())) {
                                String reason = Main.staffguiReason.get(p.getName());
                                t.kickPlayer(reason);
                                p.sendMessage(Ban.PREFIX + "Player " + GOLD + t.getName() + YELLOW + " has been kicked for: " + GOLD + reason);
                                Main.staffguiReason.remove(p.getName());
                                Main.staffgui.remove(p.getName());
                            } else {
                                p.sendMessage(Ban.PREFIX + "Something went wrong, please contact a developer!");
                            }
                        } else if (pFunction == GUIFunction.WARN) {

                        } else if (pFunction == GUIFunction.BAN) {
                            if (Main.staffguiReason.containsKey(p.getName())) {
                                String reason = Main.staffguiReason.get(p.getName());
                                Bukkit.getBanList(BanList.Type.NAME).addBan(t.getName(), reason, null, p.getName());
                                t.kickPlayer(reason);
                                p.sendMessage(Ban.PREFIX + "Player " + GOLD + t.getName() + YELLOW + " has been banned for: " + GOLD + reason);
                                Main.staffguiReason.remove(p.getName());
                                Main.staffgui.remove(p.getName());
                            } else {
                                p.sendMessage(Ban.PREFIX + "Something went wrong, please contact a developer!");
                            }
                        } else if (pFunction == GUIFunction.MUTE) {

                        }
                    }
                    // TODO: other menu shit
                    p.closeInventory();
                }
            }
        } else if (title.equals("Invsee")) {
            e.setCancelled(true);
        } else if (title.equals("Staff GUI")) {
            e.setCancelled(true);
            handleStaffGUI(p, i);
        } else if (title.equals("Kick GUI")) {
            e.setCancelled(true);
            handleKickGUI(p, i);
        } else if (title.equals("Warn GUI")) {
            e.setCancelled(true);
        } else if (title.equals("Ban GUI")) {
            e.setCancelled(true);
            handleBanGUI(p, i);
        } else if (title.equals("Mute GUI")) {
            e.setCancelled(true);
            handleMuteGUI(p, i);
        }
    }

    private void handleStaffGUI(User p, @NotNull ItemStack currentItem) {
        Material itemMat = currentItem.getType();
        if (itemMat == Material.CRIMSON_DOOR) { // kick function
            Main.staffgui.put(p.getName(), GUIFunction.KICK);
            GUI.openKickGUI(p);
        } else if (itemMat == Material.RED_WOOL) { // warn function
            Main.staffgui.put(p.getName(), GUIFunction.WARN);
            GUI.openWarnGUI(p);
        } else if (itemMat == Material.NETHERITE_AXE) { // ban function
            Main.staffgui.put(p.getName(), GUIFunction.BAN);
            GUI.openBanGUI(p);
        } else if (itemMat == Material.BLUE_ICE) { // freeze function
            Main.staffgui.put(p.getName(), GUIFunction.FREEZE);
            GUI.openPlayerList(p);
        } else if (itemMat == Material.COBWEB) { // mute function
            Main.staffgui.put(p.getName(), GUIFunction.MUTE);
            GUI.openMuteGUI(p);
        } else if (itemMat == Material.COMPASS) { // teleport function
            Main.staffgui.put(p.getName(), GUIFunction.TELEPORT);
            GUI.openPlayerList(p);
        } else if (itemMat == Material.RED_BANNER) { // exit
            p.closeInventory();
        }
    }

    private void handleKickGUI(User p, @NotNull ItemStack currentItem) {
        Material item = currentItem.getType();
        if (item == Material.OAK_PLANKS) {
            Main.staffguiReason.put(p.getName(), RED + "You have been kicked by " + p.getName() + RED + "!");
            GUI.openPlayerList(p);
        } else if (item == Material.DARK_OAK_PLANKS) {
            Main.giveReasonForPunishment.add((p.getName()));
            p.sendMessage(Ban.PREFIX + "Please enter a reason.");
            p.closeInventory();
        } else if (item == Material.RED_BANNER) {
            GUI.openStaffGUI(p);
            Main.staffgui.remove(p.getName());
        }
    }

    private void handleWarnGUI(User p, @NotNull ItemStack currentItem) {
        Material item = currentItem.getType();
    }

    private void handleBanGUI(User p, @NotNull ItemStack currentItem) {
        Material item = currentItem.getType();
        if (item == Material.OAK_PLANKS) {
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been banned by " + YELLOW + p.getName() + RED + " for " + YELLOW + "Swearing" + RED + "!");
        } else if (item == Material.BIRCH_PLANKS) {
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been banned by " + YELLOW + p.getName() + RED + " for " + YELLOW + "Hacking" + RED + "!");
        } else if (item == Material.SPRUCE_PLANKS) {
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been banned by " + YELLOW + p.getName() + RED + " for " + YELLOW + "Advertising" + RED + "!");
        } else if (item == Material.JUNGLE_PLANKS) {
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been banned by " + YELLOW + p.getName() + RED + " for " + YELLOW + "Discriminating/Racism" + RED + "!");
        } else if (item == Material.DARK_OAK_PLANKS) {
            Main.giveReasonForPunishment.add((p.getName()));
            p.sendMessage(Ban.PREFIX + "Please enter a reason.");
            p.closeInventory();
        } else if (item == Material.RED_BANNER) {
            GUI.openStaffGUI(p);
            Main.staffgui.remove(p.getName());
        }
    }

    private void handleMuteGUI(User p, @NotNull ItemStack currentItem) {
        Material item = currentItem.getType();
        if (item == Material.BEDROCK) {

        } else if (item == Material.GRASS_BLOCK) {

        } else if (item == Material.RED_BANNER) {
            GUI.openStaffGUI(p);
            Main.staffgui.remove(p.getName());
        }
    }
}