package com.abhiram.minestore.hook;

import com.abhiram.minestore.hook.impl.papi.PlaceholderAPIHook;

import java.util.ArrayList;

public class PluginHookManager {
    private final ArrayList<PluginHooker> pluginHookers = new ArrayList<>();

    public PluginHookManager(){
        this.registerHooker(new PlaceholderAPIHook());
    }

    public void registerHooker(PluginHooker pluginHooker) {
        this.pluginHookers.add(pluginHooker);
    }

    public void hook(){
        for(PluginHooker pluginHooker : this.pluginHookers){
            pluginHooker.hook();
        }
    }

    public PluginHooker getPluginHooker(String name){
        for(PluginHooker pluginHooker : this.pluginHookers){
            if(pluginHooker.getName().equalsIgnoreCase(name)){
                return pluginHooker;
            }
        }

        return null;
    }
}
