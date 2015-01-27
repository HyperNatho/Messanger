package me.hypernatho.messenger;

import lombok.Getter;
import me.hypernatho.messenger.commands.admin.Spy;
import me.hypernatho.messenger.commands.player.Message;
import me.hypernatho.messenger.commands.player.Reply;
import me.hypernatho.messenger.listeners.Join;
import me.hypernatho.messenger.listeners.Quit;
import me.hypernatho.messenger.objects.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Messenger extends JavaPlugin {

    public static Messenger getInstance() {
        return (Messenger) Bukkit.getPluginManager().getPlugin("Messenger");
    }

    @Getter public static Properties properties = new Properties();

    @Getter public static List<Player> spies = new ArrayList<Player>();
    @Getter public static HashMap<String, String> recent = new HashMap<String, String>();
    @Getter public static HashMap<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();

    @Override
    public void onEnable() {
        {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }

            if (!new File(getDataFolder(), "messages.properties").exists()) {
                saveResource("messages.properties", false);
            }

            try {
                properties.load(new FileInputStream(getDataFolder() + File.separator + "messages.properties"));
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§cUnable to access messages file! Aborting plugin startup!");

                return;
            }

            if (!new File(getDataFolder(), "config.yml").exists()) {
                saveDefaultConfig();
            }
        }
        {
            getCommand("message").setExecutor(new Message());
            getCommand("reply").setExecutor(new Reply());
            getCommand("spy").setExecutor(new Spy());
        }
        {
            PluginManager pm = Bukkit.getPluginManager();
            pm.registerEvents(new Join(), this);
            pm.registerEvents(new Quit(), this);
        }
    }
}