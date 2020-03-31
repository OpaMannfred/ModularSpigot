package de.stickmc.ms.listener;

import de.stickmc.ms.manager.MotdManager;
import de.stickmc.ms.manager.modules.MaintenanceModule;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e){
        if(MaintenanceModule.isEnabled()){
            e.setMotd(ChatColor.translateAlternateColorCodes('&', MotdManager.getWartung1().replace("[>>]", "»").replace("[o]", "●"))
                    + "\n" +
                    ChatColor.translateAlternateColorCodes('&', MotdManager.getWartung2().replace("[>>]", "»").replace("[o]", "●")));
        }else{
            e.setMotd(ChatColor.translateAlternateColorCodes('&', MotdManager.getNormal1().replace("[>>]", "»").replace("[o]", "●"))
                    + "\n"
                    + ChatColor.translateAlternateColorCodes('&', MotdManager.getNormal2().replace("[>>]", "»").replace("[o]", "●")));
        }
    }

}
