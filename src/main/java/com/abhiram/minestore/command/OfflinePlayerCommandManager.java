package com.abhiram.minestore.command;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.model.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class OfflinePlayerCommandManager {
    private final ArrayList<Command> command_queue = new ArrayList<>();
    private final MineStore plugin;

    public OfflinePlayerCommandManager(){
        this.plugin = MineStore.getInstance();
    }

    public void runCommand() throws ExecutionException, InterruptedException {
        for(Player player : Bukkit.getOnlinePlayers()){
            Command command = this.getCommand(player);
            if(command != null){
                Bukkit.getScheduler().callSyncMethod(plugin, new Callable<Boolean>() {
                    @Override
                    public Boolean call() {
                        return Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.getCommand());
                    }
                }).get();
                command_queue.remove(command);
            }
        }
    }

    public Command getCommand(Player player){
        for(Command command : this.command_queue){
            if(command.getUsername().equalsIgnoreCase(player.getName())){
                return command;
            }
        }

        return null;
    }


    public void addCommandToQueue(Command command){
        this.command_queue.add(command);
    }
}
