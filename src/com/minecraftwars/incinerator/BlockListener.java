package com.minecraftwars.incinerator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

public class BlockListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onSignChangeEvent(SignChangeEvent event)
    {
        Block block = event.getBlock();
        if (block.getType() == Material.WALL_SIGN) 
        {
            String line = event.getLine(1);
            if (line.equalsIgnoreCase("[incinerator]"))
            {
                Player player = event.getPlayer();
                Sign sign = (Sign) block.getState();
                Block furnaceBlock = Util.furnaceBlock(sign);
                if (furnaceBlock != null)
                {
                    Furnace furnace = (Furnace) furnaceBlock.getState();
                    IncinFurnace incinFurnace = new IncinFurnace(sign, furnace);
                    IncineratorManager.getInstance().addFurnace(incinFurnace);
                    player.sendMessage(ChatColor.GREEN + "You've made an incinerator!");
                }
                else
                {
                    player.sendMessage(ChatColor.GREEN + "You must place that sign on a furnace!");
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event)
    {
        // Don't need this?
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (block.getType() == Material.WALL_SIGN) 
        {
            Incinerator.DEBUG("Sign broken.");
            Sign sign = (Sign) block.getState();
            if (Util.furnaceBlock(sign) != null)
            {
                IncinFurnace incinFurnace = IncineratorManager.getInstance().getIncinerator(block.getLocation());
                if (incinFurnace != null)
                {
                    IncineratorManager.getInstance().removeFurnace(incinFurnace);
                    player.sendMessage(ChatColor.RED + "You've broken an incinerator!");
                }
            }
        }
        if (block.getType() == Material.FURNACE)
        {
            Incinerator.DEBUG("Furnace broken.");
            if (IncineratorManager.getInstance().getIncinerator(block.getLocation()) != null)
            {
                IncinFurnace incinFurnace = IncineratorManager.getInstance().getIncinerator(block.getLocation());
                if (incinFurnace != null)
                {
                    IncineratorManager.getInstance().removeFurnace(incinFurnace);
                    player.sendMessage(ChatColor.RED + "You've broken an incinerator!");
                }
            }
        }
    }
}
