package me.shizleshizle.core.commands.vaults.utils;

import me.shizleshizle.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.io.File;
import java.util.HashMap;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

public class VaultHandler {
    public static HashMap<String, HashMap<Integer, Inventory>> vaults = new HashMap<>();

    public static void saveVaultToFile(String name, Inventory inv, int vaultNumber) {
        String path = "vault." + vaultNumber + ".";
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

    @Nullable
    public static Inventory getInventory(String name, int vaultNumber) {
        return vaults.get(name).get(vaultNumber);
    }

    public static int getAmountOfVaults(String name) {
        return vaults.get(name).keySet().size();
    }

    public static boolean hasVault(String name, int vaultNumber) {
        return vaults.get(name).containsKey(vaultNumber);
    }

    public static String getVaultNumbers(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i : vaults.get(name).keySet()) {
            sb.append(GOLD).append(i).append(YELLOW).append(", ");
        }
        return sb.length() > 2 ? sb.substring(0, sb.length() - 2) : "";
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
        if (vaultFiles != null) {
            LoopThroughFiles(vaultFiles);
        }
    }

    private static void LoopThroughFiles(File[] vaultFiles) {
        final int ExtensionLetterAmount = 4;
        for (File vault : vaultFiles) {
            FileConfiguration conf = YamlConfiguration.loadConfiguration(vault);
            String name = vault.getName().substring(0, vault.getName().length() - ExtensionLetterAmount);
            String path = "vault.";
            for (int i = 1; i < 99; i++) {
                ConfigurationSection individualVault = conf.getConfigurationSection("vault." + i);
                if (individualVault != null) {
                    int size = individualVault.getInt(path + i + ".size");
                    Inventory inv = Bukkit.createInventory(null, size, GOLD + name + YELLOW + "'s Vault " + i);
                    for (int k = 0; k < size; k++) {
                        ConfigurationSection itemInConfig = individualVault.getConfigurationSection("." + k);
                        if (itemInConfig != null) {
                            ItemStack item = ItemStack.deserialize(itemInConfig.getValues(false));
                            inv.setItem(k, item);
                        }
                    }
                    addVault(name, inv, i);
                }
            }
        }
    }

    public static void addVault(String name, Inventory inv, int vaultNumber) {
        HashMap<Integer, Inventory> vaultsToStore = vaults.containsKey(name) ? vaults.get(name) : new HashMap<>();
        vaultsToStore.put(vaultNumber, inv);
        vaults.put(name, vaultsToStore);
        saveVaults();
    }
}
