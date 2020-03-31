package de.stickmc.ms.commands;

import de.stickmc.ms.manager.ReportManager;
import de.stickmc.ms.utils.messages.ReportData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Claimreport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player)sender;

        if (p.hasPermission("ms.claimreport")) {
            if(args.length == 1){
                String name = args[0];
                ReportManager.removeUserReport(name);
                ReportManager.removeUserReport(name);
                p.teleport(Bukkit.getPlayerExact(name));
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(ReportData.prefix + ReportData.accept.replace("%player%", name));
            }
        }

        return false;
    }
}
