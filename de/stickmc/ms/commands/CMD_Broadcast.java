package de.stickmc.ms.commands;

import de.stickmc.ms.utils.messages.BroadcastData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Broadcast implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.broadcast")) {

            if(args.length > 0){
                String msg = "";

                for(int i = 0; i < args.length; i++){
                    msg = msg + args[i] + " ";
                }
                Bukkit.broadcastMessage(BroadcastData.prefix + msg.replace("&", "§"));
            }else{
                p.sendMessage(BroadcastData.prefix + "§c/broadcast <Nachricht>");
            }

        }else {
            p.sendMessage(BroadcastData.prefix + BroadcastData.noperm);
        }

        return false;
    }
}
