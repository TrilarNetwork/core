package me.shizleshizle.core.commands.clearlag;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class Unloadchunks implements CommandExecutor {
    public static final String PREFIX = YELLOW.toString() + BOLD + "Clear-Lag" + GOLD + " >> " + YELLOW;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("unloadchunks")) {
            if (sender instanceof Player) {
                if (!Perm.hasPerm(sender.getName(), PermGroup.ADMIN)) {
                    ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/unloadchunks");
                    return false;
                }
            }
            if (args.length == 0) {
                int counter = 0;
                for (World w : Bukkit.getWorlds()) {
                    Chunk[] chunksInWorld = w.getLoadedChunks();
                    for (Chunk chunk : chunksInWorld) {
                        Entity[] ent = chunk.getEntities();
                        boolean skip = false;
                        for (Entity entity : ent) {
                            if (entity instanceof Player) {
                                skip = true;
                                break;
                            }
                        }
                        if (!skip) {
                            chunk.unload();
                            counter++;
                        }
                    }
                }
                sender.sendMessage(PREFIX + "Unloaded " + GOLD + counter + YELLOW + " chunks!");
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/unloadchunks");
            }
        }
        return false;
    }
}
