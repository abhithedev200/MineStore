package com.abhiram.minestore;

import com.abhiram.minestore.backend.BackendServiceManager;
import com.abhiram.minestore.command.OfflinePlayerCommandManager;
import com.abhiram.minestore.file.Config;
import com.abhiram.minestore.file.MessageConfig;
import com.abhiram.minestore.hook.PluginHookManager;
import com.abhiram.minestore.netty.MinestoreChannelHandler;
import com.abhiram.minestore.plugincommands.MainCommand;
import com.abhiram.minestore.task.BackEndUpdateTask;
import com.abhiram.minestore.task.OfflineCommandTask;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MineStore extends JavaPlugin {
    private static MineStore instance;

    /**
     * Plugin Configurations
     */
    private Config config;
    private MessageConfig messageConfig;

    /**
     * Backend Service's manager we need to use to connect with webstore
     */
    private BackendServiceManager backendServiceManager;


    /**
     * Plugin Hook Manager - manager all the plugin hookers
     */
    private PluginHookManager pluginHookManager;

    /**
     * Offline Command manager. manager all the offline player commands
     */
    private OfflinePlayerCommandManager offlinePlayerCommandManager;

    @Override
    public void onEnable(){
        instance = this;
        this.config = new Config();
        this.messageConfig = new MessageConfig();
        this.backendServiceManager = new BackendServiceManager();
        this.pluginHookManager = new PluginHookManager();
        this.offlinePlayerCommandManager = new OfflinePlayerCommandManager();

        // Hook with other plugins
        pluginHookManager.hook();

        // Start the netty tcp server
        try {
            this.StartNettyServer(config.getConfig().getInt("port"));
        }catch (Exception exception){
            exception.printStackTrace();
            getLogger().info("There was an error while starting netty tcp server");
        }

        // Just update all the backend cache for one time
        this.backendServiceManager.updateAll();

        // Register all the commands
        this.registerCommands();

        // Init all the repeating and async tasks
        this.initTasks();
    }

    private void registerCommands(){
        getCommand("ms").setExecutor(new MainCommand());
    }

    private void initTasks(){
        getLogger().info("Starting Minestore tasks.....");
        Bukkit.getScheduler().runTaskTimerAsynchronously(this,new BackEndUpdateTask(),0,20 * 120);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this,new OfflineCommandTask(),1,1);
    }

    private void StartNettyServer(int port) throws Exception {
        getLogger().info("Starting Server at " + port);

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(eventLoopGroup,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new MinestoreChannelHandler())
                .childOption(ChannelOption.SO_KEEPALIVE,true);

        serverBootstrap.bind(port).sync();
    }

    public Config getMainPluginConfig(){
        return this.config;
    }

    public MessageConfig getMessageConfig(){
        return this.messageConfig;
    }
    
    public BackendServiceManager getBackendServiceManager(){
        return this.backendServiceManager;
    }

    public OfflinePlayerCommandManager getOfflinePlayerCommandManager(){
        return this.offlinePlayerCommandManager;
    }

    public static MineStore getInstance(){
        return instance;
    }
}
