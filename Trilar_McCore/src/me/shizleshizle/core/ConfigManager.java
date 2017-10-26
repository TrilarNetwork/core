package me.shizleshizle.core;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
	boolean isSetup = false;
	private static ConfigManager instance = new ConfigManager();
	private FileConfiguration config;
	private File cfile;
	private FileConfiguration homes;
	private File chomes;
	private FileConfiguration nicks;
	private File cnicks;
	private FileConfiguration spawn;
	private File cspawn;
	private FileConfiguration warps;
	private File cwarps;
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	public void setup(Plugin p) {
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		this.cfile = new File(p.getDataFolder(), "config.yml");
		if (!this.cfile.exists()) {
			try {
				this.cfile.createNewFile();
				config = YamlConfiguration.loadConfiguration(cfile);
			} catch (IOException e) {
				Bukkit.getServer().getLogger().info("McCore >> Error: Could not create config.yml!");
			}
		} else {
			config = p.getConfig();
			config.options().copyDefaults(true);
			reloadConfig();
			saveConfig();
		}
		this.chomes = new File(p.getDataFolder(), "homes.yml");
		if (!this.chomes.exists()) {
			try {
				this.chomes.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().info("McCore >> Error: Could not create homes.yml!");
			}
		}
		homes = YamlConfiguration.loadConfiguration(chomes);
		this.cnicks = new File(p.getDataFolder(), "nicks.yml");
		if (!this.cnicks.exists()) {
			try {
				this.cnicks.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().info("McCore >> Error: Could not create nicknames.yml!");
			}
		}
		nicks = YamlConfiguration.loadConfiguration(cnicks);
		this.cspawn = new File(p.getDataFolder(), "spawn.yml");
		if (!this.cspawn.exists()) {
			try {
				this.cspawn.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().info("McCore >> Error: Could not create spawn.yml!");
			}
		}
		spawn = YamlConfiguration.loadConfiguration(cspawn);
		this.cwarps = new File(p.getDataFolder(), "warps.yml");
		if (!cwarps.exists()) {
			try {
				cwarps.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().info("McCore >> Error: Could not create warps.yml!");
			}
		}
		warps = YamlConfiguration.loadConfiguration(cwarps);
		reloadAll();
		setupConfig();
		saveAll();
		isSetup = true;
	}
	
	public void setupConfig() {
		if (!this.config.contains("settings.maxHealth")) {
			this.config.set("settings.maxHealth", 60);
		}
		if (!this.config.contains("settings.removeOnQuit")) {
			this.config.set("settings.removeOnQuit", false);
		}
		if (!this.config.contains("settings.maxhomes")) {
			this.config.set("settings.maxhomes", 1);
		}
		if (!this.config.contains("settings.teleportWaitTime")) {
			this.config.set("settings.teleportWaitTime", 5);
		}
		if (!this.config.contains("settings.database.hostname")) {
			this.config.set("settings.database.hostname", "185.211.51.30");
		}
		if (!this.config.contains("settings.database.port")) {
			this.config.set("settings.database.port", 3306);
		}
		if (!this.config.contains("settings.database.database")) {
			this.config.set("settings.database.database", "core");
		}
		if (!this.config.contains("settings.database.username")) {
			this.config.set("settings.database.username", "root");
		}
		if (!this.config.contains("settings.database.password")) {
			this.config.set("settings.database.password", "Pedo1234");
		}
	}
	
	public void saveAll() {
		saveConfig();
		saveHomes();
		saveNicks();
		saveSpawn();
		saveWarps();
	}
	
	public void reloadAll() {
		reloadConfig();
		reloadHomes();
		reloadNicks();
		reloadSpawn();
		reloadWarps();
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}
	
	public void saveConfig() {
		try {
			this.config.save(this.cfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().info("McCore >> Error: Could not save config.yml!");
		}
	}
	
	public void reloadConfig() {
		this.config = YamlConfiguration.loadConfiguration(cfile);
		Main.setupUtils();
	}
	
	public FileConfiguration getHomes() {
		return this.homes;
	}
	
	public void saveHomes() {
		try {
			this.homes.save(chomes);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().info("McCore >> Error: Could not save homes.yml!");
		}
	}
	
	public void reloadHomes() {
		this.homes = YamlConfiguration.loadConfiguration(chomes);
	}
	
	public FileConfiguration getNicks() {
		return this.nicks;
	}
	
	public void saveNicks() {
		try {
			this.nicks.save(cnicks);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().info("McCore >> Error: Could not save nicknames.yml!");
		}
	}
	
	public void reloadNicks() {
		this.nicks = YamlConfiguration.loadConfiguration(cnicks);
	}
	
	public FileConfiguration getSpawn() {
		return spawn;
	}
	
	public void saveSpawn() {
		try {
			spawn.save(cspawn);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().info("McCore >> Error: Could not save spawn.yml!");
		}
	}
	
	public void reloadSpawn() {
		spawn = YamlConfiguration.loadConfiguration(cspawn);
	}
	
	public FileConfiguration getWarps() {
		return warps;
	}
	
	public void saveWarps() {
		try {
			warps.save(cwarps);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().info("McCore >> Error: Could not save warps.yml!");
		}
	}
	
	public void reloadWarps() {
		warps = YamlConfiguration.loadConfiguration(cwarps);
	}
}