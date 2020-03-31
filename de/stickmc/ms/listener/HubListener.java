package de.stickmc.ms.listener;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class HubListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        final Player p = e.getPlayer();
        p.teleport(LocationManager.getLocation("Spawn"));
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            public void run() {
                p.teleport(LocationManager.getLocation("Spawn"));
            }
        }, 20);
    }

}
