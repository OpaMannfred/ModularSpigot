package de.stickmc.ms.listener;

import de.stickmc.ms.utils.messages.BlockData;
import org.apache.logging.log4j.core.config.JSONConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CMDListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();
        String[] cmd = e.getMessage().replace("/", "").split(" ");
        if(cmd[0].equalsIgnoreCase("pl")
                || cmd[0].equals("plugins")
                || cmd[0].equals("bukkit:pl")
                || cmd[0].equals("bukkit:plugins")
                || cmd[0].equalsIgnoreCase("ver")
                || cmd[0].equalsIgnoreCase("bukkit:?")
                || cmd[0].equalsIgnoreCase("?")){
            if(!p.hasPermission("ms.cmdblock.bypass")){
                e.setCancelled(true);
                sendMSG(p);
            }
        }
    }

    private void sendMSG(Player p){
        p.sendMessage(BlockData.prefix + BlockData.block);
    }
}
