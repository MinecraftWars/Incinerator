package com.minecraftwars.incinerator;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;

public class IncinFurnace {
	
	private int id;
	private String world;
	private int x;
	private int y;
	private int z;
	
	private Sign sign;
	private Block furnace;
	
	public IncinFurnace(int id, Block block) 
	{
		this.id = id;
		if (block instanceof Sign) {
			sign = (Sign)block;
			furnace = getIncinFromBlock(block);
		} else if (block instanceof Furnace) {
			// do sonmething stupid
		}
		this.world = furnace.getWorld().getName();
		this.x = block.getX();
		this.y = block.getY();
		this.z = block.getZ();
	}
	
	public IncinFurnace(Block block) 
	{
		int id = 0;
		
		this.world = furnace.getWorld().getName();
		this.x = furnace.getX();
		this.y = furnace.getY();
		this.z = furnace.getZ();
	}
	
	private Block getIncinFromBlock(Block block)
	{
		Block incinBlock = null;
		
		if (block instanceof Sign)
		{
			incinBlock = Util.furnaceBlock((Sign)block);
			if (incinBlock != null)
			{
				return incinBlock;
			}
		}
		if (block.getType() == Material.FURNACE)
		{
			return block;
		}
		
		return null;
	}
	
	public Block getFurnace()
	{
		return this.furnace;
	}
	
	public String getWorldName()
	{
		return this.world;
	}
	
	public Sign getSign()
	{
		return this.sign;
	}

	public int getId() {
		return this.id;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public boolean equals(IncinFurnace furnace)
	{
		if (this.getId() == furnace.getId())
		{
			return true;
		} 
		else if (this.getX() == furnace.getX() && this.getY() == furnace.getY() && this.getZ() == furnace.getZ()) 
		{
			return true;
		}
		return false;
	}
}
