package com.minecraftwars.incinerator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener
{
	public InventoryListener()
	{
		
	}
	@EventHandler(ignoreCancelled = true)
	public void onFurnaceHopperAdd(InventoryMoveItemEvent event)
	{
		
		if (event.getDestination() instanceof org.bukkit.inventory.FurnaceInventory)
		{
			//check coords against database if found incinerate the itemStack and remove fuel.
			InventoryHolder holder = event.getDestination().getHolder();
			Block block = null;
			if (holder instanceof Furnace)
			{
				block = (Block) holder;
			}
			else
			{
				return;
			}
			int id = IncineratorManager.getInstance().getIncinerators().size();
			IncinFurnace incinFurnace = new IncinFurnace(id++, block);
			if (IncineratorManager.getInstance().contains(incinFurnace))
			{
				// do things...
				Furnace furn = (Furnace) block;
				ItemStack tempStack = furn.getInventory().getItem(0);
				int qty = tempStack.getAmount();
				
				
			}
			
		}
	}
	@EventHandler(ignoreCancelled = true)
	public void onFurnaceManualAdd(InventoryClickEvent event)
	{
		if (event.getInventory() instanceof org.bukkit.inventory.FurnaceInventory)
		{
			//check coords against database if found incinerate the itemStack and remove fuel.
			//if ()
		}
	}
	@EventHandler(ignoreCancelled = true)
	public void onFurnaceBurn(FurnaceBurnEvent event)
	{
		Block block = event.getBlock();
		int id = IncineratorManager.getInstance().getIncinerators().size();
		IncinFurnace incinFurnace = new IncinFurnace(id++, block);
		if (IncineratorManager.getInstance().contains(incinFurnace))
		{
			// do things...
			Furnace furn = (Furnace)block;
			ItemStack tempStack = furn.getInventory().getItem(0);
			int qty = tempStack.getAmount();
			
		
			
		}
	}
}
