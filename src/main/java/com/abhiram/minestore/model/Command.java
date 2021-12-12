package com.abhiram.minestore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Command {
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("command")
    @Expose
    private String command;

    @SerializedName("is_online_required")
    @Expose
    private boolean isonlineneeded;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getCommand()
    {
        return command;
    }

    public boolean IsPlayerOnlineNeeded()
    {
        return isonlineneeded;
    }
}
