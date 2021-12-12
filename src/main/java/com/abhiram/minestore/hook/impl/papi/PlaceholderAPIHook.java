package com.abhiram.minestore.hook.impl.papi;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.hook.PluginHooker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class PlaceholderAPIHook implements PluginHooker {
    private final MineStore plugin;

    public PlaceholderAPIHook(){
        this.plugin = MineStore.getInstance();
    }


    @Override
    public void hook() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");

        if(plugin != null && plugin.isEnabled()){
            plugin.getLogger().info("PlaceholdersAPI has been found! Adding expansion...");
            new PlaceholderAPIExpansion().register();
        }
    }

    @Override
    public String getName() {
        return "placeholderapi";
    }
}
