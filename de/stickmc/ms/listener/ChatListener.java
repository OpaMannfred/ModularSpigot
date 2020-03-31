package de.stickmc.ms.listener;

import de.stickmc.ms.Main;
import de.stickmc.ms.manager.MuteManager;
import de.stickmc.ms.manager.modules.ChatModule;
import de.stickmc.ms.utils.messages.ChatData;
import de.stickmc.ms.utils.messages.HelpData;
import de.stickmc.ms.utils.messages.MuteData;
import de.stickmc.ms.utils.messages.TeamData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (MuteManager.isMuted(p.getName())) {
            long current = System.currentTimeMillis();
            long end = MuteManager.getEnde(p.getName());
            if (end == -1L) {
                e.setCancelled(true);
                p.sendMessage(MuteData.prefix + MuteData.notWrite.replace("%grund%", MuteManager.getGrund(p.getName())).replace("%zeit%", "§4PERMANENT"));
            } else if (current < end) {
                e.setCancelled(true);
                p.sendMessage(MuteData.prefix + MuteData.notWrite.replace("%grund%", MuteManager.getGrund(p.getName())).replace("%zeit%", MuteManager.getVerbleibendeZeit(p.getName())));
            } else {
                MuteManager.resetUnmuteBoolean(p.getName());
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onUnknown(PlayerCommandPreprocessEvent e){
        if(HelpData.enabled){
            if (!(e.isCancelled())) {
                Player p = e.getPlayer();
                String msg = e.getMessage().split(" ")[0];
                HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
                if (topic == null) {
                    p.sendMessage(HelpData.prefix + HelpData.unkownMSG);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if (Main.globalMute) {
            if (!p.hasPermission("ms.globalmute.bypass")) {
                e.setCancelled(true);
                p.sendMessage(ChatData.prefix + "Derzeit ist der §cGlobalMute §aaktiviert");
            }
        }

        for (String words : ChatModule.getCfg().getStringList("config.words")) {
            if (e.getMessage().toUpperCase().contains(words.toUpperCase())) {
                if (!p.hasPermission("ms.chat.bypass")) {
                    e.setCancelled(true);
                    p.sendMessage(ChatData.prefix + ChatData.msg);
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("ms.chat.broadcast")) {
                            all.sendMessage(ChatData.prefix + "§4" + p.getName() + " §7hat ein §cverbotenes §7Wort geschrieben");
                            all.sendMessage(ChatData.prefix + "§8» §c" + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTeamChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        if(p.hasPermission("ms.teamchat")){
            String[] str = e.getMessage().split(" ");

            if (str[0].equalsIgnoreCase("#tc")) {

                e.setCancelled(true);

                String msg = TeamData.format.replace("%player%", p.getName());

                for(int i = 1; i < str.length; i++){
                    msg = msg + "§7" + str[i] + " ";
                }

                for(Player players : Bukkit.getOnlinePlayers()){
                    if (players.hasPermission("ms.teamchat")) {
                        players.sendMessage(msg);
                    }
                }

            }else{
                return;
            }
        }

    }

}
