package me.hypernatho.messenger.listeners;
// Copyright (c) 2015 MinecraftArcade. All Rights Reserved.

import me.hypernatho.messenger.Messenger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (Messenger.getSpies().contains(event.getPlayer())) {
            Messenger.getSpies().remove(event.getPlayer());
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        if (Messenger.getSpies().contains(event.getPlayer())) {
            Messenger.getSpies().remove(event.getPlayer());
        }
    }

}
