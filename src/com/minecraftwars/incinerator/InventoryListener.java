package com.minecraftwars.incinerator;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;

public class InventoryListener implements Listener
{
    public InventoryListener()
    {
        
    }

    @EventHandler(ignoreCancelled = true)
    public void onFurnaceBurn(FurnaceBurnEvent event)
    {
        Block block = event.getBlock();
        if (IncineratorManager.getInstance().getIncinerator(block.getLocation()) != null)
        {
            event.setCancelled(true);
        }
    }
}
