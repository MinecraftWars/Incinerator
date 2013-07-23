package com.minecraftwars.incinerator;

import org.bukkit.scheduler.BukkitRunnable;

public class IncinRunnable extends BukkitRunnable
{
    public void start()
    {
        this.runTaskTimer(Incinerator.getInstance(), 10, 10);
    }

    public void run() 
    {
        IncineratorManager.getInstance().incinerateAll();
    }
}
