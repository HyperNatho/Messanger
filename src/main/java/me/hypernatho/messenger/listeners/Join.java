package me.hypernatho.messenger.listeners;

import me.hypernatho.messenger.Messenger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Messenger.getInstance().getConfig().getStringList("spies").contains(player.getUniqueId().toString())) {
            Messenger.getSpies().add(player);
        }
    }
}
