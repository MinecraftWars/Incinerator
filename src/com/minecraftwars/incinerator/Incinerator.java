package com.minecraftwars.incinerator;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Incinerator extends JavaPlugin
{
    private static Incinerator instance;

    public static Logger LOG;
    private InventoryListener inventoryListener = new InventoryListener();
    private BlockListener blockListener = new BlockListener();
    private PlayerListener playerListener = new PlayerListener();

    @Override
    public void onEnable()
    {
        instance = this;
        LOG = getLogger();

        new ConfigManager();

        try {
            PersistanceManager.load();
        } catch (IOException e) {
            LOG.severe("Bad things just happened");
            e.printStackTrace();
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(inventoryListener, this);
        pm.registerEvents(blockListener, this);
        pm.registerEvents(playerListener, this);

        LOG.info("Enabled!");
    }

    @Override
    public void onDisable()
    {
        try {
            PersistanceManager.getInstance().save();
        } catch (IOException e) {
            LOG.severe("TOOO SPAAAAAAAAAAAAAAAAAAAAAACCEEE!");
            e.printStackTrace();
        }

        LOG.info("Disabled!");
    }

    public static Incinerator getInstance()
    {
        return instance;
    }

    public static void DEBUG(String message)
    {
        LOG.info(message);
    }
}
