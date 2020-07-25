package com.abhiram.minestore.api;

import com.abhiram.minestore.MineStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class TopDonoManager {

    private MineStore plugin;

    public TopDonoManager(MineStore plugin){
        this.plugin = plugin;
    }

    private URL getSiteURL() throws Exception {
        return new URL(this.plugin.config.getConfig().getString("Url-api"));
    }


    private String getTopDono() throws Exception {
        URL baseURL = getSiteURL();

        URLConnection connection = baseURL.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        BufferedReader in = null;
        String line;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (FileNotFoundException var5) {
            System.out.println("Unable to get Top Donators");
        }

        StringBuilder buffer = new StringBuilder();

        while((line = in.readLine()) != null) {
            buffer.append(line);
        }

        in.close();
        return buffer.toString();
    }


    public ArrayList<String> getTopDonators() throws Exception{
        ArrayList<String> dono1 = new ArrayList<String>();
        Gson gson = new GsonBuilder().create();
        Donator[] dono = gson.fromJson(getTopDono(),Donator[].class);

        int a = 0;

        for(Donator don2 : dono) {
            dono1.add(dono[a].getUsername() + "  "+ "$"+ dono[a].getAmount());
            a++;
        }

        return dono1;
    }
}
