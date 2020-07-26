package com.abhiram.minestore.api;

import com.abhiram.minestore.MineStore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;



public class PlaceHolderApiHook extends PlaceholderExpansion {

    private MineStore plugin;
    private TopDonoManager manager;

    public PlaceHolderApiHook(MineStore plugin) {
        this.plugin = plugin;
        manager = new TopDonoManager(plugin);
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
    public String getPlugin() {
        return null;
    }

    @Override
    public String getAuthor() {
        return "Abhiram";
    }

    @Override
    public String getVersion() {
        return "V1.1";
    }


    @Override
    public String onPlaceholderRequest(Player p, String identifier){

        if(identifier.contains("username_top")) {
             String[] split = identifier.split("username_top_");
            try {
                return manager.getTopDonators().get(Integer.parseInt(split[1]));
            }catch (Exception e){

            }
        }else if (identifier.contains("amount_top")){
            String[] split = identifier.split("amount_top_");
            try {
                return manager.getTopDonatorsAmount().get(Integer.parseInt(split[1]));
            }catch (Exception e){

            }
        }
        return "It seems to be empty";
    }
}
