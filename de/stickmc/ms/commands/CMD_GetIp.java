package de.stickmc.ms.commands;

import de.stickmc.ms.utils.messages.GetIpData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_GetIp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.getip")) {

            if(args.length == 0){
                p.sendMessage(GetIpData.prefix + "§c/getip <Spieler>");
            }else if(args.length == 1){
                Player t = Bukkit.getPlayerExact(args[0]);
                p.sendMessage(GetIpData.prefix + GetIpData.getip.replace("%ip%", String.valueOf(t.getAddress().getAddress().getHostName())));
            }
        }else{
            p.sendMessage(GetIpData.prefix + GetIpData.noperm);
        }

        return false;
    }
}
