package me.shizleshizle.core.listeners;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.bansystem.Ban;
import me.shizleshizle.core.commands.bansystem.GUIFunction;
import me.shizleshizle.core.commands.cmdutils.VanishUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.utils.GUI;
import me.shizleshizle.core.utils.Numbers;
import me.shizleshizle.core.utils.StringHelper;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

import static org.bukkit.ChatColor.*;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Main.staffguiReason.remove(e.getPlayer().getName());
        Main.staffgui.remove(e.getPlayer().getName());
    }

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
            handlePlayerList(p, i);
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
            handleWarnGUI(p, i);
        } else if (title.equals("Ban GUI")) {
            e.setCancelled(true);
            handleBanGUI(p, i);
        } else if (title.equals("Mute GUI")) {
            e.setCancelled(true);
            handleMuteGUI(p, i);
        } else if (title.equalsIgnoreCase("Mute Time GUI")) {
            e.setCancelled(true);
            handleMuteTimeGUI(p, i, e.getClickedInventory());
        }
    }

    private void handlePlayerList(User p, @NotNull ItemStack currentItem) {
        if (currentItem.getType() == Material.PLAYER_HEAD) {
            if (currentItem.hasItemMeta()) {
                Player targetP = Bukkit.getPlayer(ChatColor.stripColor(Objects.requireNonNull(currentItem.getItemMeta()).getDisplayName()));
                if (targetP == null) {
                    p.sendMessage(Main.PREFIX + "This player does not exist!");
                    return;
                }
                User t = new User(targetP);
                if (Main.staffgui.containsKey(p.getName())) {
                    GUIFunction pFunction = Main.staffgui.get(p.getName());
                    String reason;
                    Clock c = null;
                    if (Main.staffguiReason.containsKey(p.getName())) {
                        reason = Main.staffguiReason.get(p.getName());
                    } else if (Main.muteReason.containsKey(p.getName())) {
                        c = Main.muteReason.get(p.getName());
                        reason = "Disconnected";
                    } else {
                        p.sendMessage(Ban.PREFIX + "Something went wrong, please contact a developer!");
                        return;
                    }
                    if (pFunction == GUIFunction.TELEPORT) {
                        p.teleport(t.getLocation());
                    } else if (pFunction == GUIFunction.KICK) {
                        t.kickUser(reason);
                        p.sendMessage(Ban.PREFIX + "Player " + GOLD + t.getName() + YELLOW + " has been kicked for: " + GOLD + reason);
                    } else if (pFunction == GUIFunction.WARN) {
                        t.warn(reason, p.getName());
                    } else if (pFunction == GUIFunction.BAN) {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(t.getName(), reason, null, p.getName());
                        t.kickUser(RED + "You have been banned by " + YELLOW + p.getName() + RED + " for " + YELLOW + reason);
                        p.sendMessage(Ban.PREFIX + "Player " + GOLD + t.getName() + YELLOW + " has been banned for: " + GOLD + reason);
                    } else if (pFunction == GUIFunction.MUTE) {
                        if (c != null) {
                            t.mute(c);
                        } else {
                            t.mute();
                        }
                        p.sendMessage(Ban.PREFIX + "Player " + GOLD + t.getName() + YELLOW + " has been muted!");
                        t.sendMessage(Ban.PREFIX + "You have been muted by " + GOLD + p.getName() + YELLOW + "!");
                    } else if (pFunction == GUIFunction.FREEZE) {
                        t.freezeUser(!t.isFrozen());
                    }
                    Main.staffguiReason.remove(p.getName());
                    Main.staffgui.remove(p.getName());
                }
                p.closeInventory();
            }
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
            Main.staffguiReason.put(p.getName(), " ");
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
        if (item == Material.OAK_PLANKS) {
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been warned by " + YELLOW + p.getName() + RED + " for " + YELLOW + "Swearing" + RED + "!");
        } else if (item == Material.BIRCH_PLANKS) {
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been warned by " + YELLOW + p.getName() + RED + " for " + YELLOW + "Hacking" + RED + "!");
        } else if (item == Material.SPRUCE_PLANKS) {
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been warned by " + YELLOW + p.getName() + RED + " for " + YELLOW + "Advertising" + RED + "!");
        } else if (item == Material.JUNGLE_PLANKS) {
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been warned by " + YELLOW + p.getName() + RED + " for " + YELLOW + "Discriminating/Racism" + RED + "!");
        } else if (item == Material.DARK_OAK_PLANKS) {
            Main.giveReasonForPunishment.add((p.getName()));
            p.sendMessage(Ban.PREFIX + "Please enter a reason.");
            p.closeInventory();
        } else if (item == Material.RED_BANNER) {
            GUI.openStaffGUI(p);
            Main.staffgui.remove(p.getName());
        }
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
            GUI.openPlayerList(p);
            Main.staffguiReason.put(p.getName(), RED + "You have been muted by " + YELLOW + p.getName() + RED + "!");
        } else if (item == Material.GRASS_BLOCK) {
            GUI.openMuteTimeGUI(p);
        } else if (item == Material.RED_BANNER) {
            GUI.openStaffGUI(p);
            Main.staffgui.remove(p.getName());
        }
    }

    private void handleMuteTimeGUI(User p, @NotNull ItemStack currentItem, Inventory clickedInv) {
        Material item = currentItem.getType();
        if (item == Material.LIME_WOOL) {
            Instant now = Instant.now();
            String days = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(clickedInv.getItem(18)).getItemMeta()).getDisplayName());
            String hours = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(clickedInv.getItem(20)).getItemMeta()).getDisplayName());
            String minutes = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(clickedInv.getItem(22)).getItemMeta()).getDisplayName());
            String seconds = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(clickedInv.getItem(24)).getItemMeta()).getDisplayName());
            final int removeDays = 6; // day(s)
            final int removeHours = 7; // hour(s)
            final int removeMinutes = 9; // minute(s)
            final int removeSeconds = 9; // second(s)
            days = days.substring(0, days.length() - removeDays).trim();
            hours = hours.substring(0, hours.length() - removeHours).trim();
            minutes = minutes.substring(0, minutes.length() - removeMinutes).trim();
            seconds = seconds.substring(0, seconds.length() - removeSeconds).trim();
            if (Numbers.isNumber(days) && Numbers.isNumber(hours) && Numbers.isNumber(minutes) && Numbers.isNumber(seconds)) {
                final int dayDur = Numbers.getInt(days);
                final int hourDur = Numbers.getInt(hours);
                final int minuteDur = Numbers.getInt(minutes);
                final int secondDur = Numbers.getInt(seconds);
                Instant mutedUntil = now.plus(Duration.ofDays(dayDur));
                mutedUntil = mutedUntil.plus(Duration.ofHours(hourDur));
                mutedUntil = mutedUntil.plus(Duration.ofMinutes(minuteDur));
                mutedUntil = mutedUntil.plus(Duration.ofSeconds(secondDur));
                final Clock muted = Clock.fixed(mutedUntil, ZoneId.systemDefault());
                Main.muteReason.put(p.getName(), muted);
                GUI.openPlayerList(p);
            } else {
                p.sendMessage(Ban.PREFIX + "Something went wrong, please contact a developer!");
            }
        } else if (item == Material.WARPED_PLANKS) {
            String title = ChatColor.stripColor(Objects.requireNonNull(currentItem.getItemMeta()).getDisplayName());
            if (title.endsWith("day")) {
                ItemStack change = clickedInv.getItem(18); // get the block that represents time to mute
                assert change != null;
                int counter = getNumber(change, 3);
                if (counter < 30) {
                    counter++;
                } else {
                    p.sendMessage(Ban.PREFIX + "Days in GUI cannot be bigger than 30! Please use the command instead.");
                }
                ItemStack newItem = changeTitle(change, counter);
                clickedInv.setItem(18, newItem);
            } else if (title.endsWith("hour")) {
                ItemStack change = clickedInv.getItem(20); // get the block that represents time to mute
                assert change != null;
                int counter = getNumber(change, 4);
                if (counter < 24) {
                    counter++;
                } else {
                    counter = 0;
                    ItemStack days = clickedInv.getItem(18);
                    assert days != null;
                    int dCounter = getNumber(days, 3);
                    changeTitle(change, counter);
                    changeTitle(days, ++dCounter);
                    return;
                }
                ItemStack newItem = changeTitle(change, counter);
                clickedInv.setItem(20, newItem);
            } else if (title.endsWith("minute")) {
                ItemStack change = clickedInv.getItem(22); // get the block that represents time to mute
                assert change != null;
                int counter = getNumber(change, 6);
                if (counter < 60) {
                    counter++;
                } else {
                    counter = 0;
                    ItemStack hour = clickedInv.getItem(20);
                    assert hour != null;
                    int hCounter = getNumber(hour, 4);
                    changeTitle(change, counter);
                    changeTitle(hour, ++hCounter);
                    return;
                }
                ItemStack newItem = changeTitle(change, counter);
                clickedInv.setItem(22, newItem);
            } else if (title.endsWith("second")) {
                ItemStack change = clickedInv.getItem(24); // get the block that represents time to mute
                assert change != null;
                int counter = getNumber(change, 6);
                if (counter < 60) {
                    counter++;
                } else {
                    counter = 0;
                    ItemStack minute = clickedInv.getItem(22);
                    assert minute != null;
                    int mCounter = getNumber(minute, 6);
                    changeTitle(change, counter);
                    changeTitle(minute, ++mCounter);
                    return;
                }
                ItemStack newItem = changeTitle(change, counter);
                clickedInv.setItem(24, newItem);
            }
        } else if (item == Material.CRIMSON_PLANKS) {
            String title = ChatColor.stripColor(Objects.requireNonNull(currentItem.getItemMeta()).getDisplayName());
            if (title.endsWith("day")) {
                ItemStack change = clickedInv.getItem(18); // get the block that represents time to mute
                assert change != null;
                int counter = getNumber(change, 3);
                if (counter > 0) {
                    counter--;
                }
                ItemStack newItem = changeTitle(change, counter);
                clickedInv.setItem(18, newItem);
            } else if (title.endsWith("hour")) {
                ItemStack change = clickedInv.getItem(20); // get the block that represents time to mute
                assert change != null;
                int counter = getNumber(change, 4);
                if (counter > 0) {
                    counter--;
                }
                ItemStack newItem = changeTitle(change, counter);
                clickedInv.setItem(20, newItem);
            } else if (title.endsWith("minute")) {
                ItemStack change = clickedInv.getItem(22); // get the block that represents time to mute
                assert change != null;
                int counter = getNumber(change, 6);
                if (counter > 0) {
                    counter--;
                }
                ItemStack newItem = changeTitle(change, counter);
                clickedInv.setItem(22, newItem);
            } else if (title.endsWith("second")) {
                ItemStack change = clickedInv.getItem(24); // get the block that represents time to mute
                assert change != null;
                int counter = getNumber(change, 6);
                if (counter > 0) {
                    counter--;
                }
                ItemStack newItem = changeTitle(change, counter);
                clickedInv.setItem(24, newItem);
            }
        } else if (item == Material.RED_BANNER) {
            p.closeInventory();
            GUI.openMuteGUI(p);
        }
    }

    @Nullable
    private ItemStack changeTitle(ItemStack i, int newNumber) {
        ItemMeta im = i.getItemMeta();
        assert im != null;
        String objTitle = ChatColor.stripColor(im.getDisplayName());
        String[] chars = StringHelper.splitBySpace(objTitle);
        if (!Numbers.isNumber(chars[0])) {
            Bukkit.getLogger().info("null");
        }
        chars[0] = newNumber + "";
        StringBuilder sb = new StringBuilder();
        for (String part : chars) {
            sb.append(part).append(" ");
        }
        im.setDisplayName(sb.toString().trim());
        i.setItemMeta(im);
        return i;
    }

    private int getNumber(ItemStack i, int lettersToRemove) {
        String[] chars = StringHelper.splitBySpace(ChatColor.stripColor(Objects.requireNonNull(i.getItemMeta()).getDisplayName()));
        if (Numbers.isNumber(chars[0])) {
            return Numbers.getInt(chars[0]);
        } else {
            Bukkit.getLogger().info("0 zero");
            return 0;
        }
    }
}