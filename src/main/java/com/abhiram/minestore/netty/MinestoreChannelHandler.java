package com.abhiram.minestore.netty;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.event.MinestoreCommandEvent;
import com.abhiram.minestore.model.Command;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.bukkit.Bukkit;

import java.util.concurrent.Callable;

public class MinestoreChannelHandler extends SimpleChannelInboundHandler<String> {
    private final Gson gson = new Gson();
    private final MineStore plugin;

    public MinestoreChannelHandler(){
        this.plugin = MineStore.getInstance();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
        Command command = gson.fromJson(request,Command.class);
        String password = plugin.getMainPluginConfig().getConfig().getString("password");

        if(command != null){
            if(command.getPassword().equalsIgnoreCase(password)){
                MinestoreCommandEvent commandEvent = new MinestoreCommandEvent(command.getCommand());
                Bukkit.getPluginManager().callEvent(commandEvent);

                if(!commandEvent.isCancelled()){
                    if(command.IsPlayerOnlineNeeded()){
                        if(Bukkit.getPlayer(command.getUsername()) == null){
                            plugin.getOfflinePlayerCommandManager().addCommandToQueue(command);
                            plugin.getLogger().info("Got an order from MineStore. player: " + command.getUsername() + " is offline. so command is added to queue!");
                            return;
                        }
                    }
                    plugin.getLogger().info("Got an order from MineStore. Running a command " + command.getCommand());
                    Bukkit.getScheduler().callSyncMethod(plugin,new Callable<Boolean>() {
                        @Override
                        public Boolean call() {
                            return Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.getCommand());
                        }
                    }).get();
                }
            }
        }
    }
}
