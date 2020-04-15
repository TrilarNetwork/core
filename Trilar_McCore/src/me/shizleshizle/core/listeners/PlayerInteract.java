package me.shizleshizle.core.listeners;

import me.shizleshizle.core.commands.cmdutils.VanishUtils;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.utils.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        User p = new User(e.getPlayer());

        //vanish handling
        if (p.isVanished()) {
            if (e.getItem() != null) {
                if (e.getItem().getType() == Material.COMPASS) {
                    if (Objects.requireNonNull(e.getItem().getItemMeta()).hasDisplayName()) {
                        if (ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).equals("Player Selector")) {
                            GUI.openPlayerList(p);
                        }
                    }
                }
            }
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (e.getClickedBlock() != null) {
                    if (e.getClickedBlock().getType() != null) {
                        Material type = e.getClickedBlock().getType();
                        if (type.equals(Material.CHEST)) {
                            e.setCancelled(true);
                            Chest ch = (Chest) e.getClickedBlock().getState();
                            Inventory chi = ch.getInventory();
                            Location block = e.getClickedBlock().getLocation();
                            Location loc1 = new Location(block.getWorld(), block.getBlockX() + 1, block.getBlockY(), block.getBlockZ());
                            Location loc2 = new Location(block.getWorld(), block.getBlockX() - 1, block.getBlockY(), block.getBlockZ());
                            Location loc3 = new Location(block.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ() + 1);
                            Location loc4 = new Location(block.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ() - 1);
                            Block b1 = e.getClickedBlock().getWorld().getBlockAt(loc1);
                            Block b2 = e.getClickedBlock().getWorld().getBlockAt(loc2);
                            Block b3 = e.getClickedBlock().getWorld().getBlockAt(loc3);
                            Block b4 = e.getClickedBlock().getWorld().getBlockAt(loc4);
                            Material t1 = b1.getType();
                            Material t2 = b2.getType();
                            Material t3 = b3.getType();
                            Material t4 = b4.getType();
                            Inventory inv;
                            if (t1.equals(Material.CHEST)) {
                                inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                            } else if (t2.equals(Material.CHEST)) {
                                inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                            } else if (t3.equals(Material.CHEST)) {
                                inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                            } else if (t4.equals(Material.CHEST)) {
                                inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                            } else {
                                inv = Bukkit.getServer().createInventory(null, 27, "Chest");
                            }
                            inv.setContents(chi.getContents());
                            p.openInventory(inv);
                            VanishUtils.addInvs(p, inv);
                        } else if (type.equals(Material.TRAPPED_CHEST)) {
                            e.setCancelled(true);
                            Chest ch = (Chest) e.getClickedBlock().getState();
                            Inventory chi = ch.getInventory();
                            Location block = e.getClickedBlock().getLocation();
                            Location loc1 = new Location(block.getWorld(), block.getBlockX() + 1, block.getBlockY(), block.getBlockZ());
                            Location loc2 = new Location(block.getWorld(), block.getBlockX() - 1, block.getBlockY(), block.getBlockZ());
                            Location loc3 = new Location(block.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ() + 1);
                            Location loc4 = new Location(block.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ() - 1);
                            Block b1 = e.getClickedBlock().getWorld().getBlockAt(loc1);
                            Block b2 = e.getClickedBlock().getWorld().getBlockAt(loc2);
                            Block b3 = e.getClickedBlock().getWorld().getBlockAt(loc3);
                            Block b4 = e.getClickedBlock().getWorld().getBlockAt(loc4);
                            Material t1 = b1.getType();
                            Material t2 = b2.getType();
                            Material t3 = b3.getType();
                            Material t4 = b4.getType();
                            Inventory inv;
                            if (t1.equals(Material.TRAPPED_CHEST)) {
                                inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                            } else if (t2.equals(Material.TRAPPED_CHEST)) {
                                inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                            } else if (t3.equals(Material.TRAPPED_CHEST)) {
                                inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                            } else if (t4.equals(Material.TRAPPED_CHEST)) {
                                inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                            } else {
                                inv = Bukkit.getServer().createInventory(null, 27, "Chest");
                            }
                            inv.setContents(chi.getContents());
                            p.openInventory(inv);
                            VanishUtils.addInvs(p, inv);
                        } else if (type.equals(Material.FURNACE) || type.equals(Material.BLAST_FURNACE)) {
                            e.setCancelled(true);
                            Furnace ch = (Furnace) e.getClickedBlock().getState();
                            Inventory chi = ch.getInventory();
                            p.openInventory(chi);
                            VanishUtils.addInvs(p, chi);
                        } else if (type.equals(Material.HOPPER)) {
                            e.setCancelled(true);
                            Hopper ch = (Hopper) e.getClickedBlock().getState();
                            Inventory chi = ch.getInventory();
                            p.openInventory(chi);
                            VanishUtils.addInvs(p, chi);
                        } else if (type.equals(Material.BREWING_STAND)) {
                            e.setCancelled(true);
                            BrewingStand ch = (BrewingStand) e.getClickedBlock().getState();
                            Inventory chi = ch.getInventory();
                            p.openInventory(chi);
                            VanishUtils.addInvs(p, chi);
                        } else if (type.equals(Material.STONE_BUTTON) || type.equals(Material.OAK_BUTTON) || type.equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE)
                                || type.equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE) || type.equals(Material.OAK_PRESSURE_PLATE) || type.equals(Material.STONE_PRESSURE_PLATE)) {
                            e.setCancelled(true);
                        } else if (type.equals(Material.OAK_DOOR) || type.equals(Material.OAK_DOOR) || type.equals(Material.IRON_DOOR)
                                || type.equals(Material.IRON_DOOR)) {
                            e.setCancelled(true);
                            double x = e.getClickedBlock().getLocation().getBlockX();
                            double z = e.getClickedBlock().getLocation().getBlockZ();
                            double x1 = e.getPlayer().getLocation().getBlockX();
                            double z1 = e.getPlayer().getLocation().getBlockZ();
                            double y = e.getClickedBlock().getLocation().getBlockY();
                            if (e.getClickedBlock().getLocation().getWorld().getBlockAt((int) x, ((int) y) - 1, (int) z).getType()
                                    .equals(e.getClickedBlock().getType())) {
                                y = y - 1;
                            }
                            Location l = new Location(e.getClickedBlock().getLocation().getWorld(), x + 0.5, y, z + 0.5);
                            l.setPitch(e.getPlayer().getLocation().getPitch());
                            l.setYaw(e.getPlayer().getLocation().getYaw());
                            if (x == x1 && z == z1) {

                            } else if (x1 < x && z1 == z) {
                                l.setX(x + 1 + 0.5);
                            } else if (x1 > x && z1 == z) {
                                l.setX(x - 1 + 0.5);
                            } else if (x1 == x && z1 < z) {
                                l.setZ(z + 1 + 0.5);
                            } else if (x1 == x && z1 > z) {
                                l.setZ(z - 1 + 0.5);
                            }
                            p.teleport(l);
                        }
                    }
                    e.getClickedBlock().getType();
                    Material type = e.getClickedBlock().getType();
                    if (type.equals(Material.CHEST)) {
                        e.setCancelled(true);
                        Chest ch = (Chest) e.getClickedBlock().getState();
                        Inventory chi = ch.getInventory();
                        Location block = e.getClickedBlock().getLocation();
                        Location loc1 = new Location(block.getWorld(), block.getBlockX() + 1, block.getBlockY(), block.getBlockZ());
                        Location loc2 = new Location(block.getWorld(), block.getBlockX() - 1, block.getBlockY(), block.getBlockZ());
                        Location loc3 = new Location(block.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ() + 1);
                        Location loc4 = new Location(block.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ() - 1);
                        Block b1 = e.getClickedBlock().getWorld().getBlockAt(loc1);
                        Block b2 = e.getClickedBlock().getWorld().getBlockAt(loc2);
                        Block b3 = e.getClickedBlock().getWorld().getBlockAt(loc3);
                        Block b4 = e.getClickedBlock().getWorld().getBlockAt(loc4);
                        Material t1 = b1.getType();
                        Material t2 = b2.getType();
                        Material t3 = b3.getType();
                        Material t4 = b4.getType();
                        Inventory inv;
                        if (t1.equals(Material.CHEST)) {
                            inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                        } else if (t2.equals(Material.CHEST)) {
                            inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                        } else if (t3.equals(Material.CHEST)) {
                            inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                        } else if (t4.equals(Material.CHEST)) {
                            inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                        } else {
                            inv = Bukkit.getServer().createInventory(null, 27, "Chest");
                        }
                        inv.setContents(chi.getContents());
                        p.openInventory(inv);
                        VanishUtils.addInvs(p, inv);
                    } else if (type.equals(Material.TRAPPED_CHEST)) {
                        e.setCancelled(true);
                        Chest ch = (Chest) e.getClickedBlock().getState();
                        Inventory chi = ch.getInventory();
                        Location block = e.getClickedBlock().getLocation();
                        Location loc1 = new Location(block.getWorld(), block.getBlockX() + 1, block.getBlockY(), block.getBlockZ());
                        Location loc2 = new Location(block.getWorld(), block.getBlockX() - 1, block.getBlockY(), block.getBlockZ());
                        Location loc3 = new Location(block.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ() + 1);
                        Location loc4 = new Location(block.getWorld(), block.getBlockX(), block.getBlockY(), block.getBlockZ() - 1);
                        Block b1 = e.getClickedBlock().getWorld().getBlockAt(loc1);
                        Block b2 = e.getClickedBlock().getWorld().getBlockAt(loc2);
                        Block b3 = e.getClickedBlock().getWorld().getBlockAt(loc3);
                        Block b4 = e.getClickedBlock().getWorld().getBlockAt(loc4);
                        Material t1 = b1.getType();
                        Material t2 = b2.getType();
                        Material t3 = b3.getType();
                        Material t4 = b4.getType();
                        Inventory inv;
                        if (t1.equals(Material.TRAPPED_CHEST)) {
                            inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                        } else if (t2.equals(Material.TRAPPED_CHEST)) {
                            inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                        } else if (t3.equals(Material.TRAPPED_CHEST)) {
                            inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                        } else if (t4.equals(Material.TRAPPED_CHEST)) {
                            inv = Bukkit.getServer().createInventory(null, 54, "Chest");
                        } else {
                            inv = Bukkit.getServer().createInventory(null, 27, "Chest");
                        }
                        inv.setContents(chi.getContents());
                        p.openInventory(inv);
                        VanishUtils.addInvs(p, inv);
                    } else if (type.equals(Material.FURNACE)) {
                        e.setCancelled(true);
                        Furnace ch = (Furnace) e.getClickedBlock().getState();
                        Inventory chi = ch.getInventory();
                        p.openInventory(chi);
                        VanishUtils.addInvs(p, chi);
                    } else if (type.equals(Material.HOPPER)) {
                        e.setCancelled(true);
                        Hopper ch = (Hopper) e.getClickedBlock().getState();
                        Inventory chi = ch.getInventory();
                        p.openInventory(chi);
                        VanishUtils.addInvs(p, chi);
                    } else if (type.equals(Material.BREWING_STAND)) {
                        e.setCancelled(true);
                        BrewingStand ch = (BrewingStand) e.getClickedBlock().getState();
                        Inventory chi = ch.getInventory();
                        p.openInventory(chi);
                        VanishUtils.addInvs(p, chi);
                    } else if (type.equals(Material.OAK_DOOR) || type.equals(Material.IRON_DOOR)) {
                        e.setCancelled(true);
                        double x = e.getClickedBlock().getLocation().getBlockX();
                        double z = e.getClickedBlock().getLocation().getBlockZ();
                        double x1 = e.getPlayer().getLocation().getBlockX();
                        double z1 = e.getPlayer().getLocation().getBlockZ();
                        double y = e.getClickedBlock().getLocation().getBlockY();
                        if (Objects.requireNonNull(e.getClickedBlock().getLocation().getWorld()).getBlockAt((int) x, ((int) y) - 1, (int) z).getType()
                                .equals(e.getClickedBlock().getType())) {
                            y = y - 1;
                        }
                        Location l = new Location(e.getClickedBlock().getLocation().getWorld(), x + 0.5, y, z + 0.5);
                        l.setPitch(e.getPlayer().getLocation().getPitch());
                        l.setYaw(e.getPlayer().getLocation().getYaw());
                        if (x == x1 && z == z1) {

                        } else if (x1 < x && z1 == z) {
                            l.setX(x + 1 + 0.5);
                        } else if (x1 > x && z1 == z) {
                            l.setX(x - 1 + 0.5);
                        } else if (x1 == x && z1 < z) {
                            l.setZ(z + 1 + 0.5);
                        } else if (x1 == x && z1 > z) {
                            l.setZ(z - 1 + 0.5);
                        }
                        p.teleport(l);
                    } else {
                        e.setCancelled(true);
                    }
                }
            } else {
                e.setCancelled(true);
            }
        }
    }
}
