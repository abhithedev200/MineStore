package com.abhiram.minestore.gui.jsonobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {
    @SerializedName("name")
    @Expose
    private String package_name;

    @SerializedName("category_url")
    @Expose
    private String category_name;

    @SerializedName("item_lore")
    @Expose
    private String package_lore;

    public String getPackageName()
    {
        return package_name;
    }

    public String getCategoryname()
    {
        return category_name;
    }

    public String getPackageLore()
    {
        return package_lore;
    }
}
