package com.abhiram.minestore.file;

import com.abhiram.minestore.MineStore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AbstractFile {
    private MineStore plugin;
    private File file;
    protected FileConfiguration configuration;
    protected Boolean save;


    public AbstractFile(MineStore main,String filename,String datafolder,Boolean save)
    {
        this.plugin = main;
        File file1 = new File(main.getDataFolder() + datafolder);

        if(!file1.exists())
        {
            file1.mkdirs();
        }

        file = new File(file1,filename);
        if(!file.exists())
        {
            if(save)
            {
                plugin.saveResource(filename,false);
                configuration = YamlConfiguration.loadConfiguration(file);
                return;
            }

            try
            {
                file.createNewFile();
            }catch (Exception exp)
            {
                exp.printStackTrace();
            }
        }

        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try{
            configuration.save(file);
        }catch(IOException e){
            plugin.getLogger().info("There was an error while saving " + file.getName());
        }
    }

    public FileConfiguration getConfig(){
        return configuration;
    }

    public void reload(){
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void delete() {
        file.delete();
    }
}
