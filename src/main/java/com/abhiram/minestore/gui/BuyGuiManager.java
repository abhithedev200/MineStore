package com.abhiram.minestore.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BuyGuiManager {

    /**
     * This is an test class only
     * Dont include this when you compile
     */

    private String getGuiJson() throws Exception{
        URL baseURL = new URL("https://pro.minestorecms.com/api/gui/packages");

        URLConnection connection = baseURL.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setConnectTimeout(500000);
        connection.connect();
        BufferedReader in = null;
        String line;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (FileNotFoundException var5) {
            System.out.println("Unable to get Packages");
        }

        StringBuilder buffer = new StringBuilder();

        while((line = in.readLine()) != null) {
            buffer.append(line);
        }

        in.close();
        return buffer.toString();
    }

    public ArrayList<String> getPackageLore() throws Exception{
        Gson gson = new GsonBuilder().create();
        GUIPackage[] guiPackage = gson.fromJson(getGuiJson(),GUIPackage[].class);

        ArrayList<String> lore = new ArrayList<String>();

        int a = 0;

        for(GUIPackage pc : guiPackage){
            lore.add(guiPackage[a].getLore());
            a++;
        }

        return lore;
    }


    public ArrayList<String> getPackageName() throws Exception{
        Gson gson = new GsonBuilder().create();
        GUIPackage[] guiPackage = gson.fromJson(getGuiJson(),GUIPackage[].class);

        ArrayList<String> name = new ArrayList<String>();

        int a = 0;

        for(GUIPackage pc : guiPackage){
            name.add(guiPackage[a].getName());
            a++;
        }

        return name;
    }
}
