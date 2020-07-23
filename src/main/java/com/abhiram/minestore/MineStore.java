package com.abhiram.minestore;

import com.abhiram.minestore.commands.BuyCommand;
import com.abhiram.minestore.commands.ReloadCommand;
import com.abhiram.minestore.filemanager.SpigotConfigManager;
import com.abhiram.minestore.websocket.CommandHandler;
import events.BuyEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MineStore extends JavaPlugin {

    // Config
    public SpigotConfigManager config;

    // Buy.yml
    public SpigotConfigManager buy;

    private CommandHandler handler;
    // Plugin onEnable Method
    @Override
    public void onEnable(){

        // Register all commands
        RegisterCommands();

        // Register all Events
        RegisterEvents();

        // config.yml
        config = new SpigotConfigManager("config.yml",this,getDataFolder().toString());
        // buy.yml
        buy = new SpigotConfigManager("buy.yml",this,this.getDataFolder().toString());
        int port = config.getConfig().getInt("port");
        String password = config.getConfig().getString("password");

        try{
            handler = new CommandHandler(port,password,this);
            Bukkit.getScheduler().runTaskTimerAsynchronously(this,handler, 2,2);
        }catch (Exception e){
            getLogger().info("-------------------------------------");
            getLogger().info("Minestore Version: 1.0");
            getLogger().info("ERROR: Unable to listen on port " + port);
            getLogger().info("Plugin Shuting down");
            getLogger().info("--------------------------------------");

            // Server ShutDown
            Bukkit.getServer().shutdown();
        }
    }


    @Override
    public void onDisable(){
        try {
            handler.getServerSocket().close();
        }catch (Exception e){

        }
    }

    void RegisterCommands(){
        this.getCommand("buy").setExecutor(new BuyCommand(this));
        this.getCommand("ms").setExecutor(new ReloadCommand(this));
    }

    void RegisterEvents(){
        Bukkit.getPluginManager().registerEvents(new BuyEvent(this),this);
    }

}
