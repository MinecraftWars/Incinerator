package com.minecraftwars.incinerator;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;

public class IncinFurnace {

    private String world;
    private int x;
    private int y;
    private int z;

    private Sign sign;
    private Furnace furnace;
    
    private int fuelQty;
    private int maxFuel = 500;
    private int actTemp;
    private int meltTemp = 250 ;
    private int maxTemp = 300;
    private int runTimes = 0;
    
    private List<Fuel> fuels;
    private int fuelPerIncin;
    
    private boolean signNeedsUpdate = false;
    private boolean isActive = false;

    public IncinFurnace(Sign sign, Furnace furnace) 
    {
        this(sign, furnace, 0, 0);
    }

    public IncinFurnace(Sign sign, Furnace furnace, int fuelQty, int actTemp)
    {
        this.sign = sign;
        this.furnace = furnace;
        this.world = sign.getWorld().getName();
        this.x = sign.getX();
        this.y = sign.getY();
        this.z = sign.getZ();
        this.fuelQty = fuelQty;
        this.actTemp = actTemp;
        //TODO: add config hooks for fuel ids and qtys.
        fuels = new ArrayList<Fuel>();
        fuels.add(new Fuel(263, 10));
        fuels.add(new Fuel(173, 100));
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
        else if (obj instanceof Sign)
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

    public void incinerate() 
    {
        if (canIncinerate())
        {
            furnace.setCookTime((short) 200);
            if (removeFromInv(0, Util.getRandomNumberFrom(32, 64)))
            {
                // TODO: Manage BurnTime
                // TODO: Change block to burning furnace?
                furnace.setBurnTime((short) 100);
                runTimes++;
            }
        }
        else
        {
            furnace.setCookTime((short) 0);
        }
    }

    private int getFuelRate(int id)
    {
        for (Fuel fuel : fuels)
        {
            if (fuel.getFuelId() == id)
            {
                return fuel.getValue();
            }
        }
        return 0;
    }

    public void update() 
    {
        // Check if valid TODO: squid
        if (furnace == null || sign == null)
        {
            return;
        }
        // Check fuel
        // Add fuel
        // Check temperature
        // Heat && BurnFuel || Cool
        // Incinerate
        // Update Sign
        
        boolean hasFuel = checkFuel();
        boolean needsFuel = needsFuel();
        boolean canFuel = canFuel();
        
        boolean isHeated = checkHeat();
        boolean canHeat = canHeat();
        boolean hasHeated = false;
        
        if(needsFuel && canFuel)
        {
            addFuel();
        }
        if(canHeat && hasFuel)
        {
            hasHeated = heat();
        }
        if (!hasHeated)
        {
            cool();
        }
        if (isHeated)
        {
            incinerate();
        }
        if (this.signNeedsUpdate)
        {
            updateSign();
        }
        this.isActive = isActivated();
    }

    private void cool() 
    {
        if (this.actTemp - 1 >= 0)
        {
            this.actTemp -= 1;
            signNeedsUpdate = true;
        }
    }

    private boolean canHeat() 
    {
        return this.actTemp < this.maxTemp;
    }

    private boolean canFuel()
    {
        ItemStack stack = this.furnace.getInventory().getItem(1);
        int amt = 0;
        if (stack != null)
        {
            amt = getFuelRate(stack.getType().getId());
        }
        if (amt == 0)
        {
            return false;
        }
        return this.fuelQty + amt <= this.maxFuel;
    }

    private boolean canIncinerate()
    {
        FurnaceInventory inv = this.furnace.getInventory();
        ItemStack item = inv.getItem(0);
        if (item == null)
        {
            return false;
        }
        else if (item.getTypeId() == 0 || item.getAmount() <= 0)
        {
            return false;
        }
        return true;
    }

    private boolean checkFuel() 
    {
        return this.fuelQty >= 1;
    }

    private boolean needsFuel()
    {
        return this.fuelQty < this.maxFuel;
    }

    private boolean checkHeat() 
    {
        return this.actTemp > this.meltTemp;
    }

    private boolean isActivated() 
    {
        if (checkHeat() || checkFuel()) {
            return true;
        }
        return false;
    }

    private void addFuel() 
    {
        this.fuelQty += getFuelRate(this.furnace.getInventory().getItem(1).getType().getId());
        int amt = this.furnace.getInventory().getItem(1).getAmount();
        int id = this.furnace.getInventory().getItem(1).getType().getId();
        if (amt - 1 <= 0)
        {
            this.furnace.getInventory().setItem(1, new ItemStack(Material.AIR));
        }
        else 
        {
            this.furnace.getInventory().setItem(1, new ItemStack(id, amt-1));
        }
        signNeedsUpdate = true;
    }

    private boolean heat() 
    {    
        this.fuelQty -= 1;
        this.actTemp += 1;
        signNeedsUpdate = true;
        return true;
    }

    public int getFuelLevel() {
        return this.fuelQty;
    }

    public int getTemp() {
        return this.actTemp;
    }

    private boolean removeFromInv(int slot, int amount) {
        if (this.furnace.getInventory() == null)
        {
            return false;
        }
        FurnaceInventory inv = this.furnace.getInventory();
        ItemStack item = inv.getItem(slot);
        if (item == null)
        {
            return false;
        }
        Material material = item.getType();
        if (inv.getItem(slot).getAmount() > 0 && amount > 0 && material != Material.AIR) {
            int newAmount = inv.getItem(slot).getAmount();
            newAmount = newAmount - amount;
            if (newAmount <= 0) {
                inv.setItem(slot, new ItemStack(Material.AIR));
            }
            else
            {
                inv.setItem(slot, new ItemStack(material, newAmount));
            }
            return true;
        }
        return false;
    }

    private void updateSign()
    {
        this.sign.setLine(1, ChatColor.BOLD + "[Incinerator]");
        this.sign.setLine(2, "Temp: " + this.actTemp + "/" + this.maxTemp);
        this.sign.setLine(3, "Fuel: " + this.fuelQty + "/" + this.maxFuel);
        this.sign.update();
        signNeedsUpdate = false;
    }

    public boolean isActive()
    {
        return this.isActive;
    }
}
