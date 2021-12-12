package com.abhiram.minestore.backend.donator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donator {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("amount")
    @Expose
    private String amount;


    public String getUsername(){
        return this.username;
    }

    public String getAmount(){
        return this.amount;
    }
}
