package me.shizleshizle.core.commands.messaging;

import me.shizleshizle.core.objects.Messages;
import me.shizleshizle.core.objects.User;
import me.shizleshizle.core.utils.ErrorMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reply implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("reply")) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (p.hasLastMessager()) {
                    if (args.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (String word : args) {
                            sb.append(word).append(" ");
                        }
                        p.reply(ChatColor.translateAlternateColorCodes('&', sb.toString().trim()));
                    } else {
                        ErrorMessages.doErrorMessage(p, Messages.INVALID_USAGE, "/reply <message>");
                    }
                } else {
                    p.sendMessage(Msg.PREFIX + "You have no one to reply to!");
                }
            } else {
                sender.sendMessage(Msg.PREFIX + "You have no one to reply to!");
            }
        }
        return false;
    }
}
