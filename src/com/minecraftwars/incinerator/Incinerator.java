package com.minecraftwars.incinerator;

import java.util.logging.Logger;

import javax.persistence.Lob;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.event.block.BlockBreakEvent;

public class Incinerator extends JavaPlugin
{
	private static Incinerator instance;
	
	public static Logger LOG;
	private InventoryListener inventoryListener = new InventoryListener();
	private BlockListener blockListener = new BlockListener();
	
	@Override
	public void onEnable()
	{
		instance = this;
		LOG = getLogger();
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(inventoryListener, this);
		pm.registerEvents(blockListener, this);
		
		new ConfigManager();
		
		new IncineratorManager();
		
		//new PersistanceManager(); // TODO: things

		LOG.info("Enabled!");
	}
	
	@Override
	public void onDisable()
	{
		// TODO: save
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
