package me.hypernatho.messenger.listeners;

import me.hypernatho.messenger.Messenger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class Quit implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (Messenger.getSpies().contains(event.getPlayer())) {
            Messenger.getSpies().remove(event.getPlayer());

            List<String> list = Messenger.getInstance().getConfig().getStringList("spies");
            if (!list.contains(event.getPlayer().getUniqueId().toString())) {
                list.add(event.getPlayer().getUniqueId().toString());
                Messenger.getInstance().getConfig().set("spies", list);
                Messenger.getInstance().saveConfig();
            }
            return;
        }

        List<String> list = Messenger.getInstance().getConfig().getStringList("spies");
        if (list.contains(event.getPlayer().getUniqueId().toString())) {
            list.remove(event.getPlayer().getUniqueId().toString());
            Messenger.getInstance().getConfig().set("spies", list);
            Messenger.getInstance().saveConfig();
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        if (Messenger.getSpies().contains(event.getPlayer())) {
            Messenger.getSpies().remove(event.getPlayer());

            List<String> list = Messenger.getInstance().getConfig().getStringList("spies");
            if (!list.contains(event.getPlayer().getUniqueId().toString())) {
                list.add(event.getPlayer().getUniqueId().toString());
                Messenger.getInstance().getConfig().set("spies", list);
                Messenger.getInstance().saveConfig();
            }
            return;
        }

        List<String> list = Messenger.getInstance().getConfig().getStringList("spies");
        if (list.contains(event.getPlayer().getUniqueId().toString())) {
            list.remove(event.getPlayer().getUniqueId().toString());
            Messenger.getInstance().getConfig().set("spies", list);
            Messenger.getInstance().saveConfig();
        }
    }

}
