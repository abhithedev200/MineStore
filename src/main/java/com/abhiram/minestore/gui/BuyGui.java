package com.abhiram.minestore.gui;

import com.abhiram.minestore.MineStore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BuyGui {
    private Inventory GUI;

    private MineStore plugin;

    public BuyGui(MineStore plugin){
        this.plugin = plugin;
        SetGui();
    }

    private void SetGui(){
        BuyGuiManager manager = new BuyGuiManager();
        GUI = Bukkit.createInventory(null,plugin.buy.getConfig().getInt("GUI-settings.Gui-size"),ChatColor.translateAlternateColorCodes('&',
                this.plugin.buy.getConfig().getString("GUI-settings.Gui-name")));
        for(String sec : plugin.buy.getConfig().getConfigurationSection("GUI").getKeys(false)){
            ArrayList<String> lore = (ArrayList<String>) plugin.buy.getConfig().getStringList("GUI." + sec +".lore");
            ArrayList<String> lore_tranlated = new ArrayList<String>();
            for(String lore1 : lore){
                lore_tranlated.add(ChatColor.translateAlternateColorCodes('&',lore1));
            }
            ItemStack item = new ItemStack(Material.matchMaterial(this.plugin.buy.getConfig().getString("GUI." + sec +".Type")));
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',plugin.buy.getConfig().getString("GUI." + sec +".name")));
            meta.setLore(lore_tranlated);
            item.setItemMeta(meta);
            int slot = plugin.buy.getConfig().getInt("GUI." + sec +".slot");
            GUI.setItem(slot,item);
        }
    }

    public void OpenInventory(Player player){
        player.openInventory(GUI);
    }
}
