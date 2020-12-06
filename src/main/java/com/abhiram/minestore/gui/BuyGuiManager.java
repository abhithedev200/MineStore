package com.abhiram.minestore.gui;

import com.abhiram.minestore.MineStore;
import com.abhiram.minestore.gui.jsonobjects.Category;
import com.abhiram.minestore.gui.jsonobjects.Package;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class BuyGuiManager {
    private HashMap<String, Inventory> gui_map = new HashMap<String, Inventory>();
    private final Gson gson = new Gson();
    private static BuyGuiManager buyGuiManager;

    public BuyGuiManager()
    {
        setCategoryGui();
    }

    private Inventory getPackages(java.lang.String url_name)
    {
        Inventory gui = Bukkit.createInventory(null,52,"Package!");

        try {
            Package[] packages = gson.fromJson(getPackagesFromAPI(), Package[].class);

            int a = 0;
            for(Package packag : packages)
            {
                if(packag.getCategoryname().equalsIgnoreCase(url_name))
                {
                    ItemStack package_item = new ItemStack(Material.EMERALD_BLOCK);
                    ItemMeta package_item_meta = package_item.getItemMeta();

                    package_item_meta.setDisplayName(packag.getPackageName());
                    ArrayList<java.lang.String> lore = new ArrayList<java.lang.String>();
                    lore.add(packag.getPackageLore());
                    package_item_meta.setLore(lore);

                    package_item.setItemMeta(package_item_meta);

                    gui.setItem(a,package_item);
                    a++;
                }
            }
        }
        catch (Exception e)
        {
            MineStore.getinstance().getLogger().info("Unable to connect to Minestore api.. please check api url in config.yml");
        }

        return gui;
    }
    private void setCategoryGui()
    {
        Inventory gui = Bukkit.createInventory(null,27,"Choose Category!");

        try {
            Category[] category = gson.fromJson(getCategory(),Category[].class);

            int a = 0;
            for (Category categorys : category)
            {
                ItemStack category_item = new ItemStack(Material.DIAMOND_BLOCK);
                ItemMeta category_item_meta = category_item.getItemMeta();
                category_item_meta.setDisplayName(categorys.getName());

                category_item.setItemMeta(category_item_meta);

                gui.setItem(a,category_item);
                a++;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            MineStore.getinstance().getLogger().info("Unable to connect to minestore api. please check api url in config.yml");
        }

        gui_map.put("CategoryGui",gui);
    }

    private String getCategory() throws Exception
    {

        URLConnection connection = new URL("https://pro.minestorecms.com/api/gui/categories").openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder responce = new StringBuilder();

        String line;

        while ((line = in.readLine()) != null)
        {
            responce.append(line);
        }
        in.close();
        return responce.toString();
    }

    private String getPackagesFromAPI() throws Exception
    {
        URLConnection connection = new URL("https://pro.minestorecms.com/api/gui/packages").openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder responce = new StringBuilder();

        String line;

        while ((line = in.readLine()) != null)
        {
            responce.append(line);
        }
        in.close();
        return responce.toString();
    }
    public Inventory getGui(String guiname)
    {
        if(gui_map.containsKey(guiname))
        {
            return gui_map.get(guiname);
        }

        return null;
    }
    public static BuyGuiManager getBuyGuiManager()
    {
        if(buyGuiManager == null)
        {
            buyGuiManager = new BuyGuiManager();
        }

        return buyGuiManager;
    }
}
