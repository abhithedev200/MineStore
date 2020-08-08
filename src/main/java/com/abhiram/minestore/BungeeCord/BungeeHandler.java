package com.abhiram.minestore.BungeeCord;

import com.abhiram.minestore.MineStore;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.concurrent.Callable;

public class BungeeHandler implements PluginMessageListener {
    private final MineStore plugin;

    public BungeeHandler(MineStore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(channel.equalsIgnoreCase("my:minestore")){
            ByteArrayDataInput message_convert = ByteStreams.newDataInput(message);
            final String command = message_convert.readUTF();
            plugin.getLogger().info("Got an order from Minestore BungeeCord Running it.");
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().callSyncMethod(plugin, new Callable<Boolean>() {
                            @Override
                            public Boolean call() {
                                return Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                            }
                        });
                    }
                },50);
        }
    }
}
