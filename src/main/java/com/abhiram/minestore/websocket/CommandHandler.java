package com.abhiram.minestore.websocket;


import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.api.MinestoreAPIEvents;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;

public class CommandHandler implements Runnable{
    private ServerSocket websoket;
    private int websocket_port;
    private String websoket_password;
    private MineStore plugin;

    public CommandHandler(int port, String websoket_password, MineStore plugin) throws Exception{
        this.websoket_password = websoket_password;
        this.websocket_port = port;
        this.plugin = plugin;
        websoket = new ServerSocket(port);
    }

    public void run(){
        try {
            Socket soc = websoket.accept();
            BufferedReader read = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String responce = read.readLine();
            read.close();

            final String[] pass = responce.split("  ");
            if(pass[0].equalsIgnoreCase(websoket_password)){
                MinestoreAPIEvents event = new MinestoreAPIEvents(pass[1]);
                if(event.isCancelled()) {
                    plugin.getLogger().info("Got an order from MineStore. Running a command " + pass[1]);
                    Bukkit.getScheduler().callSyncMethod(plugin, new Callable<Boolean>() {
                        @Override
                        public Boolean call() {
                            return Bukkit.dispatchCommand(Bukkit.getConsoleSender(), pass[1]);
                        }
                    }).get();
                    Bukkit.getPluginManager().callEvent(event);
                    return;
                }
            }
            plugin.getLogger().info("MineStore received an order, but unable to process it. ERROR (Invalid Password)");
            return;
        }catch (Exception var){

        }
    }

    public ServerSocket getServerSocket(){
        return websoket;
    }
}
