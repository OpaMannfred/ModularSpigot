package de.stickmc.ms.commands;

import de.stickmc.ms.utils.messages.ReportData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Denyreport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.denyreport")) {
            if(args.length == 1){
                String name = args[0];
                p.sendMessage(ReportData.prefix + ReportData.deny.replace("%player%", name));
            }
        }

        return false;
    }
}
