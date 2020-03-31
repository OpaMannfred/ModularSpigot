package de.stickmc.ms.commands;

import de.stickmc.ms.manager.modules.NotifyModule;
import de.stickmc.ms.utils.messages.NotifyData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Notify implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.notify")) {
            if(NotifyModule.getPlayers().contains(p.getName())){
                NotifyModule.removePlayer(p.getName());
                p.sendMessage(NotifyData.prefix + NotifyData.disable);
            }else{
                NotifyModule.addPlayer(p.getName());
                p.sendMessage(NotifyData.prefix + NotifyData.enable);
            }
        }else{
            p.sendMessage(NotifyData.prefix + NotifyData.noperm);
        }

        return false;
    }
}
