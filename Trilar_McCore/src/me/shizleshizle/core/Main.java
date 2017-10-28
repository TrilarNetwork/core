package me.shizleshizle.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.shizleshizle.core.commands.AB;
import me.shizleshizle.core.commands.Afk;
import me.shizleshizle.core.commands.Back;
import me.shizleshizle.core.commands.Balance;
import me.shizleshizle.core.commands.Broadcast;
import me.shizleshizle.core.commands.ChatColorCmd;
import me.shizleshizle.core.commands.ClearChat;
import me.shizleshizle.core.commands.ClearInventory;
import me.shizleshizle.core.commands.CommandSpy;
import me.shizleshizle.core.commands.Enchant;
import me.shizleshizle.core.commands.Enderchest;
import me.shizleshizle.core.commands.Feed;
import me.shizleshizle.core.commands.Fly;
import me.shizleshizle.core.commands.Freeze;
import me.shizleshizle.core.commands.Gamemode;
import me.shizleshizle.core.commands.Getpos;
import me.shizleshizle.core.commands.God;
import me.shizleshizle.core.commands.Hat;
import me.shizleshizle.core.commands.Heal;
import me.shizleshizle.core.commands.Help;
import me.shizleshizle.core.commands.Invsee;
import me.shizleshizle.core.commands.Kill;
import me.shizleshizle.core.commands.ListCmd;
import me.shizleshizle.core.commands.Lockdown;
import me.shizleshizle.core.commands.Me;
import me.shizleshizle.core.commands.Nickname;
import me.shizleshizle.core.commands.Pay;
import me.shizleshizle.core.commands.PermissionsCmd;
import me.shizleshizle.core.commands.PrefixCmd;
import me.shizleshizle.core.commands.Ranks;
import me.shizleshizle.core.commands.Reload;
import me.shizleshizle.core.commands.Repair;
import me.shizleshizle.core.commands.Rules;
import me.shizleshizle.core.commands.Skulls;
import me.shizleshizle.core.commands.Speed;
import me.shizleshizle.core.commands.StaffList;
import me.shizleshizle.core.commands.Suicide;
import me.shizleshizle.core.commands.Vanish;
import me.shizleshizle.core.commands.Weather;
import me.shizleshizle.core.commands.WhoIs;
import me.shizleshizle.core.commands.Wild;
import me.shizleshizle.core.commands.Workbench;
import me.shizleshizle.core.commands.homes.Delhome;
import me.shizleshizle.core.commands.homes.Home;
import me.shizleshizle.core.commands.homes.Sethome;
import me.shizleshizle.core.commands.spawn.Spawn;
import me.shizleshizle.core.commands.teleportation.Tp;
import me.shizleshizle.core.commands.teleportation.TpDeny;
import me.shizleshizle.core.commands.teleportation.Tpa;
import me.shizleshizle.core.commands.teleportation.Tpaccept;
import me.shizleshizle.core.commands.teleportation.Tpahere;
import me.shizleshizle.core.commands.teleportation.Tphere;
import me.shizleshizle.core.commands.teleportation.Tpo;
import me.shizleshizle.core.commands.teleportation.Tpohere;
import me.shizleshizle.core.commands.teleportation.Tppos;
import me.shizleshizle.core.commands.teleportation.Tptoggle;
import me.shizleshizle.core.commands.time.DayCmd;
import me.shizleshizle.core.commands.time.NightCmd;
import me.shizleshizle.core.commands.time.PTime;
import me.shizleshizle.core.commands.time.Time;
import me.shizleshizle.core.commands.warps.Deletewarps;
import me.shizleshizle.core.commands.warps.Setwarps;
import me.shizleshizle.core.commands.warps.Warp;
import me.shizleshizle.core.listeners.BlockBreak;
import me.shizleshizle.core.listeners.BlockPlace;
import me.shizleshizle.core.listeners.EntityDamage;
import me.shizleshizle.core.listeners.EntityTarget;
import me.shizleshizle.core.listeners.FoodChange;
import me.shizleshizle.core.listeners.InventoryClick;
import me.shizleshizle.core.listeners.PlayerChat;
import me.shizleshizle.core.listeners.PlayerInteract;
import me.shizleshizle.core.listeners.PlayerJoin;
import me.shizleshizle.core.listeners.PlayerMove;
import me.shizleshizle.core.listeners.PlayerPD;
import me.shizleshizle.core.listeners.PlayerPreProcess;
import me.shizleshizle.core.listeners.PlayerQuit;
import me.shizleshizle.core.listeners.PlayerTeleport;
import me.shizleshizle.core.mysql.MySQLManager;
import me.shizleshizle.core.objects.ChatColorHandler;
import me.shizleshizle.core.permissions.PermUser;
import me.shizleshizle.core.permissions.PermissionGroup;
import me.shizleshizle.core.permissions.Prefix;
import me.shizleshizle.core.utils.AutoB;
import me.shizleshizle.core.utils.Cooldowns;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	public static ArrayList<String> afks = new ArrayList<String>();
	public static ArrayList<String> frozen = new ArrayList<String>();
	public static ArrayList<String> gods = new ArrayList<String>();
	public static ArrayList<String> tptoggle = new ArrayList<String>();
	public static ArrayList<String> vanished = new ArrayList<String>();
	public static HashMap<String, Location> back = new HashMap<String, Location>();
	public static HashMap<String, String> tpahere = new HashMap<>();
	public static ConfigManager c;
	public static Economy econ = null;
	public static MySQLManager sql;
	public static Plugin p;
	public static boolean lockdown = false;
	public static boolean remove;
	public static String host;
	public static String db;
	public static String user;
	public static String pw;
	public static String prefix = ChatColor.GOLD + "-={ " + ChatColor.YELLOW + "Trilar" + ChatColor.GOLD + " }=- " + ChatColor.YELLOW;
	public static int port;
	public static int maxhomes;
	public static int tpTime;
	public static int maxHealth;
	public static int abdelay;
	 
	public void onEnable(){
		long time = System.currentTimeMillis();
		Logger l = getLogger();
		l.info("Core >> enabling...");
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
		// commands
		//home
		getCommand("delhome").setExecutor(new Delhome());
		getCommand("home").setExecutor(new Home());
		getCommand("sethome").setExecutor(new Sethome());
		
		//spawn
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("setspawn").setExecutor(new Spawn());
		getCommand("removespawn").setExecutor(new Spawn());
		
		//teleportation
		getCommand("tp").setExecutor(new Tp());
		getCommand("tpa").setExecutor(new Tpa());
		getCommand("tpaccept").setExecutor(new Tpaccept());
		getCommand("tpahere").setExecutor(new Tpahere());
		getCommand("tpdeny").setExecutor(new TpDeny());
		getCommand("tphere").setExecutor(new Tphere());
		getCommand("tpo").setExecutor(new Tpo());
		getCommand("tpohere").setExecutor(new Tpohere());
		getCommand("tppos").setExecutor(new Tppos());
		getCommand("tptoggle").setExecutor(new Tptoggle());
		
		//time
		getCommand("time").setExecutor(new Time());
		getCommand("day").setExecutor(new DayCmd());
		getCommand("night").setExecutor(new NightCmd());
		getCommand("ptime").setExecutor(new PTime());
		
		//warps
		getCommand("removewarp").setExecutor(new Deletewarps());
		getCommand("setwarp").setExecutor(new Setwarps());
		getCommand("warp").setExecutor(new Warp());		
		
		//weather
		getCommand("weather").setExecutor(new Weather());
		getCommand("sun").setExecutor(new Weather());
		getCommand("storm").setExecutor(new Weather());
		
		//regular
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
		getCommand("me").setExecutor(new Me());
		getCommand("nickname").setExecutor(new Nickname());
		getCommand("permission").setExecutor(new PermissionsCmd());
		getCommand("prefix").setExecutor(new PrefixCmd());
		getCommand("rank").setExecutor(new Ranks());
		getCommand("repair").setExecutor(new Repair());
		getCommand("regels").setExecutor(new Rules());
		getCommand("reload").setExecutor(new Reload());
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
		Cooldowns.runCooldown();
		AutoB.broadcast();
		long fin = System.currentTimeMillis() - time;
		l.info("Core >> successfully enabled! (" + fin + " ms)");
	}

	public void onDisable(){
		long time = System.currentTimeMillis();
		Logger l = getLogger();
		l.info("Core >> disabling...");
		
		long fin = System.currentTimeMillis() - time;
		l.info("Core >> successfully disabled! (" + fin + " ms)");
	}
	
	public static void setupUtils() {
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
	}
	
	private boolean checkVault() {
		Plugin p = getServer().getPluginManager().getPlugin("Vault");
		return (p instanceof Vault);
	}

	private void loadVault() {
		if (checkVault()) {
			if (!setupEconomy()) {
				Bukkit.getLogger().info("Stomar's Core >> Economy has not been found!");
			}
		} else {
			Bukkit.getLogger().log(Level.SEVERE, "Stomar's Core >> Vault has not been found! Disabling Stomar's Core...");
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
		return econ != null;
	}
	
	
	/*implements CommandExecutor {
	 	public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (cmd.getName().equalsIgnoreCase("")) {
				if (sender instanceof Player) {
					Player x = (Player) sender;
					User p = new User(x);
					if (Perm.hasPerm(p, PermGroup.HELPER)) {
						if (args.length == 0) {
							
						} else if (args.length == 1) {
							
						}
					} else {
						ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You must be a player to perform this command!");
				}
			}
			return false;
		}
	}*/
}