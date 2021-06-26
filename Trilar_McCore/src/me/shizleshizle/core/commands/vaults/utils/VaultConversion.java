package me.shizleshizle.core.commands.vaults.utils;

import me.shizleshizle.core.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;

public class VaultConversion {
    public static HashMap<String, HashMap<Integer, Inventory>> vaults = new HashMap<>();

    public static void saveVaultToFile(String name, Inventory inv, int vaultNumber) {
        String path = "vault" + vaultNumber + ".";
        File f = VaultFileHandler.getFile(name);
        FileConfiguration config = YamlConfiguration.loadConfiguration(f);
        config.set(path + "size", inv.getSize());
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) {
                continue;
            }
            config.set(path + i, item.serialize());
        }
        VaultFileHandler.saveFile(name);
    }

    public static void saveVaults() {
        for (String name : vaults.keySet()) {
            HashMap<Integer, Inventory> vault = vaults.get(name);
            for (int vaultNumber : vault.keySet()) {
                saveVaultToFile(name, vault.get(vaultNumber), vaultNumber);
            }
        }
    }

    public static void loadFromFile() {
        File f = Main.vaultDir;
        File[] vaultFiles = f.listFiles();
        final int ExtensionLetterAmount = 4;
        if (vaultFiles != null) {
            for (File vault : vaultFiles) {
                FileConfiguration conf = YamlConfiguration.loadConfiguration(vault);
                String name = vault.getName().substring(0, vault.getName().length() - ExtensionLetterAmount);
                //int size = conf.getInt();
                // TODO: Continue to load vaults to hashmap
            }
        }
    }
}
