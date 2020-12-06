package com.abhiram.minestore.websocket;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.api.MinestoreAPIEvents;
import com.abhiram.minestore.managers.CommandManager;
import com.abhiram.minestore.websocket.jsonobjects.Command;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.bukkit.Bukkit;

import java.util.concurrent.Callable;

public class MineStoreCommandHandler extends SimpleChannelInboundHandler<String> {
    private Gson gson = new Gson();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String incoming) throws Exception {
        final Command command = gson.fromJson(incoming,Command.class);

        String websocket_password = MineStore.getinstance().config.getConfig().getString("password");

        if(command.getPassword().equalsIgnoreCase(websocket_password))
        {
            MinestoreAPIEvents event = new MinestoreAPIEvents(command.getCommand());
            Bukkit.getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                if(Bukkit.getPlayer(command.getUsername()) == null) {
                    CommandManager.getCommandManager().addCommand(command);
                }
                MineStore.getinstance().getLogger().info("Got an order from MineStore. Running a command " + command.getCommand());
                Bukkit.getScheduler().callSyncMethod(MineStore.getinstance(), new Callable<Boolean>() {
                    @Override
                    public Boolean call() {
                        return Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.getCommand());
                    }
                }).get();
            }
        }
        else
        {
            MineStore.getinstance().getLogger().info("MineStore received an order, but unable to process it. ERROR (Invalid Password)");
            return;
        }
    }
}
