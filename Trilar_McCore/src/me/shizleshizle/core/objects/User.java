package me.shizleshizle.core.objects;

import java.net.InetSocketAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Player.Spigot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.Wild;
import me.shizleshizle.core.commands.cmdutils.VanishUtils;
import me.shizleshizle.core.commands.cmdutils.WarpUtils;
import me.shizleshizle.core.commands.warps.Warp;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.permissions.Prefix;
import me.shizleshizle.core.utils.CI;
import me.shizleshizle.core.utils.Cooldowns;
import me.shizleshizle.core.utils.NickNameManager;

public class User {
	private Player p;
	
	public User(Player p) {
		this.p = p;
	}

	public Player getUser() {
		return p;
	}
	
	public void setUser(Player p) {
		this.p = p;
	}
	
	public PermissionAttachment addAttachment(Plugin p) {
		return this.p.addAttachment(p);
	}
	
	public void addItem(ItemStack i) {
		p.getInventory().addItem(i);
	}
	
	public void addPermission(String perm) {
		ArrayList<String> perms = new ArrayList<>();
		perms.add(perm);
		addPermissions(perms);
	 }
	
	private void addPermissions(ArrayList<String> perm) {
		try {
			for (String pe : perm) {
				PreparedStatement s = Main.sql.getConnection().prepareStatement("INSERT INTO user_permissions (player, permission) VALUES ('" + getName() + "', '" + pe + "')");
				s.executeUpdate();
				s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addPotionEffect(PotionEffect e) {
		p.addPotionEffect(e);
	}
	
	public void addPotionEffects(Collection<PotionEffect> e) {
		p.addPotionEffects(e);
	}
	
	public boolean canSee(User u) {
		return p.canSee(u.getUser());
	}
	
	public void clearInventory() {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
    }
	
	public void closeInventory() {
		p.closeInventory();
	}
	
	public void freezeUser(boolean freeze) {
		if (freeze) {
			if (!Main.frozen.contains(p.getName())) {
				Main.frozen.add(p.getName());
			}
		} else {
			if (Main.frozen.contains(p.getName())) {
				Main.frozen.remove(p.getName());
			}
		}
	}
	
	public Collection<PotionEffect> getActivePotionEffects() {
		return p.getActivePotionEffects();
	}
	
	public InetSocketAddress getAddress() {
		return p.getAddress();
	}
	
	public boolean getAllowFlight() {
		return p.getAllowFlight();
	}
	
	public Location getBack() {
		if (Main.back.containsKey(p.getName())) {
			return Main.back.get(p.getName());
		} else {
			return null;
		}
	}
	
	public Location getBedSpawnLocation() {
		return p.getBedSpawnLocation();
	}
	
	public String getChatColor() {
		String cc;
		if (ChatColorHandler.hasColor(p.getName())) {
			cc = ChatColorHandler.getChatColor(p.getName());
		} else {
			cc = getGroup().getChatColor();
		}
		return cc;
	}
	
	public String getCustomName() {
		return p.getCustomName();
	}
	
	public String getDisplayName() {
		String nick = p.getName();
		if (hasNick()) {
			nick = getNick().trim();
		}
		if (Perm.getGroup(p.getName()) != null) {
			if (hasPrefix()) {
				nick = getPrefix().trim() + " " + nick;
			} else {
				if (Prefix.hasPrefix(getGroup())) {
					nick = Prefix.getPrefix(getGroup()).trim() + " " + nick;
				} else {
					nick = getGroup().getPrefix() + nick;
				}
			}
		} else {
			Perm.updateGroup(p.getName(), PermGroup.MEMBER);
			if (hasPrefix()) {
				nick = getPrefix().trim() + " " + nick;
			} else {
				nick = getGroup().getPrefix() + nick;
			}
		}
		return nick;
	}
	
	public Inventory getEnderchest() {
		return p.getEnderChest();
	}
	
	public float getExp() {
		return p.getExp();
	}
	
	public float getFallDistance() {
		return p.getFallDistance();
	}
	
	public int getFireTicks() {
		return p.getFireTicks();
	}
	
	public long getFirstPlayed() {
		return p.getFirstPlayed();
	}
	
	public float getFlySpeed() {
		return p.getFlySpeed();
	}
	
	public int getFoodLevel() {
		return p.getFoodLevel();
	}
	
	public GameMode getGameMode() {
		return p.getGameMode();
	}
	
	public PermGroup getGroup() {
		return Perm.getGroup(p.getName());
	}
	
	public double getHealth() {
		return p.getHealth();
	}
	
	public double getHealthScale() {
		return p.getHealthScale();
	}
	
	public PlayerInventory getInventory() {
		return p.getInventory();
	}
	
	public ItemStack getItemInHand() {
		return p.getItemInHand();
	}
	
	public ItemStack getItemOnCursor() {
		return p.getItemOnCursor();
	}
	
	public Player getKiller() {
		return p.getKiller();
	}
	
	public int getLevel() {
		return p.getLevel();
	}
	
	public Location getLocation() {
		return p.getLocation();
	}
	
	public double getMaxHealth() {
		return p.getMaxHealth();
	}
	
	public String getName() {
		return p.getName();
	}
	
	public String getNick() {
		if (NickNameManager.nicks.containsKey(p.getName())) {
			return ChatColor.translateAlternateColorCodes('&', NickNameManager.nicks.get(p.getName()));
		} else {
			return null;
		}
	}
	
	public InventoryView getOpenInventory() {
		return p.getOpenInventory();
	}
	
	public ArrayList<String> getPermissions() {
		ArrayList<String> perms = new ArrayList<>();
		Statement s;
		ResultSet rs;
		try {
			s = Main.sql.getConnection().createStatement();
			rs = s.executeQuery("SELECT * FROM user_permissions WHERE player='" + getName() + "'");
			while (rs.next()) {
				perms.add(rs.getString("permission"));
			}
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perms;
	}
	
	public String getPrefix() {
		return Prefix.getPrefix(p.getName());
	}
	
	public String getRawPrefix() {
		return Prefix.getRawPrefix(p.getName());
	}
	
	public int getRemainingCooldownTime() {
		int time = 0;
		if (hasCooldown()) {
			time = Cooldowns.cooldown.get(getUUID());
		}
		return time;
	}
	
	public Server getServer() {
		return p.getServer();
	}
	
	public int getTotalExperience() {
		return p.getTotalExperience();
	}
	
	public String getUserListName() {
		return p.getPlayerListName();
	}
	
	public long getUserTime() {
		return p.getPlayerTime();
	}
	
	public WeatherType getUserWeather() {
		return p.getPlayerWeather();
	}
	
	public UUID getUUID() {
		return p.getUniqueId();
	}
	
	public float getWalkSpeed() {
		return p.getWalkSpeed();
	}
	
	public World getWorld() {
		return p.getWorld();
	}
	
	/**
	 * Gives a player a cooldown
	 * If a player already has a cooldown, the time you enter will add up.
	 * 
	 * @param p 
	 * 			Name of the Player who should receive the cooldown
	 * @param time
	 * 			Time in seconds
	 */
	public void giveCooldown(int time) {
		if (hasCooldown()) {
			Cooldowns.cooldown.put(getUUID(), Cooldowns.cooldown.get(getUUID()) + time);
		} else {
			Cooldowns.cooldown.put(getUUID(), time);
		}
	}
	
	public boolean hasBack() {
		if (Main.back.containsKey(p.getName())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasChatColor() {
		return ChatColorHandler.hasColor(p.getName());
	}
	
	public boolean hasCooldown() {
		return Cooldowns.cooldown.containsKey(p.getUniqueId());
	}
	
	public boolean hasNick() {
		return NickNameManager.nicks.containsKey(p.getName());
	}
	
	public boolean hasPlayedBefore() {
		return p.hasPlayedBefore();
	}
	
	public boolean hasPotionEffect(PotionEffectType e) {
		return p.hasPotionEffect(e);
	}
	
	public boolean hasPrefix() {
		return Prefix.hasPrefix(p.getName());
	}
	
	public boolean hasTpDisabled() {
		if (Main.tptoggle.contains(p.getName())) {
			return true;
		} else {
			return false;
		}
	}
	
	public void heal(double health, int food) {
		if (health <= 20) {
			p.setHealthScale(20D);
			p.setHealth(health);
		} else {
			p.setHealthScale(health);
			p.setHealth(health);
		}
		p.setFoodLevel(food);
		p.setFireTicks(0);
		for (PotionEffect e : p.getActivePotionEffects()) {
			p.removePotionEffect(e.getType());
		}
	}
	
	public void heal(int health, int food) {
		if (health <= 20) {
			p.setHealthScale(20D);
			p.setHealth(health);
		} else {
			p.setHealthScale(health);
			p.setHealth(health);
		}
		p.setFoodLevel(food);
		p.setFireTicks(0);
		for (PotionEffect e : p.getActivePotionEffects()) {
			p.removePotionEffect(e.getType());
		}
	}
	
	public void hideUser(User u) {
		p.hidePlayer(u.getUser());
	}
	
	public boolean isAfk() {
		return Main.afks.contains(p.getName());
	}
	
	public boolean isBanned() {
		return p.isBanned();
	}
	
	public boolean isCustomNameVisible() {
		return p.isCustomNameVisible();
	}
	
	public boolean isDead() {
		return p.isDead();
	}
	
	public boolean isFlying() {
		return p.isFlying();
	}
	
	public boolean isFrozen() {
		return Main.frozen.contains(p.getName());
	}
	
	public boolean isGod() {
		return Main.gods.contains(p.getName());
	}
	
	@SuppressWarnings("deprecation")
	public boolean isOnGround() {
		return p.isOnGround();
	}
	
	public boolean isOnline() {
		return p.isOnline();
	}
	
	public boolean isOp() {
		return p.isOp();
	}
	
	public boolean isSneaking() {
		return p.isSneaking();
	}
	
	public boolean isSprinting() {
		return p.isSprinting();
	}
	
	public boolean isVanished() {
        return Main.vanished.contains(p.getName());
    }
	
	public boolean isWhitelisted() {
		return p.isWhitelisted();
	}
	
	public void kickUser(String reason) {
		p.kickPlayer(reason);
	}
	
	public void loadNick() {
		if (Main.c.getNicks().get("nicknames." + getUUID().toString() + ".nickname") != null) {
			String nick = Main.c.getNicks().getString("nicknames." + getUUID().toString() + ".nickname");
			setNick(nick);
		} else {
			setNick(p.getName());
		}
	}
	
	public InventoryView openInventory(Inventory i) {
		return p.openInventory(i);
	}
	
	public void openInventory(InventoryView i) {
		p.openInventory(i);
	}
	
	public InventoryView openWorkbench(Location loc, boolean open) {
		return p.openWorkbench(loc, open);
	}
	
	public void removeAttachment(PermissionAttachment pa) {
		p.removeAttachment(pa);
	}
	
	public void removeBack() {
		if (Main.back.containsKey(p.getName())) {
			Main.back.remove(p.getName());
		}
	}
	
	public void removeChatColor() {
		ChatColorHandler.removeChatColor(p.getName());
	}
	
	public void removeCooldown() {
		if (hasCooldown()) {
			Cooldowns.cooldown.remove(getUUID());
		}
	}
	
	public void removeNick() {
		setNick(ChatColor.RESET + p.getName());
		NickNameManager.nicks.remove(p.getName());
		setDisplayName(ChatColor.RED + p.getName());
		setUserListName(ChatColor.RESET + p.getName());
	}
	
	public void removePermissions(ArrayList<String> perm) {
		try {
			for (String pe : perm) {
				PreparedStatement s = Main.sql.getConnection().prepareStatement("DELETE FROM user_permissions WHERE player='" + getName() + "' AND permission='" + pe + "'");
				s.executeUpdate();
				s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removePotionEffect(PotionEffectType pe) {
		p.removePotionEffect(pe);
	}
	
	public void removePrefix() {
		Prefix.removePrefix(p.getName());
	}
	
	public void repairInHand() {
        ItemStack i = p.getItemInHand();
        if (i instanceof Repairable) {
            i.setDurability(i.getType().getMaxDurability());
        }
    }

    public void repairAll() {
        for (ItemStack c : p.getInventory().getContents()) {
            if (c instanceof Repairable) {
                c.setDurability(c.getType().getMaxDurability());
            }
        }
    }

    public void repairArmor() {
        for (ItemStack a : p.getEquipment().getArmorContents()) {
            if (a instanceof Repairable) {
                a.setDurability(a.getType().getMaxDurability());
            }
        }
    }
	
	public void resetMaxHealth() {
		p.resetMaxHealth();
	}
	
	public void resetUserTime() {
		p.resetPlayerTime();
	}
	
	public void resetUserWeather() {
		p.resetPlayerWeather();
	}
	
	public void saveNick() {
		if (hasNick()) {
			Main.c.getNicks().set("nicknames." + getUUID().toString() + ".nickname", NickNameManager.nicks.get(p.getName()));
			Main.c.getNicks().set("nicknames." + getUUID().toString() + ".username", getName());
		} else {
			Main.c.getNicks().set("nicknames." + getUUID().toString() + ".nickname", getName());
			Main.c.getNicks().set("nicknames." + getUUID().toString() + ".username", getName());
		}
	}
	
	public void sendMessage(String message) {
		p.sendMessage(message);
	}
	 
	public void setAfk(boolean afk) {
		if (afk) {
			Main.afks.add(p.getName());
			Bukkit.broadcastMessage(getDisplayName() + ChatColor.YELLOW.toString() + ChatColor.BOLD + " is now AFK.");
		} else {
			Main.afks.remove(p.getName());
			Bukkit.broadcastMessage(getDisplayName() + ChatColor.YELLOW.toString() + ChatColor.BOLD + " is no longer AFK.");
		}
	}
	
	public void setAllowFlight(boolean allowflight) {
		p.setAllowFlight(allowflight);
	}
	
	public void setBack(Location l) {
		Main.back.put(p.getName(), l);
	}
	
	@SuppressWarnings("deprecation")
	public void setBanned(boolean banned) {
		p.setBanned(banned);
	}
	
	public void setBedSpawnLocation(Location l) {
		p.setBedSpawnLocation(l);
	}
	
	public void setCanPickupItems(boolean canpickup) {
		p.setCanPickupItems(canpickup);
	}
	
	public void setChatColor(String cc) {
		ChatColorHandler.setChatColor(p.getName(), cc);
	}
	
	public void setCustomName(String name) {
		p.setCustomName(name);
	}
	
	public void setCustomNameVisble(boolean visible) {
		p.setCustomNameVisible(visible);
	}
	
	public void setDay(boolean allworlds) {
        if (allworlds) {
            for (World w : p.getServer().getWorlds()) {
                w.setTime(0);
            }
        } else {
            p.getWorld().setTime(0);
        }
    }
	
	public void setDisplayName(String displayname) {
		p.setDisplayName(displayname);
	}
	
	public void setExp(float exp) {
		p.setExhaustion(exp);
	}
	
	public void setFallDistance(float falldistance) {
		p.setFallDistance(falldistance);
	}
	
	public void setFireTicks(int ticks) {
		p.setFireTicks(ticks);
	}
	
	public void setFly(boolean fly) {
		if (fly) {
			setAllowFlight(true);
			setFlySpeed(0.2F);
		} else {
			setAllowFlight(false);
			setFlying(false);
		}
	}
	
	public void setFlying(boolean flying) {
		p.setFlying(flying);
	}
	
	public void setFlySpeed(float speed) {
		p.setFlySpeed(speed);
	}
	
	public void setFoodLevel(int foodlevel) {
		p.setFoodLevel(foodlevel);
	}
	
	public void setGameMode(GameMode gm) {
		p.setGameMode(gm);
	}
	
	public void setGod(boolean god) {
		if (god) {
			if (!Main.gods.contains(p.getName())) {
				Main.gods.add(p.getName());
			}
		} else {
			if (Main.gods.contains(p.getName())) {
				Main.gods.remove(p.getName());
			}
		}
	}
	
	public void setGroup(PermGroup group) {
		Perm.updateGroup(p.getName(), group);
	}
	
	public void setHealth(double health) {
		p.setHealth(health);
	}
	
	public void setHealthScale(double scale) {
		p.setHealthScale(scale);
	}
	
	public void setHealthScaled(boolean scaled) {
		p.setHealthScaled(scaled);
	}
	
	public void setItemInHand(ItemStack item) {
		p.setItemInHand(item);
	}
	
	public void setItemOnCursor(ItemStack item) {
		p.setItemOnCursor(item);
	}
	
	public void setLevel(int level) {
		p.setLevel(level);
	}
	
	public void setMaxHealth(double maxhealth) {
		p.setMaxHealth(maxhealth);
	}
	
	public void setNick(String nick) {
		NickNameManager.nicks.put(p.getName(), nick);
		nick = ChatColor.translateAlternateColorCodes('&', nick);
		setDisplayName(nick + ChatColor.RESET);
		setUserListName(nick + ChatColor.RESET);
		setCustomName(nick + ChatColor.RESET);
		saveNick();
	}
	
	public void setNight(boolean allworlds) {
        if (allworlds) {
            for (World w : p.getServer().getWorlds()) {
                w.setTime(13000);
            }
        } else {
            p.getWorld().setTime(13000);
        }
    }
	
	public void setOp(boolean op) {
		p.setOp(op);
	}
	
	public void setPrefix(String prefix) {
		Prefix.setPrefix(p.getName(), prefix);
	}
	
	public void setScoreboard(Scoreboard board) {
		p.setScoreboard(board);
	}
	
	public void setSneaking(boolean sneak) {
		p.setSneaking(sneak);
	}
	
	public void setSprinting(boolean sprint) {
		p.setSprinting(sprint);
	}
	
	public void setTotalExperience(int exp) {
		p.setTotalExperience(exp);
	}
	
	public void setTpDisabled(boolean dis) {
		if (dis) {
			if (Main.tptoggle.contains(p.getName())) {
				Main.tptoggle.remove(p.getName());
			}
		} else {
			Main.tptoggle.add(p.getName());
		}
	}
	
	public void setUserListName(String name) {
		p.setPlayerListName(name);
	}
	
	public void setUserTime(long time, boolean set) {
		p.setPlayerTime(time, set);
	}
	
	public void setUserWeather(WeatherType weather) {
		p.setPlayerWeather(weather);
	}
	
	public void setVanished(boolean vanish) {
        if (vanish) {
        	Main.vanished.add(p.getName());
            VanishUtils.pInv.put(p.getName(), p.getInventory().getContents());
            p.getInventory().clear();
            p.getInventory().addItem(new ItemStack(CI.createItem(Material.COMPASS, 1, -1, ChatColor.AQUA + "Player Selector")));
            setGod(true);
            p.setAllowFlight(true);
            p.setFlySpeed(0.3F);
            p.setFireTicks(0);
            p.setCanPickupItems(false);
            for (Player x : Bukkit.getOnlinePlayers()) {
                User ap = new User(x);
                if (!canSee(ap) || !Perm.hasPerm(ap, PermGroup.ADMIN)) {
                    x.hidePlayer(p);
                }
            }
        } else {
        	Main.vanished.remove(p.getName());
            p.getInventory().clear();
            if (VanishUtils.pInv.get(p.getName()) != null) {
                p.getInventory().setContents(VanishUtils.pInv.get(p.getName()));
                VanishUtils.pInv.remove(p.getName());
            }
            setGod(false);
            if (!p.getGameMode().equals(GameMode.CREATIVE) && !p.isFlying()) {
                p.setAllowFlight(false);
                p.setFlying(false);
            }
            if (hasNick()) {
                setUserListName(getNick());
            } else {
                setUserListName(p.getName());
            }
            toBack();
            removeBack();
            p.setFallDistance(0);
            p.setCanPickupItems(true);
            for (Player x : Bukkit.getOnlinePlayers()) {
            	x.showPlayer(p);
            }
        }
    }
	
	public void showUser(User u) {
		p.showPlayer(u.getUser());
	}
	
	public void setWalkSpeed(float speed) {
		p.setWalkSpeed(speed);
	}
	
	public void setWhitelisted(boolean whitelisted) {
		p.setWhitelisted(whitelisted);
	}
	
	public Spigot spigot() {
		return p.spigot();
	}
	
	public void teleport(Entity t) {
		p.teleport(t);
	}
	
	public void teleport(Location l) {
		p.teleport(l);
	}
	
	public void toBack() {
		if (Main.back.containsKey(getName())) {
			p.teleport(Main.back.get(getName()));
		}
	}
	
	public void toSpawn() {
        double x;
        double y;
        double z;
        float yaw;
        float pitch;
        World w;
        if (Main.c.getSpawn().get("spawn.x") != null && Main.c.getSpawn().get("spawn.y") != null && Main.c.getSpawn().get("spawn.z")
                != null && Main.c.getSpawn().get("spawn.yaw") != null && Main.c.getSpawn().get("spawn.pitch")
                != null && Main.c.getSpawn().get("spawn.world") != null) {
            x = Main.c.getSpawn().getDouble("spawn.x");
            y = Main.c.getSpawn().getDouble("spawn.y");
            z = Main.c.getSpawn().getDouble("spawn.z");
            yaw = (float) Main.c.getSpawn().getDouble("spawn.yaw");
            pitch = (float) Main.c.getSpawn().getDouble("spawn.pitch");
            w = Bukkit.getServer().getWorld(Main.c.getSpawn().getString("spawn.world"));
            Location l = new Location(w, x, y, z);
            l.setYaw(yaw);
            l.setPitch(pitch);
            p.teleport(l);
        } else {
            p.teleport(p.getWorld().getSpawnLocation());
        }
    }
	
	public void updateInventory() {
		p.updateInventory();
	}
	
	public void warp(String warp) {
        if (WarpUtils.warps.contains(warp)) {
            double x;
            double y;
            double z;
            float yaw;
            float pitch;
            World w;
            if (Main.c.getWarps().get("settings.warps." + warp + ".x") != null && Main.c.getWarps().get("settings.warps." + warp + ".y") != null &&
                    Main.c.getWarps().get("settings.warps." + warp + ".z") != null && Main.c.getWarps().get("settings.warps." + warp + ".yaw") != null &&
                    Main.c.getWarps().get("settings.warps." + warp + "pitch") != null && Main.c.getWarps().get("settings.warps." + warp + ".world") != null) {
                x = Main.c.getWarps().getDouble("settings.warps." + warp + ".x");
                y = Main.c.getWarps().getDouble("settings.warps." + warp + ".y");
                z = Main.c.getWarps().getDouble("settings.warps." + warp + ".z");
                yaw = (float) Main.c.getWarps().getDouble("settings.warps." + warp + ".yaw");
                pitch = (float) Main.c.getWarps().getDouble("settings.warps." + warp + ".pitch");
                w = Bukkit.getServer().getWorld(Main.c.getWarps().getString("settings.warps." + warp + ".world"));
                Location l = new Location(w, x, y, z);
                l.setYaw(yaw);
                l.setPitch(pitch);
                p.teleport(l);
                p.sendMessage(Warp.prefix + "You have been teleported to warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + "!");
            } else {
                p.sendMessage(Warp.prefix + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " hasn't been set correctly!");
            }
        } else {
            p.sendMessage(Warp.prefix + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " does not exist!");
        }
    }
	
	public void wild() {
        Location ogLoc = p.getLocation();
        Random random = new Random();
        Location tpLoc = null;
        int x = random.nextInt(1000) + 1;
        int y = 150;
        int z = random.nextInt(1000) + 1;
        boolean isOnLand = false;

        while (!isOnLand) {
            if (y == 0) {
                p.teleport(p.getWorld().getSpawnLocation());
                break;
            }
            tpLoc = new Location(p.getWorld(), x, y, z);

            if (tpLoc.getBlock().getType() != Material.AIR) {
                if (tpLoc.getBlock().isLiquid()) {
                    tpLoc.getBlock().setType(Material.GRASS);
                    Wild.l.add(p.getName());
                }
                isOnLand = true;
            } else {
                y--;
            }
        }
        p.teleport(new Location(p.getWorld(), tpLoc.getX(), tpLoc.getY() + 1, tpLoc.getZ()));
        p.sendMessage(Wild.prefix + "You have been teleported " + ChatColor.GOLD + (int) tpLoc.distance(ogLoc) + ChatColor.YELLOW + " blocks away!");
	}
}
