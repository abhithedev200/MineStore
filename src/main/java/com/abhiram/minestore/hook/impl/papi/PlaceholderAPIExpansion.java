package com.abhiram.minestore.hook.impl.papi;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.backend.BackendService;
import com.abhiram.minestore.backend.donator.Donator;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {
    private MineStore plugin;
    private BackendService topdonoservice;

    public PlaceholderAPIExpansion() {
        this.plugin = MineStore.getInstance();
        this.topdonoservice = plugin.getBackendServiceManager().getBackendService("donatormanager");
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getIdentifier() {
        return "minestore";
    }

    @Override
    public String getAuthor() {
        return "Abhiram";
    }

    @Override
    public String getVersion() {
        return "V2.0";
    }


    @Override
    public String onPlaceholderRequest(Player p, String identifier){
        if(identifier.contains("username_top")) {
            String[] split = identifier.split("username_top_");
            Donator donator = (Donator) topdonoservice.getData().get(Integer.parseInt(split[1]));
            return donator.getUsername();
        }else if (identifier.contains("amount_top")){
            String[] split = identifier.split("amount_top_");
            Donator donator = (Donator) topdonoservice.getData().get(Integer.parseInt(split[1]));
            double amount = Double.parseDouble(donator.getAmount());
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(amount);
        }

        return plugin.getMessageConfig().getConfig().getString("empty-placeholder");
    }
}
