package com.abhiram.minestore.backend;

import com.abhiram.minestore.backend.donator.TopDonatorManager;

import java.util.ArrayList;

public class BackendServiceManager {
    private final ArrayList<BackendService> backendServices = new ArrayList<>();

    public BackendServiceManager(){
        this.registerBackEndService(new TopDonatorManager());
    }

    public void updateAll(){
        for(BackendService backendService : this.backendServices){
            backendService.update();
        }
    }

    public BackendService getBackendService(String name){
        for(BackendService backendService : this.backendServices){
            if(backendService.getName().toLowerCase().equalsIgnoreCase(name)){
                return backendService;
            }
        }

        return null;
    }

    public void registerBackEndService(BackendService backendService){
        this.backendServices.add(backendService);
    }
}
