package de.stickmc.ms.listener;

import de.stickmc.api.OnlineAPI;
import de.stickmc.ms.Main;
import de.stickmc.ms.manager.BanManager;
import de.stickmc.ms.manager.ClanManager;
import de.stickmc.ms.manager.modules.MaintenanceModule;
import de.stickmc.ms.manager.modules.NotifyModule;
import de.stickmc.ms.utils.messages.BanData;
import de.stickmc.ms.utils.messages.MaintenanceData;
import de.stickmc.ms.utils.messages.NotifyData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (p.hasPermission("ms.notify")) {
            if (!NotifyModule.getPlayers().contains(p.getName())) {
                p.sendMessage(NotifyData.prefix + "Deine §cBenachrichtigungen §7sind momentan aktiviert");
            }else{
                p.sendMessage(NotifyData.prefix + "Deine §cBenachrichtigungen §7sind momentan deaktiviert");
            }
        }

        OnlineAPI.createPlayer(p.getUniqueId().toString());

    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        Player p = e.getPlayer();
        String uuid = p.getUniqueId().toString();

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(ClanManager.isPlayerInClan(p.getUniqueId())){
                    String playerID = ClanManager.getPlayerUUID(p.getUniqueId());
                    if(playerID.equals(p.getUniqueId().toString())){
                        ClanManager.addPlayerName(p.getName(), p.getUniqueId());
                    }
                }
            }
        }, 20);

        if (BanManager.isBanned(uuid)) {
            long current = System.currentTimeMillis();
            long end;
            if ((((current < (end = BanManager.getEnd(uuid).longValue())) ? 1 : 0) | ((end == -1L) ? 1 : 0)) != 0) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, BanData.disconnect.replace("%grund%", BanManager.getReason(uuid)).replace("%zeit%", BanManager.getReamainingTime(uuid)).replace("%br%", "\n"));
            }else{
                BanManager.unban(uuid);
            }
        }

        if(MaintenanceModule.isEnabled()){
            if (!MaintenanceModule.getCfg().getStringList("Members").contains(p.getName())) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, MaintenanceData.kickLayout);
            }
        }


    }

}
