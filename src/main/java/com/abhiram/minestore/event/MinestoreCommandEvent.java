package com.abhiram.minestore.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredListener;

import java.util.ArrayList;

public class MinestoreCommandEvent extends Event {
    private String command;
    private Boolean cancel = false;

    private final HandlerList handlers = new HandlerList();

    public MinestoreCommandEvent(String command){
        super(true);
        this.command = command;
    }


    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public HandlerList getHandlerList() {
        return handlers;
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

    public ArrayList<RegisteredListener[]> getRegisteredListener(){
        ArrayList<RegisteredListener[]> plugins = new ArrayList<>();

        plugins.add(getHandlers().getRegisteredListeners());

        return plugins;
    }

}
