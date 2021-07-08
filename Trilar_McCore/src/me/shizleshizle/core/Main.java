package me.shizleshizle.core;

import me.shizleshizle.core.commands.bansystem.GUIFunction;
import me.shizleshizle.core.commands.cmdutils.HomeUtils;
import me.shizleshizle.core.commands.vaults.utils.VaultHandler;
import me.shizleshizle.core.mysql.MySQLManager;
import me.shizleshizle.core.permissions.PermUser;
import me.shizleshizle.core.permissions.PermissionGroup;
import me.shizleshizle.core.permissions.PrefixHelper;
import me.shizleshizle.core.utils.AutoB;
import me.shizleshizle.core.utils.CommandManager;
import me.shizleshizle.core.utils.Cooldowns;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static List<String> msgs;
    public static List<String> disabledCommands = new ArrayList<>();
    public static ArrayList<String> afks = new ArrayList<>();
    public static ArrayList<String> frozen = new ArrayList<>();
    public static ArrayList<String> gods = new ArrayList<>();
    public static ArrayList<String> tptoggle = new ArrayList<>();
    public static ArrayList<String> vanished = new ArrayList<>();
    public static ArrayList<String> socialspiers = new ArrayList<>();
    public static ArrayList<String> setHome = new ArrayList<>();
    public static ArrayList<String> staffchat = new ArrayList<>();
    public static ArrayList<String> giveReasonForPunishment = new ArrayList<>();
    public static HashMap<String, Location> back = new HashMap<>();
    public static HashMap<String, String> tpahere = new HashMap<>();
    public static HashMap<String, String> messaging = new HashMap<>();
    public static HashMap<String, GUIFunction> staffgui = new HashMap<>();
    public static HashMap<String, String> staffguiReason = new HashMap<>();
    public static ConfigManager c;
    public static Economy econ = null;
    public static MySQLManager sql;
    public static CommandManager cman;
    public static Plugin p;
    public static File vaultDir;
    public static boolean maintenance = false;
    public static boolean remove;
    private static boolean lobby = false;
    public static boolean broadcastFunction;
    public static String host;
    public static String db;
    public static String user;
    public static String pw;
    public static String motd;
    public static final String PREFIX = ChatColor.GOLD + "-={ " + ChatColor.YELLOW + "Eliarant" + ChatColor.GOLD + " }=- "
            + ChatColor.YELLOW;
    public static int port;
    public static int maxhomes;
    public static int tpTime;
    public static int maxHealth;
    public static int autobroadcastDelay;
    private int i = 0;

    public void onEnable() {
        long time = System.currentTimeMillis();
        Logger l = getLogger();
        l.info("McCore >> enabling...");
        p = this;
        c = ConfigManager.getInstance();
        c.setup(this);
        setupUtils();
        vaultDir = new File(getDataFolder(), "playervaults");
        if (!vaultDir.exists() || !vaultDir.isDirectory()) {
            if (vaultDir.mkdir()) l.info("McCore >> created player vaults directory!");
        }
        VaultHandler.loadFromFile();
        //loadVault();
        sql = MySQLManager.getInstance();
        sql.setup();
        PermissionGroup.setup();
        PermUser.setup();
        PrefixHelper.setup();
        cman = new CommandManager(this);
        cman.registerToServer();
        HomeUtils.loadHomes();
        Cooldowns.runCooldown();
        AutoB.setBroadcasting(broadcastFunction);
        broadcast();
        long fin = System.currentTimeMillis() - time;
        l.info("McCore >> successfully enabled! (" + fin + " ms)");
    }

    public void onDisable() {
        long time = System.currentTimeMillis();
        Logger l = getLogger();
        l.info("McCore >> disabling...");
        try {
            if (sql.checkConnection()) {
                sql.closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        saveUtils();
        long fin = System.currentTimeMillis() - time;
        l.info("McCore >> successfully disabled! (" + fin + " ms)");
    }

    public void saveUtils() {
        HomeUtils.saveHomeNames();
        AutoB.saveToConfig();
        VaultHandler.saveVaults();
        c.getConfig().set("settings.disabledCommands", disabledCommands);
        c.saveConfig();
    }

    public static void setupUtils() {
        lobby = c.getConfig().getBoolean("lobbyVersion");
        remove = c.getConfig().getBoolean("settings.removeOnQuit");
        host = c.getConfig().getString("settings.database.hostname");
        port = c.getConfig().getInt("settings.database.port");
        db = c.getConfig().getString("settings.database.database");
        user = c.getConfig().getString("settings.database.username");
        pw = c.getConfig().getString("settings.database.password");
        motd = c.getConfig().getString("settings.motd");
        maxhomes = c.getConfig().getInt("settings.maxhomes");
        tpTime = c.getConfig().getInt("settings.teleportWaitTime");
        maxHealth = c.getConfig().getInt("settings.maxHealth");
        autobroadcastDelay = c.getConfig().getInt("settings.autoBroadcastDelay");
        broadcastFunction = c.getConfig().getBoolean("settings.enableAutoBroadcast");
        msgs = c.getConfig().getStringList("settings.broadcastMessages");
        disabledCommands = c.getConfig().getStringList("settings.disabledCommands");
    }

    private void broadcast() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            if (AutoB.isBroadcasting()) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "Eliarant" + ChatColor.GOLD + "]");
                // Bukkit.broadcastMessage(ChatColor.GOLD +
                // "<=====================>");
                // Bukkit.broadcastMessage(" ");
                // Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                // msgs.get(i)).trim());
                // Bukkit.broadcastMessage(" ");
                // Bukkit.broadcastMessage(ChatColor.GOLD +
                // ">=====================<");
                i++;
                if (i > (msgs.size() - 1)) {
                    i = 0;
                }
            }
        }, 0L, autobroadcastDelay * 20L);
    }

    public boolean checkVault() {
        Plugin p = getServer().getPluginManager().getPlugin("Vault");
        return (p instanceof Vault);
    }

    private void loadVault() {
        if (checkVault()) {
            if (!setupEconomy()) {
                Bukkit.getLogger().info("Core >> Economy has not been found!");
            }
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "Core >> Vault has not been found! Disabling Core...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public static boolean isLobby() {
        return lobby;
    }

	/*
	 implements CommandExecutor {
	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "  " + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase(" ")) {
			if (sender instanceof Player) {
				Player x = (Player) sender;
				User p = new User(x);
				if (Perm.hasPerm(p, PermGroup.MEMBER)) {
					if (args.length == 0) {
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			}
		}
		return false;
	}
}
	 */
}