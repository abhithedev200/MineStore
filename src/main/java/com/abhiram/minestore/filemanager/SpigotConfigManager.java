package com.abhiram.minestore.filemanager;

import com.abhiram.minestore.MineStore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SpigotConfigManager {
    private String fileName;
    private FileConfiguration customConfig;
    private MineStore plugin;
    private File customFile;


    public SpigotConfigManager(String fileName,MineStore plugin,String filepath){
        this.customFile = new File(filepath,fileName);
        this.fileName = fileName;
        this.plugin = plugin;
        createCustomFile();
    }


        private void createCustomFile() {
        if (!customFile.exists()) {
            customFile.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }

        customConfig = new YamlConfiguration();

        try {
            customConfig.load(customFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return customConfig;
    }

    public void Reload() {
        try {
            customConfig.load(customFile);
        } catch (Exception e) {
            System.out.println("Config not found! Copying the default config!");
            createCustomFile();
        }
    }

    public void Save(){
        try{
            customConfig.save(customFile);
        }catch (Exception e){
            plugin.getLogger().info("--------------------------------------");
            plugin.getLogger().info("MineStore official plugin v2.0 by abhi");
            plugin.getLogger().info("Plugin not able to save some files :(");
            plugin.getLogger().info("--------------------------------------");
        }
    }
}
