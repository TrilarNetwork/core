package me.shizleshizle.core.commands;

import me.shizleshizle.core.Main;
import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import me.shizleshizle.core.utils.Numbers;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class Enchant implements CommandExecutor {
    private final String PREFIX = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Enchant" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("enchant")) {
            if (!Main.isLobby()) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(PREFIX + "You must be a player to perform this command!");
                } else {
                    Player x = (Player) sender;
                    User p = new User(x);
                    if (Perm.hasPerm(p, PermGroup.HELPER)) {
                        if (args.length != 2) {
                            ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/enchant <enchantment> <level>");
                        } else {
                            int lvl = 0;
                            if (Numbers.isNumber(args[1])) {
                                lvl = Numbers.getInt(args[1]);
                            } else {
                                p.sendMessage(PREFIX + "Invalid level! Please enter a number!");
                            }
                            if (args[0].equalsIgnoreCase("sharpness")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, lvl);
                            } else if (args[0].equalsIgnoreCase("knockback")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.KNOCKBACK, lvl);
                            } else if (args[0].equalsIgnoreCase("protection")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, lvl);
                            } else if (args[0].equalsIgnoreCase("fireprotection")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, lvl);
                            } else if (args[0].equalsIgnoreCase("featherfalling")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, lvl);
                            } else if (args[0].equalsIgnoreCase("blastprotection")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, lvl);
                            } else if (args[0].equalsIgnoreCase("projectileprotection")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, lvl);
                            } else if (args[0].equalsIgnoreCase("respiration")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.OXYGEN, lvl);
                            } else if (args[0].equalsIgnoreCase("aquaaffinity")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.WATER_WORKER, lvl);
                            } else if (args[0].equalsIgnoreCase("thorns")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.THORNS, lvl);
                            } else if (args[0].equalsIgnoreCase("depthstrider")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, lvl);
                            } else if (args[0].equalsIgnoreCase("smite")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, lvl);
                            } else if (args[0].equalsIgnoreCase("baneofarthropods")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, lvl);
                            } else if (args[0].equalsIgnoreCase("fireaspect")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, lvl);
                            } else if (args[0].equalsIgnoreCase("looting")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, lvl);
                            } else if (args[0].equalsIgnoreCase("power")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, lvl);
                            } else if (args[0].equalsIgnoreCase("punch")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, lvl);
                            } else if (args[0].equalsIgnoreCase("flame")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.ARROW_FIRE, lvl);
                            } else if (args[0].equalsIgnoreCase("infinity")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, lvl);
                            } else if (args[0].equalsIgnoreCase("efficiency")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.DIG_SPEED, lvl);
                            } else if (args[0].equalsIgnoreCase("silktouch")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.SILK_TOUCH, lvl);
                            } else if (args[0].equalsIgnoreCase("unbreaking")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.DURABILITY, lvl);
                            } else if (args[0].equalsIgnoreCase("fortune")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, lvl);
                            } else if (args[0].equalsIgnoreCase("lockofthesea")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.LUCK, lvl);
                            } else if (args[0].equalsIgnoreCase("lure")) {
                                p.getItemInMainHand().addUnsafeEnchantment(Enchantment.LURE, lvl);
                            } else {
                                p.sendMessage(PREFIX + "Invalid enchantment! Please enter a valid enchantment.");
                            }
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.NOPERM, "/enchant");
                    }
                }
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.LOBBY, "enchant");
            }
        }
        return false;
    }
}