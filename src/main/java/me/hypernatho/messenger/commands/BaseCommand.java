package me.hypernatho.messenger.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String tag, String[] args) {
        if (isPlayerOnly() && !(sender instanceof Player)) {
            tell(sender, "&4Only players can execute that command.");
            return true;
        }

        if (permission() != null && !sender.getName().equalsIgnoreCase("CONSOLE") && !sender.hasPermission(permission())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have permission to do that!"));
            return true;
        }

        execute(sender, tag, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String tag, String[] args);

    public abstract boolean isPlayerOnly();

    public abstract String permission();

    public static void tell(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
