package de.stickmc.ms.commands;

import de.stickmc.ms.manager.modules.NotifyModule;
import de.stickmc.ms.utils.messages.KickData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Kick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.kick")) {

            if(args.length == 0){
                p.sendMessage(KickData.prefix + "§c/kick <Spieler> [Grund]");
            }else if(args.length == 1){
                Player t = Bukkit.getPlayer(args[0]);

                if(t != null){

                    p.sendMessage(KickData.prefix + KickData.success.replace("%player%", t.getName()));
                    if (KickData.broadcastEnabled) {
                        for(Player all : Bukkit.getOnlinePlayers()){
                            if (all.hasPermission("ms.kick.see")) {
                                if (NotifyModule.getPlayers().contains(p.getName())) {
                                    all.sendMessage(KickData.prefix + KickData.noreasonBroadcast.replace("%target%", t.getName()).replace("%player%", p.getName()));
                                }
                            }
                        }
                    }
                    t.kickPlayer(KickData.noreasonKick.replace("%player%", p.getName()).replace("%br%", "\n"));

                }else{
                    p.sendMessage(KickData.prefix + KickData.userNotOnline);
                }
            }else if(args.length == 2){
                Player t = Bukkit.getPlayer(args[0]);
                String grund = args[1];

                if(t != null){
                    if(!t.getName().equalsIgnoreCase(p.getName())){
                        p.sendMessage(KickData.prefix + KickData.success.replace("%player%", t.getName()));
                        if (KickData.broadcastEnabled) {
                            for(Player all : Bukkit.getOnlinePlayers()){
                                if (all.hasPermission("ms.kick.see")) {
                                    all.sendMessage(KickData.prefix + KickData.reasonBroadcast.replace("%target%", t.getName()).replace("%player%", p.getName()).replace("%grund%", grund));
                                }
                            }
                        }
                        t.kickPlayer(KickData.reasonKick.replace("%player%", p.getName()).replace("%grund%", grund).replace("%br%", "\n"));
                    }else{
                        p.sendMessage(KickData.prefix + KickData.notSelf);
                    }



                }else{
                    p.sendMessage(KickData.prefix + KickData.userNotOnline);
                }
            }else{
                p.sendMessage(KickData.prefix + "§c/kick <Spieler> [Grund]");
            }

        }else{
            p.sendMessage(KickData.prefix + KickData.noperm);
        }

        return false;
    }
}
