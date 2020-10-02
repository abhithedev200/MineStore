package com.abhiram.minestore.websocket;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.api.MinestoreAPIEvents;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.bukkit.Bukkit;

import java.util.concurrent.Callable;

public class NettyCommandHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String response) throws Exception {

        String websoket_password = MineStore.getinstance().config.getConfig().getString("password");


        final String[] pass = response.split("  ");
        if (pass[0].equalsIgnoreCase(websoket_password)) {
            MinestoreAPIEvents event = new MinestoreAPIEvents(pass[1]);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                MineStore.getinstance().getLogger().info("Got an order from MineStore. Running a command " + pass[1]);
                Bukkit.getScheduler().callSyncMethod(MineStore.getinstance(), new Callable<Boolean>() {
                    @Override
                    public Boolean call() {
                        return Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pass[1]);
                    }
                }).get();
            }
        }else
        {
            MineStore.getinstance().getLogger().info("MineStore received an order, but unable to process it. ERROR (Invalid Password)");
        }
    }

}
