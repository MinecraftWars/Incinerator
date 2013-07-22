package com.minecraftwars.incinerator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class IncineratorManager 
{
	private static IncineratorManager instance;

	private List<IncinFurnace> incinerators;
	
	public IncineratorManager()
	{
		instance = this;
		incinerators = new ArrayList<IncinFurnace>();
	}
	
	public void addFurnace(IncinFurnace furnace)
	{
		if (!incinerators.contains(furnace))
		{
			incinerators.contains(furnace);
		}
	}
	
	public void removeFurnace(IncinFurnace furnace)
	{
		incinerators.remove(furnace);
	}
	
	public IncinFurnace getIncinFurnace(Location location) {
		for (IncinFurnace furnace : incinerators)
		{
			if (location.getX() == furnace.getX() && location.getY() == furnace.getY() && location.getZ() == furnace.getZ()) {
				return furnace;
			}
		}
		return null;
	}

	public boolean contains(IncinFurnace furnace)
	{
		for (IncinFurnace frnce : incinerators) {
			if (frnce.equals(furnace)) {
				return true;
			}
		}
		return false;
	}
	
	public static IncineratorManager getInstance()
	{
		return instance;
	}
	
	public List<IncinFurnace> getIncinerators() {
		return this.incinerators;
	}
}
