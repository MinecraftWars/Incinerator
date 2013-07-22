package com.minecraftwars.incinerator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event)
	{
		Block block = event.getBlock();
		if (block.getType() == Material.SIGN) 
		{
			org.bukkit.material.Sign signMaterial = (org.bukkit.material.Sign) block.getState().getData();
			BlockFace blockFace = signMaterial.getAttachedFace();
			Block blockAttachedTo = block.getRelative(blockFace);
			if (blockAttachedTo.getType() == Material.FURNACE)
			{
				Sign sign = (Sign) block;
				String line = sign.getLine(2);
				if(line.equalsIgnoreCase("[incinerator]"))
				{   
					org.bukkit.block.Furnace furnace = (org.bukkit.block.Furnace) blockAttachedTo;
					Player player = event.getPlayer();
					int id = IncineratorManager.getInstance().getIncinerators().size();
					IncinFurnace incinFurnace = new IncinFurnace(id++, furnace.getBlock());
					IncineratorManager.getInstance().addFurnace(incinFurnace);
					player.sendMessage(ChatColor.RED + "You've made an incinerator!");					
				}
			}
		}
		
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event)
	{
		Block block = event.getBlock();
		if (block.getType() == Material.SIGN) 
		{
			if (Util.furnaceBlock((Sign)block) != null)
			{
				IncinFurnace incinFurnace = IncineratorManager.getInstance().getIncinFurnace(block.getLocation());
				IncineratorManager.getInstance().removeFurnace(incinFurnace);
				Incinerator.DEBUG("Sign broken...");
			}
		}
		if (block.getType() == Material.FURNACE)
		{
			
		}
	}
}
