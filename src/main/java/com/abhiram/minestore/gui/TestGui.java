package com.abhiram.minestore.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class TestGui {
    private Inventory gui;

    /**
     * This is an test class only
     * Dont include this when you compile
     */

    public TestGui(){
        setGui();
    }

    public void setGui(){
        BuyGuiManager manager = new BuyGuiManager();
        gui = Bukkit.createInventory(null,54,"Buy");
        try{
            int a = 0;
            for(String name : manager.getPackageName()){
                ArrayList<String> lore = new ArrayList<String>();
                lore.add(manager.getPackageLore().get(a));
                ItemStack item = new ItemStack(Material.LEATHER_HELMET);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(name);
                meta.setLore(lore);
                item.setItemMeta(meta);


                gui.setItem(a,item);
                a++;
            }
        }catch (Exception e){

        }
    }

    public void openGui(Player p){
        p.openInventory(gui);
    }
}
