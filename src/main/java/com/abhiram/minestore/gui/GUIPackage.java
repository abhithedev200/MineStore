package com.abhiram.minestore.gui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.bukkit.event.EventHandler;

public class GUIPackage {
    /**
     * This is an test class only
     * Dont include this when you compile
     */
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String lore;


    public String getName(){
        return name;
    }

    public String getLore(){
        return lore;
    }
}
