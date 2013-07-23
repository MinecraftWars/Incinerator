package com.minecraftwars.incinerator;

import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener
{
	public InventoryListener()
	{
		
	}

	// FIXME: This is called only when an itemstack is burned as FUEL in a furnace.  I.E. When coal is used
	// Items that do not "burn" do not fire this event..
	// TODO: Come up with better way to manage the incinerator
	@EventHandler(ignoreCancelled = true)
	public void onFurnaceBurn(FurnaceBurnEvent event)
	{
		Block block = event.getBlock();
		//check furnace block against database, if found incinerate the itemStack and remove fuel.
		if (IncineratorManager.getInstance().isBlockIncinerator(block))
		{
			//IncinFurnace incinerator = IncineratorManager.getInstance().getIncinerator(block.getLocation());
			//incinerator.getFurnace();
			
			// do things...
			Furnace furn = (Furnace)block;
			//ItemStack tempStack = furn.getInventory().getItem(0);
			//int qty = tempStack.getAmount();
			// do moar things..
			furn.getInventory().clear();
		}
	}
}
