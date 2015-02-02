package me.hypernatho.messenger.commands.player;

import me.hypernatho.messenger.Messenger;
import me.hypernatho.messenger.commands.BaseCommand;
import me.hypernatho.messenger.objects.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reply extends BaseCommand {

    @Override
    public void execute(CommandSender sender, String tag, String[] args) {
        Player player = (Player) sender;

        if (!Messenger.getRecent().containsKey(player.getName())) {
            tell(player, "&cYou have nobody to reply to!");
            return;
        }

        if (Bukkit.getPlayer(Messenger.getRecent().get(player.getName())) == null) {
            tell(player, "&7'&c" + Messenger.getRecent().get(player.getName()) + "&7' is not online!");
            return;
        }

        Player target = Bukkit.getPlayer(Messenger.getRecent().get(player.getName()));

        if (Messenger.getCooldowns().containsKey(player.getName())) {
            Cooldown cooldown = Messenger.getCooldowns().get(player.getName());
            if (!cooldown.hasExpired()) {
                tell(player, Messenger.getProperties().getProperty("cooldown").replace("+time", String.valueOf((cooldown.getExpiryTime()-System.currentTimeMillis()) / 1000)));
                return;
            }
        }

        String message = "";
        for (int i = 0; i < args.length; i++) {
            message = message + args[i] + " ";
        }

        message.trim();

        tell(player, Messenger.getProperties().getProperty("formatSender").replace("+recipient", target.getName()).replace("+message", message));
        tell(target, Messenger.getProperties().getProperty("formatRecipient").replace("+sender", player.getName()).replace("+message", message));

        if (Messenger.getRecent().containsKey(player.getName())) { Messenger.getRecent().remove(player.getName()); }
        if (Messenger.getRecent().containsKey(target.getName())) { Messenger.getRecent().remove(target.getName()); }

        Messenger.getRecent().put(player.getName(), target.getName());
        Messenger.getRecent().put(target.getName(), player.getName());

        for (Player spy : Messenger.getSpies()) {
            if (!spy.getName().equals(player.getName()) && !spy.getName().equals(target.getName())) {
                tell(spy, Messenger.getProperties().getProperty("formatSpy").replace("+sender", player.getName()).replace("+recipient", target.getName()).replace("+message", message));
            }
        }

        if (Messenger.getInstance().getConfig().get("console-log") != null &&
                Messenger.getInstance().getConfig().getBoolean("console-log") == true) {
            Bukkit.getConsoleSender().sendMessage(message);
        }

        if (Messenger.getCooldowns().containsKey(player.getName())) {
            Messenger.getCooldowns().remove(player.getName());
        }
        Cooldown cooldown = new Cooldown();
        cooldown.expireIn((long) Messenger.getInstance().getConfig().getInt("cooldown")*1000);
        Messenger.getCooldowns().put(player.getName(), cooldown);
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }

    @Override
    public String permission() {
        return "messenger.message";
    }

}
