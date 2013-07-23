package com.minecraftwars.incinerator;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

public class Util {

    public static Block furnaceBlock(Sign sign) {
        Block signBlock = sign.getBlock();
        org.bukkit.material.Sign signData = (org.bukkit.material.Sign)signBlock.getState().getData();
        BlockFace attached = signData.getAttachedFace();

        Block blockAttached = signBlock.getRelative(attached);
        if (blockAttached.getType() == Material.BURNING_FURNACE || blockAttached.getType() == Material.FURNACE)
        {
            return blockAttached;
        }
        return null; 
    }

    public static int getRandomNumberFrom(int min, int max) {
        Random foo = new Random();
        int randomNumber = foo.nextInt((max + 1) - min) + min;
        return randomNumber;
    }
}
