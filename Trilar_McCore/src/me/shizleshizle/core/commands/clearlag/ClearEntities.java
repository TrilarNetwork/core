package me.shizleshizle.core.commands.clearlag;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.permissions.Perm;
import me.shizleshizle.core.permissions.PermGroup;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.YELLOW;

public class ClearEntities implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("clearentities")) {
            if (sender instanceof Player) {
                if (!Perm.hasPerm(sender.getName(), PermGroup.ADMIN)) {
                    ErrorMessages.doErrorMessage(sender, Messages.NOPERM, "/clearentities");
                }
            }
            if (args.length == 0) {
                int counter = 0;
                for (World w : Bukkit.getWorlds()) {
                    List<Entity> entities = w.getEntities();
                    for (Entity e : entities) {
                        if (e instanceof LivingEntity) continue;
                        if (e instanceof ItemFrame) continue;
                        e.remove();
                        counter++;
                    }
                }
                sender.sendMessage(Chunks.PREFIX + "You have removed " + GOLD + counter + YELLOW + " entities!");
            } else {
                ErrorMessages.doErrorMessage(sender, Messages.INVALID_USAGE, "/clearentities");
            }
        }
        return false;
    }
}
