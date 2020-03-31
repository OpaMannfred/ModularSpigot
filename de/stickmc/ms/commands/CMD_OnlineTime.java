package de.stickmc.ms.commands;

import de.stickmc.api.OnlineAPI;
import de.stickmc.ms.manager.modules.OnlineTimeModule;
import de.stickmc.ms.utils.messages.TimeData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_OnlineTime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if(args.length == 0){
            int h = OnlineAPI.getHours(p.getUniqueId().toString());
            int m = OnlineAPI.getMinutes(p.getUniqueId().toString());
            p.sendMessage(TimeData.prefix + TimeData.msg.replace("%hours%", String.valueOf(h)).replace("%minutes%", String.valueOf(m)));
        }

        return false;
    }
}
