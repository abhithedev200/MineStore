package com.abhiram.minestore.backend.donator;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.backend.BackendService;
import com.abhiram.minestore.backend.RequestContainer;

import java.util.Arrays;
import java.util.List;

public class TopDonatorManager implements BackendService<Donator> {
    private final RequestContainer<Donator[]> requestContainer;
    private final MineStore plugin;
    private Donator[] donators;
    private final boolean debug;

    public TopDonatorManager(){
        this.plugin = MineStore.getInstance();
        this.requestContainer = new RequestContainer<>(plugin,"api/top_donators",Donator[].class);
        this.debug = this.plugin.getMainPluginConfig().getConfig().getBoolean("debug-mode");
        this.update();
    }

    @Override
    public void update(){
        try {
            this.donators = requestContainer.request();
        }catch (Exception exception){
            if(debug) {
                exception.printStackTrace();
                plugin.getLogger().info("There was an error while fetching top donors");
            }
        }
    }

    @Override
    public String getName() {
        return "donatormanager";
    }

    @Override
    public List<Donator> getData() {
        return Arrays.asList(donators);
    }
}
