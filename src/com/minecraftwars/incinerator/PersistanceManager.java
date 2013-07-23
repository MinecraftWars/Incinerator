package com.minecraftwars.incinerator;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class PersistanceManager 
{
    private static PersistanceManager instance;
    private File file = new File(Incinerator.getInstance().getDataFolder(), "SaveData.yml");

    public PersistanceManager() 
    {
        instance = this;
    }

    public static PersistanceManager getInstance() 
    {
        return instance;
    }

    public void save() throws IOException 
    {
    	YamlConfiguration saveFile = new YamlConfiguration();
    	IncineratorManager.getInstance().save(saveFile.createSection("Incinerators"));
        file.delete();
        file.createNewFile();
        saveFile.save(file);
    }

    public static void load() throws IOException
    {
        new PersistanceManager();
        if (!instance.loadData())
        {
            new IncineratorManager();
        }
    }

    private boolean loadData()
    {
        if (!file.exists()) {
            return false;
        }
        YamlConfiguration saveFile = YamlConfiguration.loadConfiguration(file);
        IncineratorManager.load(saveFile.getConfigurationSection("Incinerators"));
        return true;
    }
}
