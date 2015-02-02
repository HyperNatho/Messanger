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
            return;
        }

        Messenger.getSpies().add(player);
        tell(player, Messenger.getProperties().getProperty("spyEnabled"));
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
