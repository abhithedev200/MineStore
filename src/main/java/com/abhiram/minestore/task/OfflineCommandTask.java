package com.abhiram.minestore.task;

import com.abhiram.minestore.MineStore;

public class OfflineCommandTask implements Runnable {
    private final MineStore plugin;

    public OfflineCommandTask(){
        this.plugin = MineStore.getInstance();
    }

    @Override
    public void run(){
        try {
            plugin.getOfflinePlayerCommandManager().runCommand();
        }catch (Exception exception){
            plugin.getLogger().info("There was an error while executing offline player commands");
        }
    }
}
