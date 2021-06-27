package me.shizleshizle.core.commands.vaults;

import me.shizleshizle.core.commands.vaults.utils.VaultFileHandler;
import me.shizleshizle.core.commands.vaults.utils.VaultHandler;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Vault implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Vaults" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("playervaults")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.VIP)) {
                    if (args.length == 1) {
                        if (Numbers.isNumber((args[0]))) {
                            int vaultNumber = Numbers.getInt(args[0]);
                            if (p.hasVault(vaultNumber)) {
                                p.openVault(vaultNumber);
                            } else {
                                p.createVault(vaultNumber);
                                p.openVault(vaultNumber);
                                p.sendMessage(PREFIX + "Created Vault #" + GOLD + vaultNumber + YELLOW + "!");
                            }
                        } else if (args[0].equalsIgnoreCase("list")) {
                            p.sendMessage(PREFIX + "These are your vaults: ");
                            p.sendMessage(VaultHandler.getVaultNumbers(p.getName()));
                        } else {
                            p.sendMessage(PREFIX + "You must enter a vault number!");
                        }
                    } else if (args.length == 2) {
                        if (Numbers.isNumber(args[0])) {
                            int vaultNumber = Numbers.getInt(args[0]);
                            if (Numbers.isNumber(args[1])) {
                                if (p.hasVault(vaultNumber)) {
                                    p.sendMessage(PREFIX + "You already have vault #" + GOLD + vaultNumber + YELLOW + "!");
                                } else {
                                    int size = Numbers.getInt(args[1]);
                                    p.createVault(vaultNumber, size);
                                    p.openVault(vaultNumber);
                                    p.sendMessage(PREFIX + "Created Vault #" + GOLD + vaultNumber + YELLOW + " with size " + GOLD + size + YELLOW + "!");
                                }
                            } else {
                                if (Perm.hasPerm(p, PermGroup.ADMIN)) {
                                    String targetName = args[1];
                                    if (VaultHandler.hasVault(targetName, vaultNumber)) {
                                        p.openOtherVault(targetName, vaultNumber);
                                        p.sendMessage(PREFIX + "Opening " + GOLD + targetName + YELLOW + "'s vault #" + GOLD + vaultNumber + YELLOW + "!");
                                    } else {
                                        p.sendMessage(PREFIX + "Player " + GOLD + targetName + YELLOW + " does not have vault #" + GOLD + vaultNumber + YELLOW + "!");
                                    }
                                } else {
                                    p.sendMessage(PREFIX + "You must enter a number!");
                                }
                            }
                        } else {
                            if (args[1].equalsIgnoreCase("list")) {
                                String name = args[0];
                                if (VaultFileHandler.hasFile(name)) {
                                    p.sendMessage(PREFIX + "Player " + GOLD + name + YELLOW + " has these vaults: ");
                                    p.sendMessage(VaultHandler.getVaultNumbers(name));
                                }
                            }
                            p.sendMessage(PREFIX + "You must enter a vault number!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/playervaults <vaultNumber> [size]");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/playervaults");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.PLAYER_ONLY_CMD, "/playervaults");
            }
        }
        return false;
    }
}
