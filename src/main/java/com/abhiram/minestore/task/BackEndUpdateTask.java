package com.abhiram.minestore.task;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.backend.donator.Donator;

public class BackEndUpdateTask implements Runnable {
    private final MineStore plugin;

    public BackEndUpdateTask(){
        this.plugin = MineStore.getInstance();
    }


    @Override
    public void run(){
        this.plugin.getBackendServiceManager().updateAll();

//        for(Object donator : this.plugin.getBackendServiceManager().getBackendService("donatormanager").getData()){
//            Donator donator1 = (Donator) donator;
//            System.out.println(donator1.getUsername());
//        }
    }
}
