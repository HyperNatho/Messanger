package me.hypernatho.messenger.commands.admin;

import me.hypernatho.messenger.Messenger;
import me.hypernatho.messenger.commands.BaseCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spy extends BaseCommand {

    @Override
    public void execute(CommandSender sender, String tag, String[] args) {
        Player player = (Player) sender;
        if (Messenger.getSpies().contains(player)) {
            Messenger.getSpies().remove(player);
            tell(player, Messenger.getProperties().getProperty("spyDisabled"));

            Messenger.getInstance().getConfig().getStringList("spies").remove(player.getUniqueId().toString());
            Messenger.getInstance().saveConfig();
            return;
        }

        Messenger.getSpies().add(player);
        tell(player, Messenger.getProperties().getProperty("spyEnabled"));

        Messenger.getInstance().getConfig().getStringList("spies").add(player.getUniqueId().toString());
        Messenger.getInstance().saveConfig();
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }

    @Override
    public String permission() {
        return "messenger.spy";
    }

}
