package com.abhiram.minestore;

import com.abhiram.minestore.BungeeCord.BungeeHandler;
import com.abhiram.minestore.api.PlaceHolderApiHook;
import com.abhiram.minestore.commands.BuyCommand;
import com.abhiram.minestore.commands.ReloadCommand;
import com.abhiram.minestore.filemanager.SpigotConfigManager;
import com.abhiram.minestore.task.CommandRunnerTask;
import com.abhiram.minestore.websocket.CommandHandler;
import com.abhiram.minestore.websocket.MineStoreCommandHandler;
import com.abhiram.minestore.websocket.NettyServerSocketHandler;
import events.BuyEvent;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MineStore extends JavaPlugin {

    // This class instance
    private static MineStore main;


    // Config
    public SpigotConfigManager config;

    // Buy.yml
    public SpigotConfigManager buy;

    private CommandHandler handler;

    // Plugin onEnable Method
    @Override
    public void onEnable(){
        main = this;

        // Added Bstats
        new Metrics(this,9575);

        // PlaceHolder api check
        PapiCheck();

        // Register all Events
        RegisterEvents();

        // config.yml
        config = new SpigotConfigManager("config.yml",this,this.getDataFolder().toString());

        // Register all commands
        RegisterCommands();

        // buy.yml
        buy = new SpigotConfigManager("buy.yml",this,this.getDataFolder().toString());

        // Init All Minestore Tasks
        getLogger().info("Running Init Tasks");
        Bukkit.getScheduler().runTaskTimer(this,new CommandRunnerTask(),20,20);
        if(!config.getConfig().getBoolean("Bungee-Mode")) {
            try
            {
                int port = config.getConfig().getInt("port");
                StartNettyServer(port);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }else {
            getLogger().info("----------------------------------");
            getLogger().info("Minestore BungeeCord Mode enabled.");
            getLogger().info("Version: 1.1");
            getLogger().info("----------------------------------");

            getServer().getMessenger().registerIncomingPluginChannel(this,"my:minestore",new BungeeHandler(this));
        }
    }


    @Override
    public void onDisable(){
        try {
            if(!config.getConfig().getBoolean("Bungee-Mode")) {
                handler.getServerSocket().close();
            }
        }catch (Exception e){

        }
    }

    void RegisterCommands(){
        if(config.getConfig().getBoolean("buy-enable")) {
            this.getCommand("buy").setExecutor(new BuyCommand(this));
        }
        this.getCommand("ms").setExecutor(new ReloadCommand(this));
    }

    void RegisterEvents(){
        Bukkit.getPluginManager().registerEvents(new BuyEvent(this),this);
    }

    void PapiCheck(){
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI").isEnabled()){
            getLogger().info("Placeholder API has been founded. Adding expansion");
            new PlaceHolderApiHook(this).register();
        }
    }

    @Deprecated
    private void CheckOrder(){
        int port = config.getConfig().getInt("port");
        String password = config.getConfig().getString("password");

        try{
            handler = new CommandHandler(port,password,this);
            Bukkit.getScheduler().runTaskTimerAsynchronously(this,handler, 2,2);
        }catch (Exception e){
            getLogger().info("-------------------------------------");
            getLogger().info("MINESTORE Version: 1.2");
            getLogger().info("ERROR: Unable to listen on port " + port);
            getLogger().info("Plugin Shuting down");
            getLogger().info("--------------------------------------");

            // Server ShutDown
            Bukkit.getServer().shutdown();
        }
    }


    /**
     * Handling minestore with netty server
     * @param port
     * @throws Exception
     */

    private void StartNettyServer(int port) throws Exception
    {
        getLogger().info("Starting Server at " + port);

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(eventLoopGroup,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerSocketHandler())
                .childOption(ChannelOption.SO_KEEPALIVE,true);

        serverBootstrap.bind(port).sync();
    }

    public static MineStore getinstance()
    {
        return main;
    }
}
