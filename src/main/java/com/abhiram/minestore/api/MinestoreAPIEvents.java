package com.abhiram.minestore.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredListener;

import java.util.ArrayList;

public class MinestoreAPIEvents extends Event {

    private String command;
    private Boolean cancel;
    private static final HandlerList HANDLERS = new HandlerList();

    public MinestoreAPIEvents(String command){
        this.command = command;
    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public String getCommand(){
        return command;
    }

    public void SetCanceled(boolean val){
        this.cancel = val;
    }

    public boolean isCancelled(){
        return cancel;
    }

    public ArrayList<RegisteredListener[]> getRegisteredListner(){
        ArrayList<RegisteredListener[]> plugins = new ArrayList<org.bukkit.plugin.RegisteredListener[]>();

        plugins.add(getHandlers().getRegisteredListeners());

        return plugins;
    }
}
