package com.minecraftwars.incinerator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;

import com.minecraftwars.incinerator.IncinFurnace;

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
            incinerators.add(furnace);
        }
    }

    public void addFurnaces(List<IncinFurnace> furnaces)
    {
        for (IncinFurnace furnace : furnaces)
        {
            if (!incinerators.contains(furnace))
            {
                incinerators.add(furnace);
            }
        }
    }

    public void removeFurnace(IncinFurnace furnace)
    {
        incinerators.remove(furnace);
    }

    public IncinFurnace getIncinerator(Location location)
    {
        Block block = location.getBlock();
        if (!isValidType(block.getType()))
        {
            return null;
        }
        
        for (IncinFurnace incinerator : incinerators)
        {
            // Check sign
            if (incinerator.getSign().getLocation().equals(location))
            {
                return incinerator;
            }
            // Check furnace
            else if (incinerator.getFurnace().getLocation().equals(location))
            {
                return incinerator;
            }
        }
        return null;
    }

    private boolean isValidType(Material material)
    {
        return material == Material.FURNACE || material == Material.BURNING_FURNACE || material == Material.WALL_SIGN;
    }

    public static IncineratorManager getInstance()
    {
        return instance;
    }

    public List<IncinFurnace> getIncinerators() 
    {
        return this.incinerators;
    }

    public boolean isBlockIncinerator(Block block)
    {
        for (IncinFurnace incinerator : incinerators)
        {
            if (incinerator.equals(block)) {
                return true;
            }
        }
        return false;
    }

    public void save(ConfigurationSection section) 
    {
        List<String> data = new ArrayList<String>();
        for (IncinFurnace incin : incinerators)
        {
            String line = incin.getWorldName() + "," + incin.getX() + "," + incin.getY() + "," + incin.getZ() + "," + incin.getFuelLevel() + "," + incin.getTemp();
            data.add(line);
        }
        section.set("Locations", data);
    }

    public static void load(ConfigurationSection section) 
    {
        IncineratorManager incineratorManager = new IncineratorManager();
        List<String> data = new ArrayList<String>();
        data = section.getStringList("Locations");
        for (String incin : data)
        {
            String[] inc = incin.split(",");
            World world = Bukkit.getWorld(inc[0]);
            Location loc = new Location(world, getInt(inc[1]), getInt(inc[2]), getInt(inc[3]));
            Sign sign = (Sign) world.getBlockAt(loc).getState();
            Furnace furnace = (Furnace) Util.furnaceBlock(sign).getState();
            int fuelLevel = getInt(inc[4]);
            int temp = getInt(inc[5]);
            incineratorManager.addFurnace(new IncinFurnace(sign, furnace, fuelLevel, temp));
        }
    }

    private static int getInt(String string)
    {
        return Integer.parseInt(string);
    }

	public void incinerateAll() 
	{
		for (IncinFurnace incinerator : incinerators)
		{
			incinerator.update();
		}
		
	}
}
