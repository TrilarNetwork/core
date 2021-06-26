package me.shizleshizle.core.commands.vaults.utils;

import me.shizleshizle.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class VaultFileHandler {

    public static boolean hasFile(String name) {
        File f = new File(Main.vaultDir, name + ".yml");
        return f.exists();
    }

    public static boolean createFile(String name) {
        boolean createdFile = false;
        File f = new File(Main.vaultDir, name + ".yml");
        try {
            boolean created = f.createNewFile();
            createdFile = created;
            if (!created) {
                Bukkit.getLogger().info("Core >> Files: Did not create file for player, does it already exist?");
            }
        } catch (IOException e) {
            Bukkit.getLogger().info("Core >> Files: " + e);
        }
        return createdFile;
    }

    public static boolean deleteFile(String name) {
        File f = new File(Main.vaultDir, name + ".yml");
        boolean delete = f.delete();
        if (!delete) {
            Bukkit.getLogger().info("Core >> Files: Could not delete player file!");
        }
        return delete;
    }

    public static void saveFile(String name) {
        File f = new File(Main.vaultDir, name + ".yml");
        FileConfiguration conf = YamlConfiguration.loadConfiguration(f);
        try {
            conf.save(f);
        } catch (IOException e) {
            Bukkit.getLogger().info("Core >> Files: Could not save player file!");
        }
    }

    public static File getFile(String name) {
        return new File(Main.vaultDir, name + ".yml");
    }
}
