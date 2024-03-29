package me.shizleshizle.core.objects;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.commands.Wild;
import me.shizleshizle.core.commands.cmdutils.VanishUtils;
import me.shizleshizle.core.commands.cmdutils.WarnUtils;
import me.shizleshizle.core.commands.cmdutils.WarpUtils;
import me.shizleshizle.core.commands.messaging.Msg;
import me.shizleshizle.core.commands.vaults.utils.VaultHandler;
import me.shizleshizle.core.commands.warps.Warp;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.permissions.PrefixHelper;
import me.shizleshizle.core.utils.CI;
import me.shizleshizle.core.utils.Cooldowns;
import me.shizleshizle.core.utils.NickNameManager;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Player.Spigot;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;
import java.sql.*;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

import static org.bukkit.ChatColor.*;

public class User {
    private final Clock permanentMute;
    private Player p;

    public User(Player p) {
        this.p = p;
        Instant now = Instant.now();
        Duration tenYears = Duration.ofDays(365 * 10);
        Instant future = now.plus(tenYears);
        permanentMute = Clock.fixed(future, ZoneId.systemDefault());
    }

    @Nullable
    public static User getUser(@NotNull String name) {
        Player t = Bukkit.getPlayerExact(name);
        if (t == null) return null;
        return new User(t);
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

    public boolean canCreateNewVault() {
        int hasVaults = VaultHandler.getAmountOfVaults(getName());
        if (Perm.hasPerm(getName(), PermGroup.ADMIN)) {
            return true;
        } else if (Perm.hasPerm(getName(), PermGroup.MODERATOR)) {
            return (hasVaults < 5);
        } else if (Perm.hasPerm(getName(), PermGroup.VIP)) {
            return (hasVaults < 1);
        }
        return false;
    }

    public void clearInventory() {
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
    }

    public void closeInventory() {
        p.closeInventory();
    }

    public void createVault(int vaultNumber, int... size) {
        int invSize = size.length > 0 ? size[0] : 54;
        VaultHandler.addVault(getName(), Bukkit.createInventory(null, invSize, getName() + "'s Vault " + vaultNumber), vaultNumber);
    }

    public void freezeUser(boolean freeze) {
        if (freeze) {
            if (!Main.frozen.contains(p.getName())) {
                Main.frozen.add(p.getName());
            }
        } else {
            Main.frozen.remove(p.getName());
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

    public void setAllowFlight(boolean allowflight) {
        p.setAllowFlight(allowflight);
    }

    public Location getBack() {
        Location l = null;
        if (Main.back.containsKey(p.getName())) {
            l = Main.back.get(p.getName());
        }
        return l;
    }

    public void setBack(Location l) {
        Main.back.put(p.getName(), l);
    }

    public Location getBedSpawnLocation() {
        return p.getBedSpawnLocation();
    }

    public void setBedSpawnLocation(Location l) {
        p.setBedSpawnLocation(l);
    }

    public String getChatColorString() {
        String cc;
        if (ChatColorHandler.hasColor(p.getName())) {
            cc = ChatColorHandler.getChatColor(p.getName());
        } else {
            cc = getGroup().getChatColor();
        }
        return cc;
    }

    @Nullable
    public ChatColor getChatColor() {
        return ChatColor.getByChar(getChatColorString().substring(1));
    }

    public void setChatColor(String cc) {
        ChatColorHandler.setChatColor(p.getName(), cc);
    }

    public String getCustomName() {
        return p.getCustomName();
    }

    public void setCustomName(String name) {
        p.setCustomName(name);
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
                if (PrefixHelper.hasPrefix(getGroup())) {
                    nick = PrefixHelper.getPrefix(getGroup()).trim() + " " + nick;
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

    public void setDisplayName(String displayname) {
        p.setDisplayName(displayname);
    }

    public Inventory getEnderchest() {
        return p.getEnderChest();
    }

    public float getExp() {
        return p.getExp();
    }

    public void setExp(float exp) {
        p.setExhaustion(exp);
    }

    public Location getEyeLocation() {
        return p.getEyeLocation();
    }

    public float getFallDistance() {
        return p.getFallDistance();
    }

    public void setFallDistance(float falldistance) {
        p.setFallDistance(falldistance);
    }

    public int getFireTicks() {
        return p.getFireTicks();
    }

    public void setFireTicks(int ticks) {
        p.setFireTicks(ticks);
    }

    public long getFirstPlayed() {
        return p.getFirstPlayed();
    }

    public float getFlySpeed() {
        return p.getFlySpeed();
    }

    public void setFlySpeed(float speed) {
        p.setFlySpeed(speed);
    }

    public int getFoodLevel() {
        return p.getFoodLevel();
    }

    public void setFoodLevel(int foodlevel) {
        p.setFoodLevel(foodlevel);
    }

    public String getFooter() {
        return p.getPlayerListFooter();
    }

    public GameMode getGameMode() {
        return p.getGameMode();
    }

    public void setGameMode(GameMode gm) {
        p.setGameMode(gm);
    }

    public PermGroup getGroup() {
        return Perm.getGroup(p.getName());
    }

    public void setGroup(PermGroup group) {
        Perm.updateGroup(p.getName(), group);
    }

    public String getHeader() {
        return p.getPlayerListHeader();
    }

    public double getHealth() {
        return p.getHealth();
    }

    public void setHealth(double health) {
        p.setHealth(health);
    }

    public double getHealthScale() {
        return p.getHealthScale();
    }

    public void setHealthScale(double scale) {
        p.setHealthScale(scale);
    }

    public PlayerInventory getInventory() {
        return p.getInventory();
    }

    public ItemStack getItemInMainHand() {
        return p.getInventory().getItemInMainHand();
    }

    public ItemStack getItemInOffHand() {
        return p.getInventory().getItemInOffHand();
    }

    public void setItemInOffHand(ItemStack item) {
        p.getInventory().setItemInOffHand(item);
    }

    public ItemStack getItemOnCursor() {
        return p.getItemOnCursor();
    }

    public void setItemOnCursor(ItemStack item) {
        p.setItemOnCursor(item);
    }

    public Player getKiller() {
        return p.getKiller();
    }

    @Nullable
    public User getLastMessager() {
        String messagerName = "";
        if (Main.messaging.containsKey(getName())) {
            messagerName = Main.messaging.get(getName());
        } else if (Main.messaging.containsValue(getName())) {
            for (String pl : Main.messaging.keySet()) {
                if (Main.messaging.get(pl).equals(getName())) {
                    messagerName = pl;

                }
            }
        }
        return new User(Bukkit.getPlayerExact(messagerName));
    }

    public int getLevel() {
        return p.getLevel();
    }

    public void setLevel(int level) {
        p.setLevel(level);
    }

    public Location getLocation() {
        return p.getLocation();
    }

    public AttributeInstance getMaxHealth() {
        return p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
    }

    public String getName() {
        return p.getName();
    }

    public List<Entity> getNearbyEntities(double x, double y, double z) {
        return p.getNearbyEntities(x, y, z);
    }

    public String getNick() {
        if (NickNameManager.nicks.containsKey(p.getName())) {
            return ChatColor.translateAlternateColorCodes('&', NickNameManager.nicks.get(p.getName()));
        } else {
            return null;
        }
    }

    public void setNick(String nick) {
        NickNameManager.nicks.put(p.getName(), nick);
        nick = ChatColor.translateAlternateColorCodes('&', nick);
        setDisplayName(nick + ChatColor.RESET);
        setUserListName(nick + ChatColor.RESET);
        setCustomName(nick + ChatColor.RESET);
        saveNick();
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
        return PrefixHelper.getPrefix(p.getName());
    }

    public void setPrefix(String prefix) {
        PrefixHelper.setPrefix(p.getName(), prefix);
    }

    public String getRawPrefix() {
        return PrefixHelper.getRawPrefix(p.getName());
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

    public void setTotalExperience(int exp) {
        p.setTotalExperience(exp);
    }

    public String getUserListName() {
        return p.getPlayerListName();
    }

    public void setUserListName(String name) {
        p.setPlayerListName(name);
    }

    public long getUserTime() {
        return p.getPlayerTime();
    }

    public WeatherType getUserWeather() {
        return p.getPlayerWeather();
    }

    public void setUserWeather(WeatherType weather) {
        p.setPlayerWeather(weather);
    }

    public UUID getUUID() {
        return p.getUniqueId();
    }

    public float getWalkSpeed() {
        return p.getWalkSpeed();
    }

    public void setWalkSpeed(float speed) {
        p.setWalkSpeed(speed);
    }

    public int getWarnAmount() {
        return WarnUtils.getWarnAmount(getName());
    }

    public String[] getWarns() {
        return WarnUtils.getWarns(getName());
    }

    public World getWorld() {
        return p.getWorld();
    }

    /**
     * Gives a player a cooldown
     * If a player already has a cooldown, the time you enter will add up.
     *
     * @param time Time in seconds
     */
    public void giveCooldown(int time) {
        if (hasCooldown()) {
            Cooldowns.cooldown.put(getUUID(), Cooldowns.cooldown.get(getUUID()) + time);
        } else {
            Cooldowns.cooldown.put(getUUID(), time);
        }
    }

    public boolean hasBack() {
        return (Main.back.containsKey(p.getName()));
    }

    public boolean hasChatColor() {
        return ChatColorHandler.hasColor(p.getName());
    }

    public boolean hasCooldown() {
        return Cooldowns.cooldown.containsKey(p.getUniqueId());
    }

    public boolean hasLastMessager() {
        return (Main.messaging.containsKey(getName()) || Main.messaging.containsValue(getName()));
    }

    public boolean hasNick() {
        return NickNameManager.nicks.containsKey(p.getName());
    }

    public boolean hasPlayedBefore() {
        return p.hasPlayedBefore();
    }

    public boolean hasPermission(PermGroup pg) {
        return Perm.hasPerm(getName(), pg);
    }

    public boolean hasPotionEffect(PotionEffectType e) {
        return p.hasPotionEffect(e);
    }

    public boolean hasPrefix() {
        return PrefixHelper.hasPrefix(p.getName());
    }

    public boolean hasSocialSpyEnabled() {
        return Main.socialspiers.contains(getName());
    }

    public boolean hasTpDisabled() {
        return Main.tptoggle.contains(p.getName());
    }

    public boolean hasVault(int vaultNumber) {
        return VaultHandler.hasVault(getName(), vaultNumber);
    }

    public boolean hasAnyVaults() {
        return VaultHandler.hasAnyVaults(getName());
    }

    public boolean hasWarn(int id) {
        return WarnUtils.hasWarn(getName(), id);
    }

    public void heal() {
        p.setHealthScale(20D);
        p.setHealth(20);
        setFoodLevel(20);
        setFireTicks(0);
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
        p.hidePlayer(Main.p, u.getUser());
    }

    public boolean isAfk() {
        return Main.afks.contains(p.getName());
    }

    public void setAfk(boolean afk) {
        if (afk) {
            Main.afks.add(p.getName());
            Bukkit.broadcastMessage(getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD + " is now AFK.");
        } else {
            Main.afks.remove(p.getName());
            Bukkit.broadcastMessage(getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD + " is no longer AFK.");
        }
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

    public void setFlying(boolean flying) {
        p.setFlying(flying);
    }

    public boolean isFrozen() {
        return Main.frozen.contains(p.getName());
    }

    public boolean isGod() {
        return Main.gods.contains(p.getName());
    }

    public void setGod(boolean god) {
        if (god) {
            if (!Main.gods.contains(p.getName())) {
                Main.gods.add(p.getName());
                heal(20, 20);
                setFireTicks(0);
                untarget();
            }
        } else {
            Main.gods.remove(p.getName());
        }
    }

    public boolean isMuted() {
        boolean muted = false;
        try {
            Statement s = Main.sql.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Muted WHERE player='" + getName() + "';");
            if (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("mutedUntil");
                Instant time = timestamp.toInstant();
                muted = !(time.isBefore(Instant.now()));
                if (!muted) {
                    unmute();
                }
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> SQL Error: " + e);
        }
        return muted;
    }

    public boolean isOnGround() {
        return p.isOnGround();
    }

    public boolean isOnline() {
        return p.isOnline();
    }

    public boolean isOp() {
        return p.isOp();
    }

    public void setOp(boolean op) {
        p.setOp(op);
    }

    public boolean isSneaking() {
        return p.isSneaking();
    }

    public void setSneaking(boolean sneak) {
        p.setSneaking(sneak);
    }

    public boolean isSprinting() {
        return p.isSprinting();
    }

    public void setSprinting(boolean sprint) {
        p.setSprinting(sprint);
    }

    public boolean isTalkingInStaffChat() {
        return Main.staffchat.contains(getName());
    }

    public boolean isTargetable() {
        return !Main.untargetable.contains(getName());
    }

    public void setTargetable(boolean targetable) {
        if (targetable) {
            Main.untargetable.remove(getName());
        } else {
            if (!Main.untargetable.contains(getName())) {
                Main.untargetable.add(getName());
            }
        }
    }

    public boolean isVanished() {
        return Main.vanished.contains(p.getName());
    }

    /**
     * Vanish or unvanish a player.
     *
     * @param vanish When true the player will be vanished, when false the player will be unvanished.
     */
    public void setVanished(boolean vanish) {
        if (vanish) {
            Main.vanished.add(p.getName());
            VanishUtils.pInv.put(p.getName(), p.getInventory().getContents());
            p.getInventory().clear();
            addItem(new ItemStack(CI.createItem(Material.COMPASS, 1, -1, ChatColor.AQUA + "Player Selector")));
            setGod(true);
            p.setAllowFlight(true);
            p.setFlySpeed(0.3F);
            p.setFireTicks(0);
            p.setCanPickupItems(false);
            for (Player x : Bukkit.getOnlinePlayers()) {
                User ap = new User(x);
                if (!canSee(ap) || !Perm.hasPerm(ap, PermGroup.ADMIN)) {
                    x.hidePlayer(Main.p, p);
                }
            }
            untarget();
        } else {
            if (Main.vanished.contains(p.getName())) {
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
                removeBack();
                p.setFallDistance(0);
                p.setCanPickupItems(true);
                for (Player x : Bukkit.getOnlinePlayers()) {
                    x.showPlayer(Main.p, p);
                }
            }
        }
    }

    public boolean isWhitelisted() {
        return p.isWhitelisted();
    }

    public void setWhitelisted(boolean whitelisted) {
        p.setWhitelisted(whitelisted);
    }

    public void kickUser(String reason) {
        p.kickPlayer(reason);
    }

    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        return p.launchProjectile(projectile);
    }

    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector vel) {
        return p.launchProjectile(projectile, vel);
    }

    public void loadNick() {
        if (Main.c.getNicks().get("nicknames." + getUUID().toString() + ".nickname") != null) {
            String nick = Main.c.getNicks().getString("nicknames." + getUUID().toString() + ".nickname");
            setNick(nick);
        } else {
            setNick(p.getName());
        }
    }

    public void message(User target, String msg) {
        target.sendMessage(GOLD + getName() + YELLOW + " > " + GOLD + "You" + YELLOW + " >> " + WHITE + msg);
        sendMessage(GOLD + "You" + YELLOW + " > " + GOLD + target.getName() + YELLOW + " >> " + WHITE + msg);
        if (!isMessaging(getName(), target.getName())) {
            if (isMessaging(getName())) removeMessaging(getName());
            if (isMessaging(target.getName())) removeMessaging(target.getName());
            Main.messaging.put(getName(), target.getName());
        }
        for (String playerName : Main.socialspiers) {
            Player spy = Bukkit.getPlayerExact(playerName);
            if (spy != null && spy.isOnline()) {
                if (!spy.getName().equals(target.getName()) && !spy.getName().equals(getName())) {
                    spy.sendMessage(GOLD + getName() + YELLOW + " > " + GOLD + target.getName() + YELLOW + " >> " + WHITE + msg);
                }
            }
        }
    }

    private boolean isMessaging(String p) {
        return Main.messaging.containsKey(p) || Main.messaging.containsValue(p);
    }

    private boolean isMessaging(String p1, String p2) {
        return Main.messaging.containsKey(p1) && Main.messaging.get(p1).equals(p2);
    }

    private void removeMessaging(String p) {
        if (Main.messaging.containsKey(p)) {
            Main.messaging.remove(p);
        } else if (Main.messaging.containsValue(p)) {
            for (String pl : Main.messaging.keySet()) {
                if (Main.messaging.get(pl).equals(p)) {
                    Main.messaging.remove(pl, p);
                }
            }
        }
    }

    public void mute(Clock... until) {
        Clock mutedUntil = permanentMute;
        if (until.length > 0) {
            mutedUntil = until[0];
        }
        try {
            Statement mute = Main.sql.getConnection().createStatement();
            Timestamp ts = Timestamp.from(mutedUntil.instant());
            String statement = isMuted() ? "UPDATE Muted SET mutedUntil='" + mutedUntil + "' WHERE player='" + getName() + "';" : "INSERT INTO Muted (player, mutedUntil) VALUES ('"
                    + getName() + "', '" + ts + "');";
            mute.executeUpdate(statement);
            mute.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> SQL Error: " + e);
        }
    }

    public InventoryView openInventory(Inventory i) {
        return p.openInventory(i);
    }

    public void openInventory(InventoryView i) {
        p.openInventory(i);
    }

    public void openVault(int vaultNumber) {
        openInventory(VaultHandler.getInventory(getName(), vaultNumber));
    }

    public void openOtherVault(String name, int vaultNumber) {
        openInventory(VaultHandler.getInventory(name, vaultNumber));
    }

    public InventoryView openWorkbench(Location loc, boolean open) {
        return p.openWorkbench(loc, open);
    }

    public void removeAttachment(PermissionAttachment pa) {
        p.removeAttachment(pa);
    }

    public void removeBack() {
        Main.back.remove(p.getName());
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
        PrefixHelper.removePrefix(p.getName());
    }

    public void removeWarn(int id) {
        WarnUtils.RemoveWarn(getName(), id);
    }

    public void repairInHand() {
        ItemStack i = getItemInMainHand();
        Damageable meta = (Damageable) i.getItemMeta();
        assert meta != null;
        meta.setDamage(0);
        i.setItemMeta((ItemMeta) meta);
    }

    public void repairAll() {
        for (ItemStack c : p.getInventory().getContents()) {
            if (c == null) continue;
            repairObject(c);
        }
        for (ItemStack c : p.getInventory().getArmorContents()) {
            if (c == null) continue;
            repairObject(c);
        }
        for (ItemStack c : p.getInventory().getExtraContents()) {
            if (c == null) continue;
            repairObject(c);
        }
        for (ItemStack c : p.getInventory().getStorageContents()) {
            if (c == null) continue;
            repairObject(c);
        }
    }

    public void repairArmor() {
        for (ItemStack a : Objects.requireNonNull(p.getEquipment()).getArmorContents()) {
            if (a == null) continue;
            Damageable meta = (Damageable) a.getItemMeta();
            assert meta != null;
            meta.setDamage(0);
            a.setItemMeta((ItemMeta) meta);
        }
    }

    private void repairObject(ItemStack obj) {
        Damageable meta = (Damageable) obj.getItemMeta();
        assert meta != null;
        meta.setDamage(0);
        obj.setItemMeta((ItemMeta) meta);
    }

    public void reply(String msg) {
        if (hasLastMessager()) {
            User lastMessager = getLastMessager();
            if (lastMessager == null) {
                sendMessage(Msg.PREFIX + "You have no one to reply to!");
                return;
            }
            lastMessager.sendMessage(GOLD + getName() + YELLOW + " > " + GOLD + "You" + YELLOW + " >> " + WHITE + msg);
            sendMessage(GOLD + "You" + YELLOW + " > " + GOLD + lastMessager.getName() + YELLOW + " >> " + WHITE + msg);
            for (String playerName : Main.socialspiers) {
                Player spy = Bukkit.getPlayerExact(playerName);
                if (spy != null && spy.isOnline()) {
                    if (!spy.getName().equals(lastMessager.getName()) && !spy.getName().equals(getName())) {
                        spy.sendMessage(GOLD + getName() + YELLOW + " > " + GOLD + lastMessager.getName() + YELLOW + " >> " + WHITE + msg);
                    }
                }
            }
        } else {
            sendMessage(Msg.PREFIX + "You have no one to reply to!");
        }
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

    public void sendFooter(String footer) {
        p.setPlayerListFooter(footer);
    }

    public void sendHeader(String header) {
        p.setPlayerListHeader(header);
    }

    public void sendHeaderAndFooter(String header, String footer) {
        p.setPlayerListHeaderFooter(header, footer);
    }

    public void sendMessage(String message) {
        p.sendMessage(message);
    }

    public void sendStaffMessage(String message) {
        for (Player pl : p.getServer().getOnlinePlayers()) {
            User target = new User(pl);
            if (Perm.hasPerm(target, PermGroup.TRIAL_HELPER)) {
                ChatColor c = getChatColor();
                if (c == null) c = WHITE;
                target.sendMessage(DARK_AQUA + "Staff-Chat: " + getDisplayName() + GOLD + " >> " + c + ITALIC + message);
            }
        }
    }

    public void sendTitle(@Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        p.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    public void setCanPickupItems(boolean canpickup) {
        p.setCanPickupItems(canpickup);
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

    public void setFly(boolean fly) {
        if (fly) {
            setAllowFlight(true);
            setFlySpeed(0.2F);
        } else {
            setAllowFlight(false);
            setFlying(false);
        }
    }

    public void setHat(ItemStack hat) {
        if ((p.getInventory().getHelmet() != null)) {
            addItem(p.getInventory().getHelmet());
            p.getInventory().setHelmet(null);
        }
        p.getInventory().setHelmet(hat);
    }

    public void setHealthScaled(boolean scaled) {
        p.setHealthScaled(scaled);
    }

    public void setItemInHand(ItemStack item) {
        p.getInventory().setItemInMainHand(item);
    }

    public void setMOTD(String motd) {
        Main.motd = motd;
        Main.c.getConfig().set("settings.motd", motd);
        Main.c.saveConfig();
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

    public void setScoreboard(Scoreboard board) {
        p.setScoreboard(board);
    }

    public void setSocialSpyActive(boolean active) {
        if (active) {
            Main.socialspiers.add(getName());
        } else {
            Main.socialspiers.remove(getName());
        }
    }

    public void setStaffChat(boolean staffChat) {
        if (staffChat) {
            Main.staffchat.add(getName());
        } else {
            Main.staffchat.remove(getName());
        }
    }

    public void setTpDisabled(boolean dis) {
        if (dis) {
            Main.tptoggle.remove(p.getName());
        } else {
            Main.tptoggle.add(p.getName());
        }
    }

    public void setUserTime(long time, boolean set) {
        p.setPlayerTime(time, set);
    }

    public void showMOTD() {
        sendMessage(ChatColor.translateAlternateColorCodes('&', Main.motd));
    }

    public void showUser(User u) {
        p.showPlayer(Main.p, u.getUser());
    }

    public void setWeather(WeatherTypes type) {
        Random r = new Random();
        int rand = r.nextInt(601);
        if (rand < 30) rand = 30;
        switch (type) {
            case CLEAR:
                for (World w : Bukkit.getWorlds()) {
                    w.setStorm(false);
                    w.setThundering(false);
                    w.setWeatherDuration(rand * 20);
                }
                break;
            case STORM:
                for (World w : Bukkit.getWorlds()) {
                    w.setStorm(true);
                    w.setThundering(false);
                    w.setWeatherDuration(rand * 20);
                }
                break;
            case THUNDER:
                for (World w : Bukkit.getWorlds()) {
                    w.setStorm(true);
                    w.setThundering(true);
                    w.setWeatherDuration(rand * 20);
                }
                break;
        }
    }

    public Spigot spigot() {
        return p.spigot();
    }

    public void teleport(Entity t) {
        p.teleport(t);
    }

    public void teleport(Location l) {
        setBack(getLocation());
        p.teleport(l);
    }

    /**
     * Teleports the player to his back location
     */
    public void toBack() {
        if (Main.back.containsKey(getName())) {
            teleport(Main.back.get(getName()));
        }
    }

    /**
     * Teleports the player to the spawn.
     */
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
            w = Bukkit.getServer().getWorld(Objects.requireNonNull(Main.c.getSpawn().getString("spawn.world")));
            Location l = new Location(w, x, y, z);
            l.setYaw(yaw);
            l.setPitch(pitch);
            teleport(l);
        } else {
            teleport(p.getWorld().getSpawnLocation());
        }
    }

    public void updateInventory() {
        p.updateInventory();
    }

    public void unmute() {
        try {
            Statement s = Main.sql.getConnection().createStatement();
            s.executeUpdate("DELETE FROM Muted WHERE player='" + getName() + "';");
            s.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Core >> SQL Error: " + e);
        }
    }

    public void untarget() {
        for (Entity ents : p.getWorld().getLivingEntities()) {
            if (ents instanceof Mob) {
                Mob b = (Mob) ents;
                if (b.getTarget() == p) {
                    b.setTarget(null);
                }
            }
        }
    }

    /**
     * Warn a player. The warn is put in the database.
     *
     * @param reason The reason why the player is warned.
     * @param warner The name of the person who warned the player.
     */
    public void warn(String reason, @Nullable String warner) {
        WarnUtils.warn(getName(), reason, warner);
    }

    /**
     * Teleports the player to certain warp.
     *
     * @param warp The warp the player will be teleported to.
     */
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
                w = Bukkit.getServer().getWorld(Objects.requireNonNull(Main.c.getWarps().getString("settings.warps." + warp + ".world")));
                Location l = new Location(w, x, y, z);
                l.setYaw(yaw);
                l.setPitch(pitch);
                teleport(l);
                p.sendMessage(Warp.PREFIX + "You have been teleported to warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + "!");
            } else {
                p.sendMessage(Warp.PREFIX + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " hasn't been set correctly!");
            }
        } else {
            p.sendMessage(Warp.PREFIX + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " does not exist!");
        }
    }

    /**
     * Teleport the player to a random location from where they are standing.
     */
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
                teleport(p.getWorld().getSpawnLocation());
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
        teleport(new Location(p.getWorld(), tpLoc.getX(), tpLoc.getY() + 1, tpLoc.getZ()));
        p.sendMessage(Wild.PREFIX + "You have been teleported " + ChatColor.GOLD + (int) tpLoc.distance(ogLoc) + ChatColor.YELLOW + " blocks away!");
    }
}
