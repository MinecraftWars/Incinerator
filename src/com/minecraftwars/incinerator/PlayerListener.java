package com.minecraftwars.incinerator;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) 
    {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            Block block = event.getClickedBlock();
            Player player = event.getPlayer();
            if (IncineratorManager.getInstance().isBlockIncinerator(block))
            {
                player.sendMessage(ChatColor.RED + "It's Hot! Ouch!");
            }
        }
    }
}
