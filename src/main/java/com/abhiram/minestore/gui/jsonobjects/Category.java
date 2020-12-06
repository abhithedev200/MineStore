package com.abhiram.minestore.gui.jsonobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("name")
    @Expose
    private String name;

    public String getName()
    {
        return name;
    }
}
