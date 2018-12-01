package me.shizleshizle.core;

import me.shizleshizle.core.commands.*;
import me.shizleshizle.core.commands.homes.Delhome;
import me.shizleshizle.core.commands.homes.Home;
import me.shizleshizle.core.commands.homes.Sethome;
import me.shizleshizle.core.commands.spawn.Spawn;
import me.shizleshizle.core.commands.teleportation.*;
import me.shizleshizle.core.commands.tickets.*;
import me.shizleshizle.core.commands.time.DayCmd;
import me.shizleshizle.core.commands.time.NightCmd;
import me.shizleshizle.core.commands.time.PTime;
import me.shizleshizle.core.commands.time.Time;
import me.shizleshizle.core.commands.warps.Deletewarps;
import me.shizleshizle.core.commands.warps.Setwarps;
import me.shizleshizle.core.commands.warps.Warp;
import me.shizleshizle.core.listeners.*;
import me.shizleshizle.core.mysql.MySQLManager;
import me.shizleshizle.core.objects.ChatColorHandler;
import me.shizleshizle.core.permissions.PermUser;
import me.shizleshizle.core.permissions.PermissionGroup;
import me.shizleshizle.core.permissions.Prefix;
import me.shizleshizle.core.utils.AutoB;
import me.shizleshizle.core.utils.Cooldowns;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
	public static List<String> msgs;
	public static ArrayList<String> afks = new ArrayList<>();
	public static ArrayList<String> frozen = new ArrayList<>();
	public static ArrayList<String> gods = new ArrayList<>();
	public static ArrayList<String> tptoggle = new ArrayList<>();
	public static ArrayList<String> vanished = new ArrayList<>();
	public static HashMap<String, Location> back = new HashMap<>();
	public static HashMap<String, String> tpahere = new HashMap<>();
	public static ConfigManager c;
	public static Economy econ = null;
	public static MySQLManager sql;
	public static Plugin p;
	public static boolean lockdown = false;
	private static boolean lobby = false;
	public static boolean remove;
	public static String host;
	public static String db;
	public static String user;
	public static String pw;
	public static String prefix = ChatColor.GOLD + "-={ " + ChatColor.YELLOW + "Trilar" + ChatColor.GOLD + " }=- "
			+ ChatColor.YELLOW;
	public static int port;
	public static int maxhomes;
	public static int tpTime;
	public static int maxHealth;
	public static int abdelay;
	private int i = 0;

	public void onEnable() {
		long time = System.currentTimeMillis();
		Logger l = getLogger();
		l.info("McCore >> enabling...");
		p = this;
		c = ConfigManager.getInstance();
		c.setup(this);
		setupUtils();
		loadVault();
		sql = MySQLManager.getInstance();
		sql.setup();
		PermissionGroup.setup();
		PermUser.setup();
		Prefix.setup();
		ChatColorHandler.setup();
		register();
		registerEvents();
		Cooldowns.runCooldown();
		AutoB.enable();
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
		long fin = System.currentTimeMillis() - time;
		l.info("McCore >> successfully disabled! (" + fin + " ms)");
	}

	private void register() {
		// commands
		// home
		getCommand("delhome").setExecutor(new Delhome());
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new Sethome());

		// spawn
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("setspawn").setExecutor(new Spawn());
		getCommand("removespawn").setExecutor(new Spawn());

		// teleportation
		getCommand("tp").setExecutor(new Tp());
		getCommand("tpa").setExecutor(new Tpa());
		getCommand("tpaccept").setExecutor(new Tpaccept());
		getCommand("tpahere").setExecutor(new Tpahere());
		getCommand("tpall").setExecutor(new Tpall());
		getCommand("tpdeny").setExecutor(new TpDeny());
		getCommand("tphere").setExecutor(new Tphere());
		getCommand("tpo").setExecutor(new Tpo());
		getCommand("tpohere").setExecutor(new Tpohere());
		getCommand("tppos").setExecutor(new Tppos());
		getCommand("tptoggle").setExecutor(new Tptoggle());

		// tickets
		getCommand("checkticket").setExecutor(new CheckTicket());
		getCommand("closeticket").setExecutor(new CloseTicket());
		getCommand("taketicket").setExecutor(new TakeTicket());
		getCommand("ticket").setExecutor(new Ticket());
		getCommand("tickets").setExecutor(new Tickets());

		// time
		getCommand("time").setExecutor(new Time());
		getCommand("day").setExecutor(new DayCmd());
		getCommand("night").setExecutor(new NightCmd());
		getCommand("ptime").setExecutor(new PTime());

		// warps
		getCommand("removewarp").setExecutor(new Deletewarps());
		getCommand("setwarp").setExecutor(new Setwarps());
		getCommand("warp").setExecutor(new Warp());

		// weather
		getCommand("weather").setExecutor(new Weather());
		getCommand("sun").setExecutor(new Weather());
		getCommand("storm").setExecutor(new Weather());

		// regular
		getCommand("autobroadcaster").setExecutor(new AB());
		getCommand("afk").setExecutor(new Afk());
		getCommand("back").setExecutor(new Back());
		getCommand("broadcast").setExecutor(new Broadcast());
		getCommand("chatcolor").setExecutor(new ChatColorCmd());
		getCommand("clearchat").setExecutor(new ClearChat());
		getCommand("clearinventory").setExecutor(new ClearInventory());
		getCommand("commandspy").setExecutor(new CommandSpy());
		getCommand("enchant").setExecutor(new Enchant());
		getCommand("enderchest").setExecutor(new Enderchest());
		getCommand("feed").setExecutor(new Feed());
		getCommand("fly").setExecutor(new Fly());
		getCommand("freeze").setExecutor(new Freeze());
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("gms").setExecutor(new Gamemode());
		getCommand("gmc").setExecutor(new Gamemode());
		getCommand("gma").setExecutor(new Gamemode());
		getCommand("gmsp").setExecutor(new Gamemode());
		getCommand("getpos").setExecutor(new Getpos());
		getCommand("god").setExecutor(new God());
		getCommand("hat").setExecutor(new Hat());
		getCommand("heal").setExecutor(new Heal());
		getCommand("help").setExecutor(new Help());
		getCommand("Invsee").setExecutor(new Invsee());
		getCommand("kill").setExecutor(new Kill());
		getCommand("list").setExecutor(new ListCmd());
		getCommand("lockdown").setExecutor(new Lockdown());
		getCommand("mccore").setExecutor(new Reload());
		getCommand("me").setExecutor(new Me());
		getCommand("mods").setExecutor(new Mods());
		getCommand("nickname").setExecutor(new Nickname());
		getCommand("permission").setExecutor(new PermissionsCmd());
		getCommand("prefix").setExecutor(new PrefixCmd());
		getCommand("rank").setExecutor(new Ranks());
		getCommand("repair").setExecutor(new Repair());
		getCommand("rules").setExecutor(new Rules());
		getCommand("socialmedia").setExecutor(new SocialMedia());
		getCommand("skull").setExecutor(new Skulls());
		getCommand("speed").setExecutor(new Speed());
		getCommand("staff").setExecutor(new StaffList());
		getCommand("suicide").setExecutor(new Suicide());
		getCommand("vanish").setExecutor(new Vanish());
		getCommand("whois").setExecutor(new WhoIs());
		getCommand("wild").setExecutor(new Wild());
		getCommand("workbench").setExecutor(new Workbench());
		if (checkVault()) {
			getCommand("balance").setExecutor(new Balance());
			getCommand("pay").setExecutor(new Pay());

		}
	}

	private void registerEvents() {
		// events
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(), this);
		pm.registerEvents(new EntityDamage(), this);
		pm.registerEvents(new EntityTarget(), this);
		pm.registerEvents(new FoodChange(), this);
		pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new PlayerInteract(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerPD(), this);
		pm.registerEvents(new PlayerPreProcess(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new PlayerTeleport(), this);
	}

	public static void setupUtils() {
		lobby = c.getConfig().getBoolean("lobbyVersion");
		remove = c.getConfig().getBoolean("settings.removeOnQuit");
		host = c.getConfig().getString("settings.database.hostname");
		port = c.getConfig().getInt("settings.database.port");
		db = c.getConfig().getString("settings.database.database");
		user = c.getConfig().getString("settings.database.username");
		pw = c.getConfig().getString("settings.database.password");
		maxhomes = c.getConfig().getInt("settings.maxhomes");
		tpTime = c.getConfig().getInt("settings.teleportWaitTime");
		maxHealth = c.getConfig().getInt("settings.maxHealth");
		abdelay = c.getConfig().getInt("settings.autoBroadcastDelay");
		msgs = Main.c.getConfig().getStringList("settings.broadcastMessages");
	}

	private void broadcast() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
			if (AutoB.isBroadcasting()) {
				Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.YELLOW + "Trilar" + ChatColor.GOLD + "]");
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
		}, 0L, abdelay * 20);
	}

	private boolean checkVault() {
		//Plugin p = getServer().getPluginManager().getPlugin("Vault");
		//return (p instanceof Vault);
		return false;
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
		/*if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		} */
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
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