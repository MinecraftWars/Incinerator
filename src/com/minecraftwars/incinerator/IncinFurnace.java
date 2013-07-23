package com.minecraftwars.incinerator;

import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;

public class IncinFurnace {

    private String world;
    private int x;
    private int y;
    private int z;

    private Sign sign;
    private Furnace furnace;

    public IncinFurnace(Sign sign) 
    {
        this(sign, (Furnace)Util.furnaceBlock(sign));
    }

    public IncinFurnace(Sign sign, Furnace furnace)
    {
        this.sign = sign;
        this.furnace = furnace;
        this.world = sign.getWorld().getName();
        this.x = sign.getX();
        this.y = sign.getY();
        this.z = sign.getZ();
    }

    public Furnace getFurnace()
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    @Override
    public boolean equals(Object obj) // TODO: Fix Me!!!
    {
        // null check
        if (obj == null)
            return false;

        // Check if we equal a furnance first..
        if (obj instanceof Furnace) 
        {
            if (this.furnace.getLocation().equals(((Furnace) obj).getLocation())) 
            {
                return true;
            }
        }
        if (obj instanceof Sign)
        {
            if (this.sign.getLocation().equals(((Sign) obj).getLocation()))
            {
                return true;
            }
        }

        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;

        // Check the Incinerator
        IncinFurnace other = (IncinFurnace) obj;
        if (sign.getLocation().equals(other.getSign().getLocation()))
        {
            return true;
        } 
        else if (furnace.getLocation().equals(other.getFurnace().getLocation()))
        {
            return true;
        } 
        return false;
    }
}
